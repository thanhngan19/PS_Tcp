package handler;

import model.Customer;
import model.ListTransfer;

import java.util.List;

public interface ICustomerHandle {

    List<Customer>findAll();
    Customer findById(int id);
    String[] transListToArr();
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void deletePhone(ListTransfer deteleList);
}
