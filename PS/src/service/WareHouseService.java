package service;

import model.WareHouse;
import repository.IWareHouseRepository;
import repository.WareHouseRepository;

import java.util.List;

public class WareHouseService implements IWareHouseService{
    IWareHouseRepository repo = new WareHouseRepository();
    @Override
    public WareHouse findById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<WareHouse> findAll() {
        return repo.findAll();
    }
}
