package service;

import model.Supplier;

import java.util.List;

public interface ISupplierService {
    List<Supplier> findAll();
    void editCus(Supplier customer);
    void addCus(Supplier customer);
    void deleteCustomer(int id);
}
