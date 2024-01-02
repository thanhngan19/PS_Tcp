package repository;

import model.VersionPhone;

import java.util.List;

public interface IVersionPhoneRepository {
    void addNew(VersionPhone ver);
  List<VersionPhone> findAll();
  void editVer(VersionPhone ver);
  void addVer(VersionPhone ver);
  void delete(int ver);
}
