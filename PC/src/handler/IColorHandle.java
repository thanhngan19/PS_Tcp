package handler;
import model.ColorBr;
import model.ListTransfer;

import java.util.List;

public interface IColorHandle {
    List<ColorBr> findAll();
    void editPhone(ListTransfer editPhone);
    void addPhone(ListTransfer addList);
    void  deletePhone(ListTransfer deteleList);
    boolean checkUp(String name);
    String selectByName(int id);
    String[] transListToArr();
    ColorBr findById(int id);
}
