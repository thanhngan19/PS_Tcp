package service;

import model.Customer;
import repository.CustomerRepository;
import repository.ICustomerRepository;

import java.util.List;

public class CustomerService implements ICustomerService{
    private ICustomerRepository repo = new CustomerRepository();
    @Override
    public List<Customer> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Customer> searchByName(String name) {
        return repo.searchByName(name);
    }

    @Override
    public void editCus(Customer customer) {
     repo.editCus(customer);
    }

    @Override
    public void addCus(Customer customer) {
    repo.addCus(customer);
    }

    @Override
    public void deleteCustomer(int id) {
    repo.deleteCustomer(id);
    }

    @Override
    public String selectName(int id) {
        return repo.selectName(id);
    }

}
