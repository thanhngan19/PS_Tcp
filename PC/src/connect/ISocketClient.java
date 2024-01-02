package connect;

import model.*;

import java.util.List;

public interface ISocketClient {

    ListTranmission findAll();
    List<Phone> listPhone();
    User findUser(String userName);
   <T> List<T> listSearch(ListTransfer search);
   void listEdit(ListTransfer editList);
   void listAdd(ListTransfer addList);
   void listDelete(ListTransfer deList);
   List<Phone> listSearch(String url);


}
