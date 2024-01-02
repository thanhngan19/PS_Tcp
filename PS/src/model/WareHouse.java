package model;

import java.io.Serializable;

public class WareHouse extends Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public WareHouse(int id, String name, int status) {
        super(id, name, status);
    }

    public WareHouse() {
    }
}
