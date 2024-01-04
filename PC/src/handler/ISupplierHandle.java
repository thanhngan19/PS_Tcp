package handler;

import model.ListTransfer;
import model.Supplier;

import java.util.List;

public interface ISupplierHandle {
    String[] transListToArr();
    List<Supplier> findAll();
    Supplier findById(int id);
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void deletePhone(ListTransfer deteleList);
}
