package model;


public class Admin {
    private static String userEmail, email, password;

    public Admin(String uNameEmail, String pWord) {
        userEmail = uNameEmail;
        password = pWord;
    }

    public void setDetails(String uNameEmail, String pWord) {
        userEmail = uNameEmail;
        password = pWord;
    }

    public String getEmail() {
        return email;
    }
    
    public String getUsername() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }
}
