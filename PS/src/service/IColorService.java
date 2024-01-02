package service;

import model.ColorBr;
import model.Os;

import java.util.List;

public interface IColorService {
    ColorBr findById(int id);
    List<ColorBr> findAll();
    void editOr(ColorBr or);
    void addOr(ColorBr or);
    void deleteOr(int id);
}
