package service;

import model.Supplier;
import repository.ISupplierRepository;
import repository.SupplierRepository;

import java.util.List;

public class SupplierService implements ISupplierService{
    private ISupplierRepository repo= new SupplierRepository();
    @Override
    public List<Supplier> findAll() {
        return repo.findAll();
    }

    @Override
    public void editCus(Supplier customer) {
        repo.editCus(customer);
    }

    @Override
    public void addCus(Supplier customer) {
repo.addCus(customer);
    }

    @Override
    public void deleteCustomer(int id) {
repo.deleteCustomer(id);
    }
}
