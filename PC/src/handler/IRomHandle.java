package handler;

import model.*;

import java.util.List;

public interface IRomHandle {
    List<Rom> findAll();
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void deletePhone(ListTransfer deteleList);
    boolean checkUp(int name);
    int selectByName(String name);
    String[] transListToArr();
    Rom findById(int id);
}
