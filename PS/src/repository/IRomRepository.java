package repository;

import model.Origin;
import model.Rom;

import java.util.List;

public interface IRomRepository {
    Rom findById(int id);
    List<Rom> findAll();
    void editOr(Rom or);
    void addOr(Rom or);
    void deleteOr(int id);
}
