package handler;

import model.*;

import java.util.List;

public interface IWareHouseHandle {
    List<WareHouse> findAll();
    String [] transListToArr();
    WareHouse findById(int id);
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void deletePhone(ListTransfer deteleList);


}
