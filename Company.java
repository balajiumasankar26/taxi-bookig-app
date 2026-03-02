
import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private String email;
    private String location;
    private long commision;
    private static Company zula;
    private List<Customer> customerList;
    private List<Driver> driverList;
    private List<Taxis> taxisList;
    private Admin admin;
    private List<Location> locationList;
    private List<Booking> bookingList;
    int []map=new int[256];
    private Company(String name, String email, String location) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.commision = 0;
        driverList=new ArrayList<>();
        taxisList=new ArrayList<>();
        locationList=new ArrayList<>();
        customerList=new ArrayList<>();
        bookingList=new ArrayList<>();
    }
    public static Company getInstance(String name, String email, String location){
        if(zula==null){
            zula=new Company(name,email,location);
            zula.initData();
        }
        return zula;
    }

    private void initData() {
        driverList.add(new Driver(1,"aaa","111",43,"M","D"));
        driverList.add(new Driver(2,"bbb","222",31,"M","G"));
        driverList.add(new Driver(3,"ccc","333",38,"F","H"));
        driverList.add(new Driver(4,"ddd","444",28,"F","A"));
        customerList.add(new Customer(1,"zz","99",25,"F"));
        customerList.add(new Customer(2,"yy","88",61,"M"));
        customerList.add(new Customer(3,"xx","77",22,"M"));
        customerList.add(new Customer(1,"ww","66",36,"F"));
        locationList.add(new Location(1,"A",0));
        locationList.add(new Location(1,"C",4));
        locationList.add(new Location(1,"D",7));
        locationList.add(new Location(1,"F",9));
        locationList.add(new Location(1,"B",15));
        locationList.add(new Location(1,"G",18));
        locationList.add(new Location(1,"H",20));
        locationList.add(new Location(1,"E",23));
        map['A']=0;
        map['C']=4;
        map['D']=7;
        map['F']=9;
        map['B']=15;
        map['G']=18;
        map['H']=20;
        map['E']=23;
        taxisList.add(new Taxis(1,"AA","D"));
        taxisList.add(new Taxis(2,"AB","G"));
        taxisList.add(new Taxis(3,"AC","H"));
        taxisList.add(new Taxis(4,"AD","A"));

    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public List<Taxis> getTaxisList() {
        return taxisList;
    }

    public Users getUser(String userName,String role) {
        if(role.equals("driver")){
            for(Driver user:driverList){
                if(user.name.equals(userName)){
                    return user;
                }
            }
        }
        if(role.equals("customer")){
            for(Customer user:customerList){
                if(user.name.equals(userName)){
                    return user;
                }
            }
        }
        if(role.equals("admin")){
            return new Admin("Admin","M",20,"123445",Roles.Admin,1);
        }
        return null;
    }
    public void addCustomer(Customer newCus) {
        customerList.add(newCus);
    }

    /*public void book() {
        //find the nearby as nearbyUser;
        for(Driver d:driverList){
            if(d.isLast==1){
                d.isLast=0;
            }
        }
        //nearbyUser.isLast=1;
    }*/

    public Booking makeBook(String src, String des, Customer currUser) {
        //findNear;
        for(Driver d:driverList){
            System.out.println("id "+d.userId+"location "+d.location);
        }
        Driver minTaxi=null;
        int min=Integer.MAX_VALUE;
        for(Driver t:driverList){
            if(t.isLast==1) continue;
            int a=map[t.location.charAt(0)];
            int b=map[src.charAt(0)];
            int dist=Math.abs((int)(b-a));
            if(dist<min){
                min=dist;
                minTaxi=t;
            }
            else if(dist==min&&minTaxi!=null&&t.cnt<minTaxi.cnt){
                minTaxi=t;
            }
        }
        if(minTaxi==null) return null;
        int fair=Math.abs(map[des.charAt(0)]-map[src.charAt(0)])*10;
        Booking book=new Booking(1,src,des,fair,minTaxi,currUser);
        return book;
    }

    public void addNewBooking(Booking booking) {
        for(Driver d:driverList){
            if(d.isLast==1){
                d.isLast=0;
                break;
            }
        }
        booking.driver.isLast=1;
        bookingList.add(booking);
        booking.driver.location=booking.des;
        booking.driver.cnt+=1;
        addCommision(booking.fair);
        System.out.println("Booked successfully");
    }

    public void printRideSummary(Customer currUser) {
        System.out.println("Customer Id"+currUser.userId);
        System.out.println("Customer Name"+currUser.name);
        System.out.println("src     des    Cab details      Fare");
        for(Booking b:bookingList){
            if(b.customer.userId==currUser.userId){
                System.out.println(b.src+"  "+b.des+"   "+b.driver.userId+"    "+b.fair);
            }
        }
    }
    public void addCommision(long fair){
        commision+= (long) (fair*0.3);
    }

    public void cabDriverSummary(Driver currUser) {
        System.out.println("Customer Id"+currUser.userId);
        System.out.println("Customer Name"+currUser.name);
        System.out.println("src     des    Customer details     Fare     Zula commission");
        for(Booking b:bookingList){
            if(b.driver.userId==currUser.userId){
                System.out.println(b.src+"  "+b.des+"   "+b.driver.userId+"    "+b.fair+"   "+b.zulaCommision);
            }
        }
    }

    public void addCab(String name, String pass, int age, String gender, String loc) {
        driverList.add(new Driver(name,gender,age,pass,loc));
    }
    public void addLoc(String name,int origin){
        map[name.charAt(0)]=origin;
    }
    public void removeLoc(String name,int origin){
    }
    public void printCapDetails(){
        for(Driver d:driverList){
            printCap(d);
        }
        System.out.println();
    }
    public void printCap(Driver currUser) {
        long fair=0,zCommision=0;
        System.out.println("Cab id"+currUser.userId);
        System.out.println("Total numbers of trips: "+bookingList.size());
        for(Booking b:bookingList){
                //System.out.println(b.src+"  "+b.des+"   "+b.customer.userId+"    "+b.fair+"   "+b.zulaCommision);
                fair+=b.fair;
                zCommision+=b.zulaCommision;
        }
        System.out.println("Total Fare Collected: "+fair);
        System.out.println("Total Zula commision: "+zCommision);
        System.out.println("Trip details");
        System.out.println("src     des    Customer details     Fare     Zula commission");
        for(Booking b:bookingList){
                System.out.println(b.src+"  "+b.des+"   "+b.customer.userId+"    "+b.fair+"   "+b.zulaCommision);
        }
    }

    public void printCustomerDetails() {
        for(Customer cus:customerList){
            printdetails(cus);
        }
        System.out.println();
    }
    public void printdetails(Customer cus){
        long trips=0,f=0;
        System.out.println("Customer Id: "+cus.userId);
        for(Booking b:bookingList){
            if(b.customer.userId==cus.userId){
                trips++;
                f+=b.fair;
            }
        }
        System.out.println("Total Number of Trips: "+trips);
        System.out.println("Total Fare Given: "+f);
        System.out.println("src     des    Customer details     Fare     Zula commission");
        for(Booking b:bookingList){
            if(b.customer.userId==cus.userId){
                System.out.println(b.src+"  "+b.des+"  "+b.driver.userId+"  "+b.fair);
            }
        }
    }
}
