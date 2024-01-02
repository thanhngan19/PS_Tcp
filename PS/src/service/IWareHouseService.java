package service;

import model.WareHouse;

import java.util.List;

public interface IWareHouseService {
   WareHouse findById(int id);
   List<WareHouse> findAll();
}
