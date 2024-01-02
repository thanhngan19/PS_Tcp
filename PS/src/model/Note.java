package model;

import java.sql.Timestamp;

public class Note {
    private int id;
    private Customer cus;
    private Timestamp time;
    private long price;
    private int status;

    public Note(int id, Customer cus, Timestamp time, long price, int status) {
        this.id = id;
        this.cus = cus;
        this.time = time;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
