package model;

import java.io.Serializable;

public class Rom extends Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public Rom(int id, String name, int status) {
        super(id, name, status);
    }

    public Rom() {
    }
}