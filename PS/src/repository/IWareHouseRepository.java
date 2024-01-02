package repository;

import model.WareHouse;

import java.util.List;

public interface IWareHouseRepository {
    WareHouse findById(int id);
    List<WareHouse> findAll();
}
