package model;

import java.util.Date;

public class Reservation {

    private int userId, groupSize;
    private String firstName, lastName, cellNum, email;
    private Date date;

    public Reservation(int userId, int groupSize, String firstName, String lastName, String cellNum, String email, Date date) {
        this.userId = userId;
        this.groupSize = groupSize;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellNum = cellNum;
        this.email = email;
        this.date = date;
    }

    public int getUserId() {
        return this.userId;
    }
    
     public int getGroupSize() {
        return this.groupSize;
    }
     
     public String getFirstName() {
        return this.firstName;
    }
     
     public String getLastName() {
        return this.lastName;
    }
     
     public String getCellNum() {
        return this.cellNum;
    }
     
     public String getEmail() {
        return this.email;
    }
     
     public Date getDate() {
        return this.date;
    }
}
