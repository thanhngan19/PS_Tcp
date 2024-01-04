package service;

import model.VersionPhone;

import java.util.List;

public interface IVersionPhoneService {
    List<VersionPhone> findAll();
    void addNew(VersionPhone ver);
    void editVer(VersionPhone ver);
    void delete(int ver);
}
