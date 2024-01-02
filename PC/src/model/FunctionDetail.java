package model;

import java.io.Serializable;

public class FunctionDetail extends Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public FunctionDetail(int id, String name, int status) {
        super(id, name, status);
    }

    public FunctionDetail() {
    }
}
