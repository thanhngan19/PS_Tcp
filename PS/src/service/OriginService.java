package service;

import model.Origin;
import repository.IOriginRepository;
import repository.OriginRepository;

import java.util.List;

public class OriginService implements IOriginService{
    IOriginRepository repo= new OriginRepository();
    @Override
    public Origin findById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Origin> findAll() {
        return repo.findAll();
    }

    @Override
    public void editOr(Origin or) {
        repo.editOr(or);
    }

    @Override
    public void addOr(Origin or) {
   repo.addOr(or);
    }

    @Override
    public void deleteOr(int id) {
repo.deleteOr(id);
    }
}
