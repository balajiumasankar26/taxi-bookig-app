public class User {
    String name ;
    String gender;
    int age;
    String password;
    Roles type;

    public User(String name, String gender, int age, String password, Roles type) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.password = password;
        this.type = type;
    }
}
