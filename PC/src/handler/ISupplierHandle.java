package handler;

import model.Supplier;

import java.util.List;

public interface ISupplierHandle {
    String[] transListToArr();
    List<Supplier> findAll();
    Supplier findById(int id);
}
