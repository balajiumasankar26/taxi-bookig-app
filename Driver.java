public class Driver extends Users{
    static  int id=0;
    int userId;
    public int isLast;
    String location;
    int cnt;
    public Driver( int userId,String name,  String password,int age,String gender,String location) {
        super(name, gender, age, password, Roles.Driver);
        id+=1;
        this.userId = userId;
        this.location=location;
        isLast=0;
        cnt=0;
    }
    public Driver(String name, String gender, int age, String password,String location) {
        super(name, gender, age, password, Roles.Driver);
        id+=1;
        this.location = location;
        isLast=0;
        cnt=0;
        userId=id;
    }
    @Override
    public String toString() {
        return "Driver{" +
                "userId=" + userId +
                ", isLast=" + isLast +
                ", location='" + location + '\'' +
                '}';
    }
}
