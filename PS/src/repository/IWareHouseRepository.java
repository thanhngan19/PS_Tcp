package repository;

import model.Supplier;
import model.WareHouse;

import java.util.List;

public interface IWareHouseRepository {
    WareHouse findById(int id);
    List<WareHouse> findAll();
    void editCus(WareHouse customer);
    void addCus(WareHouse customer);
    void deleteCustomer(int id);
}
