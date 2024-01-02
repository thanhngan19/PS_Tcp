package model;

public class ImportNoteDetail{
    private int id;
    private int imNote;
    private VersionPhone ver;
    private int quantity;
    private int price;
    private int howTo;

    public ImportNoteDetail(int id, int imNote, VersionPhone ver, int quantity, int price, int howTo) {
        this.id = id;
        this.imNote = imNote;
        this.ver = ver;
        this.quantity = quantity;
        this.price = price;
        this.howTo = howTo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImNote() {
        return imNote;
    }

    public void setImNote(int imNote) {
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

    public int getHowTo() {
        return howTo;
    }

    public void setHowTo(int howTo) {
        this.howTo = howTo;
    }
}
