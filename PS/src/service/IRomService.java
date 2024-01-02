package service;

import model.Os;
import model.Rom;

import java.util.List;

public interface IRomService {
    Rom findById(int id);
    List<Rom> findAll();
    void editOr(Rom or);
    void addOr(Rom or);
    void deleteOr(int id);

}
