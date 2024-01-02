package model;

import java.io.Serializable;

public class FunctionDetail {
   private String machucnang;
   private String tenchucnang;
   private int status;

    public FunctionDetail(String machucnang, String tenchucnang, int status) {
        this.machucnang = machucnang;
        this.tenchucnang = tenchucnang;
        this.status = status;
    }

    public String getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(String machucnang) {
        this.machucnang = machucnang;
    }

    public String getTenchucnang() {
        return tenchucnang;
    }

    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public FunctionDetail() {
    }
}
