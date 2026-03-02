public class Customer extends Users{
    static int id;
    int userId;

    public Customer(int userId,String name, String password, int age,String gender) {
        super(name, gender, age, password, Roles.Customer);
        id+=1;
        this.userId = userId;
    }

    public Customer(String name, String gender, int age, String password) {
        super(name, gender, age, password, Roles.Customer);
        this.userId = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
