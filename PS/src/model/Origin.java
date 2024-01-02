package model;

import java.io.Serializable;

public class Origin extends Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public Origin(int id, String name, int status) {
        super(id, name, status);
    }

    public Origin() {
    }
}
