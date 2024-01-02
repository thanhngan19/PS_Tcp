package model;

import java.util.List;

public class ListSearch {
    private String search;
    private List<Phone> listPhone;


    public ListSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public ListSearch(String search, List<Phone> listPhone) {
        this.search = search;
        this.listPhone = listPhone;
    }

    public List<Phone> getListPhone() {
        return listPhone;
    }

    public void setListPhone(List<Phone> listPhone) {
        this.listPhone = listPhone;
    }
}
