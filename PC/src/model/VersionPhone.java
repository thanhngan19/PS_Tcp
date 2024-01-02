package model;

public class VersionPhone {
    private int id;
    private Phone phone;
    private Ram ram;
    private Rom rom;
    private ColorBr color;
    private double inPrice;
    private double exPrice;
    private int status;

    public VersionPhone(int id, Phone phone, Ram ram, Rom rom, ColorBr color, double inPrice, double exPrice, int status) {
        this.id = id;
        this.phone = phone;
        this.ram = ram;
        this.rom = rom;
        this.color = color;
        this.inPrice = inPrice;
        this.exPrice = exPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Rom getRom() {
        return rom;
    }

    public void setRom(Rom rom) {
        this.rom = rom;
    }

    public ColorBr getColor() {
        return color;
    }

    public void setColor(ColorBr color) {
        this.color = color;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public double getExPrice() {
        return exPrice;
    }

    public void setExPrice(double exPrice) {
        this.exPrice = exPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public VersionPhone() {
    }

    public VersionPhone(Ram ram, Rom rom, ColorBr color, double inPrice, double exPrice, int status) {
        this.ram = ram;
        this.rom = rom;
        this.color = color;
        this.inPrice = inPrice;
        this.exPrice = exPrice;
        this.status = status;
    }
}
