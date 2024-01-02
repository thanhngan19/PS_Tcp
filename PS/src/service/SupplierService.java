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
}
