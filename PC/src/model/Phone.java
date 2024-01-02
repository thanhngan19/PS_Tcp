package model;

import java.io.Serializable;

public class Phone implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String img;
    private Origin origin;
    private String chip;
    private int pin;
    private double inch;
    private Os os;
    private int iOs;
    private String rearCam;
    private String frontCam;
    private int time;
    private Brand brand;
    private WareHouse wareHouse;
    private int quantity;
    private int status;

    public Phone(int id, String name, String img, Origin origin, String chip, int pin, double inch, Os os, int iOs, String rearCam, String frontCam, int time, Brand brand, WareHouse wareHouse, int quantity,int status) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.origin = origin;
        this.chip = chip;
        this.pin = pin;
        this.inch = inch;
        this.os = os;
        this.iOs = iOs;
        this.rearCam = rearCam;
        this.frontCam = frontCam;
        this.time = time;
        this.brand = brand;
        this.wareHouse = wareHouse;
        this.quantity = quantity;
        this.status= status;
    }

    public Phone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getInch() {
        return inch;
    }

    public void setInch(double inch) {
        this.inch = inch;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRearCam() {
        return rearCam;
    }

    public void setRearCam(String rearCam) {
        this.rearCam = rearCam;
    }

    public String getFrontCam() {
        return frontCam;
    }

    public void setFrontCam(String frontCam) {
        this.frontCam = frontCam;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Os getOs() {
        return os;
    }

    public void setOs(Os os) {
        this.os = os;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getiOs() {
        return iOs;
    }

    public void setiOs(int iOs) {
        this.iOs = iOs;
    }
}
