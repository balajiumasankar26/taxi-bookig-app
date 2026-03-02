public class Users {
    String name;
    String gender;
    int age;
    String password;
    Roles type;

    public Users(String name, String gender, int age, String password, Roles type) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.password = password;
        this.type = type;
    }
}
