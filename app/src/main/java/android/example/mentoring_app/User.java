package android.example.mentoring_app;

public class User {
    private String Email_user, Fullname_user, Password_user;
            private int userType_user;

    public User() {

    }

    public String getEmail_user() {
        return Email_user;
    }

    public void setEmail_user(String email_user) {
        Email_user = email_user;
    }

    public String getFullname_user() {
        return Fullname_user;
    }

    public void setFullname_user(String fullname_user) {
        Fullname_user = fullname_user;
    }

    public String getPassword_user() {
        return Password_user;
    }

    public void setPassword_user(String password_user) {
        Password_user = password_user;
    }



    public int getUserType_user() {
        return userType_user;
    }

    public void setUserType_user(int userType_user) {
        this.userType_user = userType_user;
    }

    public User(String fullname_user, String email_user, String password_user,  int userType_user ) {
        this.Fullname_user = fullname_user;
        this.Email_user = email_user;
        this.Password_user = password_user;
        this.userType_user=userType_user;


    }
}
