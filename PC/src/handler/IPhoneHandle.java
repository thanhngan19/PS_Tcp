package handler;

import model.*;

import java.util.List;

public interface IPhoneHandle {
    List<Phone> findAll();
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void deletePhone(ListTransfer deteleList);
    List<Phone> searchPhone(String search);
    Phone searchById(int id);
}
