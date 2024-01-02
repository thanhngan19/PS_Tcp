package repository;

import model.Origin;
import model.Ram;

import java.util.List;

public interface IRamRepository {
    Ram findById(int id);
    List<Ram> findAll();
    void editOr(Ram or);
    void addOr(Ram or);
    void deleteOr(int id);

}
