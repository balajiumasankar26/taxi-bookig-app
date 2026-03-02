public class Auth {
    public boolean login(Users user,String password){
        return user.password.equals(password);
    }
}
