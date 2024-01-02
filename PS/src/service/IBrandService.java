package service;

import model.Brand;
import model.Os;

import java.util.List;

public interface IBrandService {
    Brand findById(int id);
    List<Brand> findAll();
    void editOr(Brand or);
    void addOr(Brand or);
    void deleteOr(int id);
}
