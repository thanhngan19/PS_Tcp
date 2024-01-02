package model;

public class UserStatus {
   private String userName;
   private String name;
   private String time;
   private String date;
   private String status;


    public UserStatus(String userName, String name, String time, String date, String status) {
        this.userName = userName;
        this.name = name;
        this.time = time;
        this.date = date;
        this.status = status;

    }

    public UserStatus() {
    }

    public UserStatus(String userName, String name, String status) {
        this.userName = userName;
        this.name = name;
        this.status = status;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
