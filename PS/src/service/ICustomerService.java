package service;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    List<Customer> searchByName(String name);
    void editCus(Customer customer);
    void addCus(Customer customer);
    void deleteCustomer(int id);
    String selectName(int id);
}
