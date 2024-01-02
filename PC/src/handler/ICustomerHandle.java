package handler;

import model.Customer;

import java.util.List;

public interface ICustomerHandle {

    List<Customer>findAll();
    Customer findById(int id);
    String[] transListToArr();
}
