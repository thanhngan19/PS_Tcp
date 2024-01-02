package model;

public class User {
    private int id;
    private String userName;
    private String passWord;
    private GrAuthor manhomquyen;
    private int status;

    public User(int id, String userName, String passWord, GrAuthor manhomquyen, int status) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.manhomquyen = manhomquyen;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public GrAuthor getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(GrAuthor manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
