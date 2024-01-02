package repository;

import model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    List<Customer> searchByName(String name);
    void editCus(Customer customer);
    void addCus(Customer customer);
    void deleteCustomer(int id);
    String selectName(int id);
}
