package model;

public class NoteDetail
{
    private int id;
    private VersionPhone ver;
    private int quantity;
    private int price;

    public NoteDetail(int id, VersionPhone ver, int quantity, int price) {
        this.id = id;
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
