
package model;

import java.util.Date;

public class Reviews {
    private String name, review ;
    private Date date;
    private boolean active;

    public Reviews(String name, String review, Date date, boolean active) {
        this.name = name;
        this.review = review;
        this.date = date;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
