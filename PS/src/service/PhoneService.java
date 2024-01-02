package service;

import model.Phone;
import repository.IPhoneRepository;
import repository.PhoneRepository;

import java.util.List;

public class PhoneService implements IPhoneService{
    private IPhoneRepository repo = new PhoneRepository();
    @Override
    public List<Phone> findAll() {
        return repo.findAll();
    }

    @Override
    public void addNew(Phone phone) {
repo.addNew(phone);
    }

    @Override
    public void editPhone(Phone phone) {
  repo.editPhone(phone);
    }

    @Override
    public void deletePhone(int id) {
repo.deletePhone(id);
    }

    @Override
    public List<Phone> searchPhone(String url) {
        return null;
    }

    @Override
    public int selectId() {
        return repo.selectId();
    }

    @Override
    public Phone findById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Phone> search(String url) {
        return repo.searchPhone(url);
    }
}
