package service;

import model.Os;

import java.util.List;

public interface IOsService {
  Os findById(int id);
  List<Os> findAll();
  void editOr(Os or);
  void addOr(Os or);
  void deleteOr(int id);
}
