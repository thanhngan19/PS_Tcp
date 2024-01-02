package service;

import model.Origin;
import model.Os;

import java.util.List;

public interface IOriginService {
  Origin findById(int id);
  List<Origin> findAll();
  void editOr(Origin or);
  void addOr(Origin or);
  void deleteOr(int id);
}
