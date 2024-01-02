package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.List;

public class OriginHandle implements IOriginHandle{
    private ISocketClient soc= new Log_In();
    @Override
    public List<Origin> findAll() {
        return null;
    }

    @Override
    public void editPhone(ListTransfer editPhone) {

    }

    @Override
    public void  addPhone(ListTransfer addList) {

    }

    @Override
    public void deletePhone(ListTransfer deteleList) {

    }

    @Override
    public boolean checkUp(String name) {
        return false;
    }
    @Override
    public String[] transListToArr() {
        List<Origin> list= soc.findAll().getListOr();
        String [] arr= new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i).getName();
        }
        return  arr;
    }

    @Override
    public Origin findById(int id) {
        Origin or= new Origin();
        for(Origin orF: soc.findAll().getListOr()){
            if(orF.getId()==id){
                or= orF;
            }
        }
        return or;
    }
}
