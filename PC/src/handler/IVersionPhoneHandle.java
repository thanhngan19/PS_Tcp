package handler;

import model.ListTransfer;
import model.VersionPhone;

import java.util.List;

public interface IVersionPhoneHandle {
    List<VersionPhone> findAll();
    List<VersionPhone> findByMaSP(int msp);
    VersionPhone findById(int id);
    boolean checkUp(VersionPhone name);
    void editVer(ListTransfer list);
    void deleteVer(ListTransfer id);
    void addVer(ListTransfer list);
}
