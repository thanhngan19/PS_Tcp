package model;

import java.sql.Timestamp;

public class ImportNote extends Note{
private Supplier sup;

    public ImportNote(int id, Customer cus, Timestamp time, long price, int status, Supplier sup) {
        super(id, cus, time, price, status);
        this.sup = sup;
    }



    public Supplier getSup() {
        return sup;
    }

    public void setSup(Supplier sup) {
        this.sup = sup;
    }
}
