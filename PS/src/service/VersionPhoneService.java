package service;

import model.VersionPhone;
import repository.IVersionPhoneRepository;
import repository.VersionPhoneRepository;

import java.util.List;

public class VersionPhoneService implements IVersionPhoneService {
    private IVersionPhoneRepository repo= new VersionPhoneRepository();

    @Override
    public List<VersionPhone> findAll() {
        return repo.findAll();
    }

    @Override
    public void addNew(VersionPhone ver) {
        repo.addNew(ver);
    }

    @Override
    public void editVer(VersionPhone ver) {
        repo.editVer(ver);
    }

    @Override
    public void addVer(VersionPhone ver) {
   repo.addVer(ver);
    }

    @Override
    public void delete(int ver) {
repo.delete(ver);
    }


}
