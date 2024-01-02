package model;

public class Supplier {
    private int id;
    private String name;
    private String address;
    private String email;
    private String sdt;
    private int status;

    public Supplier(int id, String name, String address, String email, String sdt, int status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.sdt = sdt;
        this.status = status;
    }

    public Supplier() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
