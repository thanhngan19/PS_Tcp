package service;

import model.Os;
import model.Ram;

import java.util.List;

public interface IRamService {
    Ram findById(int id);
    List<Ram> findAll();
    void editOr(Ram or);
    void addOr(Ram or);
    void deleteOr(int id);
}
