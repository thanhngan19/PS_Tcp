package model;

import java.io.Serializable;

public class Ram extends Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public Ram(int id, String name, int status) {
        super(id, name, status);
    }

    public Ram() {
    }

}
