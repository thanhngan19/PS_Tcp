package model;

import java.io.Serializable;

public class ColorBr extends Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public ColorBr(int id, String name, int status) {
        super(id, name, status);
    }

    public ColorBr() {
    }
}
