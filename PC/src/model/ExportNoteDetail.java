package model;

public class ExportNoteDetail {
    private int id;
    private ExportNote imNote;
    private VersionPhone ver;
    private int quantity;
    private int price;

    public ExportNoteDetail(int id, ExportNote imNote, VersionPhone ver, int quantity, int price) {
        this.id = id;
        this.imNote = imNote;
        this.ver = ver;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExportNote getImNote() {
        return imNote;
    }

    public void setImNote(ExportNote imNote) {
        this.imNote = imNote;
    }

    public VersionPhone getVer() {
        return ver;
    }

    public void setVer(VersionPhone ver) {
        this.ver = ver;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
