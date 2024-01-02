package handler;

import model.*;

import java.util.List;

public interface IBrandHandle {
    List<Brand> findAll();
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void deletePhone(ListTransfer deteleList);
    boolean checkUp(String name);
    String [] transListToArr();
    Brand findById(int id);
}
