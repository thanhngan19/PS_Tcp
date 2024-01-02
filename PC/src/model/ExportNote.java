package model;

import java.sql.Timestamp;

public class ExportNote extends Note {


    public ExportNote(int id, Customer cus, Timestamp time, long price, int status) {
        super(id, cus, time, price, status);
    }

}
