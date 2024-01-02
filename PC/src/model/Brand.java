package model;

import java.io.Serializable;

public class Brand extends Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public Brand(int id, String name, int status) {
        super(id, name, status);
    }

    public Brand() {
    }
}
