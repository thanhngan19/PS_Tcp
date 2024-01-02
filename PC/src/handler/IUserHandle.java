package handler;

import model.*;

import java.util.List;

public interface IUserHandle {
    List<User> findAll();
    void editUser(ListTransfer editUser);
    void addNew(ListTransfer addList);
    void deleteUser(ListTransfer deteleList);


}
