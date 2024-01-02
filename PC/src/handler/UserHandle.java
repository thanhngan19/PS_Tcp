package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.List;

public class UserHandle implements IUserHandle{
    private ISocketClient soc= new Log_In();

    @Override
    public List<User> findAll() {
        return soc.findAll().getUser();
    }

    @Override
    public void editUser(ListTransfer editList) {
         soc.listEdit(editList);
    }

    @Override
    public void addNew(ListTransfer addList) {
         soc.listAdd(addList);
    }

    @Override
    public void deleteUser(ListTransfer deteleList) {
         soc.listDelete(deteleList);
    }



}
