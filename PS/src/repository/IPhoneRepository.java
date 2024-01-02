package repository;

import model.Phone;

import java.util.List;

public interface IPhoneRepository {
    List<Phone> findAll();
    void addNew(Phone phone);
    void editPhone(Phone phone);
    void deletePhone(int id);
    List<Phone> searchPhone(String url);
    Phone findById(int id);
    int selectId();

}
