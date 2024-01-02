package repository;

import model.ColorBr;

import java.util.List;

public interface IColorRepository {
    ColorBr findById(int id);
    List<ColorBr> findAll();
    void editColor(ColorBr color);
    void addColor(ColorBr color);
    void deleteColor(int id);

}
