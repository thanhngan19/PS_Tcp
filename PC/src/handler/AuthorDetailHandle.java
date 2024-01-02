package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.AuthorDetail;

import java.util.ArrayList;
import java.util.List;

public class AuthorDetailHandle implements IAuthorDetailHandle {
    ISocketClient soc= new Log_In();
    @Override
    public List<AuthorDetail> findById(int id) {
        List<AuthorDetail> list = new ArrayList<AuthorDetail>();
        for(AuthorDetail auth: soc.findAll().getAuthorDetailList()){
            if(auth.getId() == id){
                list.add(auth);
            }
        }
        return list;
    }
}
