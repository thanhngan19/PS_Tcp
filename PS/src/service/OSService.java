package service;

import model.Os;
import repository.IOsRepository;
import repository.OsRepository;

import java.util.List;

public class OSService implements IOsService {
    IOsRepository repo= new OsRepository();
    @Override
    public Os findById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Os> findAll() {
        return repo.findAll();
    }

    @Override
    public void editOr(Os or) {
        repo.editOr(or);
    }

    @Override
    public void addOr(Os or) {
repo.addOr(or);
    }

    @Override
    public void deleteOr(int id) {
repo.deleteOr(id);
    }
}
