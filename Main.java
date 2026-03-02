import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Company zula=Company.getInstance("zula","zula@gmail.com","A");
        Scanner scan=new Scanner(System.in);
        int bId=1;
        Users currUser=null;
        while(true){
            if(currUser==null)
            System.out.println("1=>Login -1=>Exit");
            else System.out.println("2-booking 3-Customer rides summary 4-Cab rides summary 5-new Cab 6-new Location 7-All cabs 8-All customers -1=>Exit");
            int ch=scan.nextInt();
            if(ch==-1) break;;
            switch (ch){
                case 1:
                    System.out.println("Enter your userName");
                    String userName=scan.next();
                    System.out.println("Enter your role");
                    String role=scan.next();
                    System.out.println("Enter your password");
                    String password=scan.next();
                    Users user=zula.getUser(userName,role);
                    Auth auth=new Auth();
                    if(user!=null&&auth.login(user,password)){
                        System.out.println("Login successfull");
                        currUser=user;
                    }
                    else if(role.equals("admin")){
                        currUser=user;
                    }
                    else {
                        System.out.println("Invalid cred");
                        if (role.equals("customer")) {
                            System.out.println("Create a account give the following details");
                            String name = scan.next();
                            password = scan.next();
                            int age = scan.nextInt();
                            String gender = scan.next();
                            Customer newCus = new Customer(name, gender, age, password);
                            zula.addCustomer(newCus);
                            currUser=newCus;
                        }
                    }
                    break;
                case 2:
                    if(currUser==null){
                        System.out.println("login first");
                        break;
                    }
                    if(currUser.type==Roles.Customer) {
                        System.out.println("Enter the source and destination");
                        String src = scan.next();
                        String des = scan.next();
                        Booking booking = zula.makeBook(src, des, (Customer)currUser);
                        if (booking == null) {
                            System.out.println("Not avail");
                        } else {
                            System.out.println("Fare Estimate=> " + booking.fair);
                            System.out.println(booking);
                            System.out.println(booking.driver);
                            boolean isok = false;
                            System.out.println("Accepting or not(true/false)");
                            String in = scan.next();
                            if (in.equals("true")) {
                                isok = true;
                            }
                            if (isok) {
                                zula.addNewBooking(booking);
                                bId++;
                            }
                        }

                    }
                    break;
                case 3:
                    if(currUser==null) {
                        System.out.println("Login first");
                        break;
                    }
                    zula.printRideSummary((Customer)currUser);
                    break;
                case 4:
                    if(currUser==null) {
                        System.out.println("Login first");
                        break;
                    }
                    if(currUser.type== Roles.Driver) {
                        zula.cabDriverSummary((Driver)currUser);
                    }
                    break;
                case 5:
                    if(currUser==null) {
                        System.out.println("Login first");
                        break;
                    }
                    if(currUser.type==Roles.Admin){
                        System.out.println("Enter name,pass,age,gender,loc");
                        String name=scan.next();
                        String pass=scan.next();
                        int age=scan.nextInt();
                        String gender=scan.next();
                        String loc=scan.next();
                        zula.addCab(name,pass,age,gender,loc);
                    }
                    else{
                        System.out.println("Access denied");
                    }
                    break;
                case 6:
                    if(currUser==null) {
                        System.out.println("Login first");
                        break;
                    }
                    if(currUser.type==Roles.Admin){
                        System.out.println("Enter the location name,origin");
                        String name=scan.next();
                        int origin=scan.nextInt();
                        System.out.println("1.add 2.remove");
                        int in=scan.nextInt();
                        if(in==1){
                            zula.addLoc(name,origin);
                        }
                        else if(in==2){
                            zula.removeLoc(name,origin);
                        }
                    }
                    else{
                        System.out.println("Access denied");
                    }
                    break;
                case 7:
                    if(currUser==null) {
                        System.out.println("Login first");
                        break;
                    }
                    if(currUser.type==Roles.Admin){
                        zula.printCapDetails();
                    }
                    break;
                case 8:
                    if(currUser==null) {
                        System.out.println("Login first");
                        break;
                    }
                    if(currUser.type==Roles.Admin){
                        zula.printCustomerDetails();
                    }
                    break;
            }
        }
    }
}