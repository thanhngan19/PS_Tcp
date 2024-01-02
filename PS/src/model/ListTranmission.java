package model;



import java.io.Serializable;
import java.util.List;

public class ListTranmission{
    private List<Phone> listPhone;
    private List<ColorBr> listColor;
    private List<Ram> listRam;
    private List<Rom> listRom;
    private List<Brand> listBrand;
    private List<Origin> listOr;
    private List<VersionPhone> listVer;
    private List<Os> listOs;
    private List<WareHouse> listWare;
    private List<User> user;
    private List<FunctionDetail>  fDetail;
    private List<GrAuthor> grAuthor;
    private List<Customer> customerList;
    private List<AuthorDetail> authorDetailList;
    private List<Supplier> sup;



    public List<Phone> getListPhone() {
        return listPhone;
    }


    public void setListPhone(List<Phone> listPhone) {
        this.listPhone = listPhone;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<ColorBr> getListColor() {
        return listColor;
    }

    public void setListColor(List<ColorBr> listColor) {
        this.listColor = listColor;
    }

    public List<Ram> getListRam() {
        return listRam;
    }

    public void setListRam(List<Ram> listRam) {
        this.listRam = listRam;
    }

    public List<Rom> getListRom() {
        return listRom;
    }

    public void setListRom(List<Rom> listRom) {
        this.listRom = listRom;
    }

    public List<Brand> getListBrand() {
        return listBrand;
    }

    public void setListBrand(List<Brand> listBrand) {
        this.listBrand = listBrand;
    }

    public List<Origin> getListOr() {
        return listOr;
    }

    public void setListOr(List<Origin> listOr) {
        this.listOr = listOr;
    }

    public List<VersionPhone> getListVer() {
        return listVer;
    }

    public void setListVer(List<VersionPhone> listVer) {
        this.listVer = listVer;
    }

    public List<Os> getListOs() {
        return listOs;
    }

    public void setListOs(List<Os> listOs) {
        this.listOs = listOs;
    }

    public List<WareHouse> getListWare() {
        return listWare;
    }

    public void setListWare(List<WareHouse> listWare) {
        this.listWare = listWare;
    }

    public List<FunctionDetail> getfDetail() {
        return fDetail;
    }

    public void setfDetail(List<FunctionDetail> fDetail) {
        this.fDetail = fDetail;
    }

    public List<GrAuthor> getGrAuthor() {
        return grAuthor;
    }

    public void setGrAuthor(List<GrAuthor> grAuthor) {
        this.grAuthor = grAuthor;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public ListTranmission(List<Phone> listPhone, List<ColorBr> listColor, List<Ram> listRam, List<Rom> listRom, List<Brand> listBrand, List<Origin> listOr, List<VersionPhone> listVer, List<Os> listOs, List<WareHouse> listWare, List<User> user, List<FunctionDetail> fDetail, List<GrAuthor> grAuthor, List<Customer> customerList, List<AuthorDetail> authorDetailList, List<Supplier> sup) {
        this.listPhone = listPhone;
        this.listColor = listColor;
        this.listRam = listRam;
        this.listRom = listRom;
        this.listBrand = listBrand;
        this.listOr = listOr;
        this.listVer = listVer;
        this.listOs = listOs;
        this.listWare = listWare;
        this.user = user;
        this.fDetail = fDetail;
        this.grAuthor = grAuthor;
        this.customerList = customerList;
        this.authorDetailList = authorDetailList;
        this.sup = sup;
    }




    public List<AuthorDetail> getAuthorDetailList() {
        return authorDetailList;
    }

    public ListTranmission() {
    }

    public List<Supplier> getSup() {
        return sup;
    }

    public void setSup(List<Supplier> sup) {
        this.sup = sup;
    }

    public void setAuthorDetailList(List<AuthorDetail> authorDetailList) {
        this.authorDetailList = authorDetailList;
    }
}
