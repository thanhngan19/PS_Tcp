package model;

import java.io.Serializable;

public class ListTransfer{

    private Phone phoneEdit;
    private PhoneDetail phoneDetailEdit;
    private ColorBr colorEdit;
    private Ram ramEdit;
    private Rom romEdit;
    private Brand brEdit;
    private Os osEdit;
    private Origin orEdit;
    private VersionPhone ver;
    private String message;
    private int id;
    private User user;

    public Phone getPhoneEdit() {
        return phoneEdit;
    }

    public void setPhoneEdit(Phone phoneEdit) {
        this.phoneEdit = phoneEdit;
    }

    public PhoneDetail getPhoneDetailEdit() {
        return phoneDetailEdit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPhoneDetailEdit(PhoneDetail phoneDetailEdit) {
        this.phoneDetailEdit = phoneDetailEdit;
    }

    public ColorBr getColorEdit() {
        return colorEdit;
    }

    public void setColorEdit(ColorBr colorEdit) {
        this.colorEdit = colorEdit;
    }

    public Ram getRamEdit() {
        return ramEdit;
    }

    public void setRamEdit(Ram ramEdit) {
        this.ramEdit = ramEdit;
    }

    public Rom getRomEdit() {
        return romEdit;
    }

    public void setRomEdit(Rom romEdit) {
        this.romEdit = romEdit;
    }

    public Brand getBrEdit() {
        return brEdit;
    }

    public void setBrEdit(Brand brEdit) {
        this.brEdit = brEdit;
    }

    public Os getOsEdit() {
        return osEdit;
    }

    public void setOsEdit(Os osEdit) {
        this.osEdit = osEdit;
    }

    public Origin getOrEdit() {
        return orEdit;
    }

    public void setOrEdit(Origin orEdit) {
        this.orEdit = orEdit;
    }

    public VersionPhone getVer() {
        return ver;
    }

    public void setVer(VersionPhone ver) {
        this.ver = ver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ListTransfer(Phone phoneEdit, String message) {
        this.phoneEdit = phoneEdit;
        this.message = message;
    }

    public ListTransfer(PhoneDetail phoneDetailEdit, String message) {
        this.phoneDetailEdit = phoneDetailEdit;
        this.message = message;
    }

    public ListTransfer(ColorBr colorEdit, String message) {
        this.colorEdit = colorEdit;
        this.message = message;
    }

    public ListTransfer(Ram ramEdit, String message) {
        this.ramEdit = ramEdit;
        this.message = message;
    }

    public ListTransfer(Rom romEdit, String message) {
        this.romEdit = romEdit;
        this.message = message;
    }

    public ListTransfer(Brand brEdit, String message) {
        this.brEdit = brEdit;
        this.message = message;
    }

    public ListTransfer(Os osEdit, String message) {
        this.osEdit = osEdit;
        this.message = message;
    }

    public ListTransfer(Origin orEdit, String message) {
        this.orEdit = orEdit;
        this.message = message;
    }

    public ListTransfer(VersionPhone ver, String message) {
        this.ver = ver;
        this.message = message;
    }

    public ListTransfer(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public ListTransfer(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public ListTransfer(Phone phoneEdit, VersionPhone ver, String message) {
        this.phoneEdit = phoneEdit;
        this.ver = ver;
        this.message = message;
    }
}
