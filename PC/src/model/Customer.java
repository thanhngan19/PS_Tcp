package model;

import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private int gender;
    private Date date;
    private String sdt;
    private String email;
    private int status;


    public Customer(int id, String name, int gender, Date date, String sdt, String email, int status) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.sdt = sdt;
        this.email = email;
        this.status = status;
    }

    public Customer(String name, int gender, Date date, String sdt, String email, int status) {
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.sdt = sdt;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
