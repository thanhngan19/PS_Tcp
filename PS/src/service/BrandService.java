package service;

import model.Brand;
import model.Os;
import repository.BrandRepository;
import repository.IBrandRepository;

import java.util.List;

public class BrandService implements IBrandService{
    IBrandRepository repo= new BrandRepository();
    @Override
    public Brand findById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<Brand> findAll() {
        return repo.findAll();
    }

    @Override
    public void editOr(Brand or) {
        repo.editBrand(or);
    }

    @Override
    public void addOr(Brand or) {
  repo.addBrand(or);
    }

    @Override
    public void deleteOr(int id) {
repo.deleteBrand(id);
    }
}
