package model;

import java.io.Serializable;

public class Os extends Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public Os(int id, String name, int status) {
        super(id, name, status);
    }

    public Os() {
    }
}
