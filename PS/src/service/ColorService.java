package service;

import model.ColorBr;
import repository.ColorRepository;
import repository.IColorRepository;

import java.util.List;

public class ColorService implements IColorService{
    private IColorRepository repo= new ColorRepository();
    @Override
    public ColorBr findById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<ColorBr> findAll() {
        return repo.findAll();
    }

    @Override
    public void editOr(ColorBr or) {
        repo.editColor(or);
    }

    @Override
    public void addOr(ColorBr or) {
repo.addColor(or);
    }

    @Override
    public void deleteOr(int id) {
repo.deleteColor(id);
    }
}
