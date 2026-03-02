public class Admin extends Users{
    int userId;

    public Admin(String name, String gender, int age, String password, Roles type, int userId) {
        super(name, gender, age, password, type);
        this.userId = userId;
    }
}
