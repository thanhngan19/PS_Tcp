package service;

import model.Rom;
import repository.IRomRepository;
import repository.RomRepository;

import java.util.List;

public class RomService implements IRomService{
    private IRomRepository repo= new RomRepository();
    @Override
    public Rom findById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Rom> findAll() {
        return repo.findAll();
    }

    @Override
    public void editOr(Rom or) {
        repo.editOr(or);
    }

    @Override
    public void addOr(Rom or) {
  repo.addOr(or);
    }

    @Override
    public void deleteOr(int id) {
repo.deleteOr(id);
    }
}
