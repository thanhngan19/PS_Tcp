package service;

import model.Phone;

import java.util.List;

public interface IPhoneService {
    List<Phone> findAll();
    void addNew(Phone phone);
    void editPhone(Phone phone);
    void deletePhone(int id);
    List<Phone> searchPhone(String url);
    int selectId();
    Phone findById(int id);
    List<Phone> search(String url);
}
