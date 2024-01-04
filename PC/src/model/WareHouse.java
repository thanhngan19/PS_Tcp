package model;

import java.io.Serializable;

public class WareHouse {
    private int id;
    private String name;
    private String note;
    private int status;

    public WareHouse(int id, String name, String note, int status) {
        this.id = id;
        this.name = name;
        this.note = note;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public WareHouse() {
    }

    public WareHouse(String name, String note, int status) {
        this.name = name;
        this.note = note;
        this.status = status;
    }
}
