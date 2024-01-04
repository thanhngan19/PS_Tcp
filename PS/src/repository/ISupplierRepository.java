package repository;

import model.Customer;
import model.Supplier;

import java.util.List;

public interface ISupplierRepository {
    List<Supplier> findAll();
    void editCus(Supplier customer);
    void addCus(Supplier customer);
    void deleteCustomer(int id);
}
