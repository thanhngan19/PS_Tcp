package service;

import model.Os;
import model.Ram;
import repository.IRamRepository;
import repository.RamRepository;

import java.util.List;

public class RamService implements IRamService{
    private IRamRepository repo= new RamRepository();
    @Override
    public Ram findById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Ram> findAll() {
        return repo.findAll();
    }

    @Override
    public void editOr(Ram or) {
        repo.editOr(or);
    }

    @Override
    public void addOr(Ram or) {
      repo.addOr(or);
    }

    @Override
    public void deleteOr(int id) {
    repo.deleteOr(id);
    }
}
