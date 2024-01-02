package repository;

import model.Supplier;

import java.util.List;

public interface ISupplierRepository {
    List<Supplier> findAll();
}
