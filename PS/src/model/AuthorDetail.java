package model;

public class AuthorDetail {
    private int id;
    private String name;
    private String action;

    public AuthorDetail(int id, String name, String action) {
        this.id = id;
        this.name = name;
        this.action = action;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AuthorDetail() {
    }
}
