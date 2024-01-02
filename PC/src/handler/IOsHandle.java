package handler;

import model.*;

import java.util.List;

public interface IOsHandle {
    List<Os> findAll();
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void deletePhone(ListTransfer deteleList);
    boolean checkUp(String name);
    String [] transListToArr();
    Os findById(int id);
}
