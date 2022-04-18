package model;


public class Admin {
    private String userEmail, email, password;

    public Admin(String userEmail, String email,String password) {
        this.userEmail = userEmail;
        this.email = email;
        this.password = password;
    }
 
    public Admin(String uNameEmail, String pWord) {
        this.userEmail = uNameEmail;
        this.password = pWord;
    }

    public void setUserEmail(String uNameEmail) {
        this.userEmail = uNameEmail;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String pWord) {
        this.password = pWord;
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
