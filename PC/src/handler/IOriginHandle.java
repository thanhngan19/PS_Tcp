package handler;

import model.*;

import java.util.List;

public interface IOriginHandle {
    List<Origin> findAll();
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void deletePhone(ListTransfer deteleList);
    boolean checkUp(String name);
    String[] transListToArr();
    Origin findById(int id);
}
