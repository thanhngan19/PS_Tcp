package repository;

import model.Brand;

import java.util.List;

public interface IBrandRepository {
  Brand findById(int id);
  List<Brand> findAll();
  void editBrand(Brand br);
  void addBrand(Brand br);
  void deleteBrand(int id);
}
