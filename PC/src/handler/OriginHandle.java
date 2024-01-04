package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.List;

public class OriginHandle implements IOriginHandle{
    private ISocketClient soc= new Log_In();
    @Override
    public List<Origin> findAll() {
        return soc.findAll().getListOr();
    }

    @Override
    public void editPhone(ListTransfer editPhone) {
    soc.listEdit(editPhone);
    }

    @Override
    public void  addPhone(ListTransfer addList) {
soc.listAdd(addList);
    }

    @Override
    public void deletePhone(ListTransfer deteleList) {
soc.listDelete(deteleList);
    }

    @Override
    public boolean checkUp(String name) {

        boolean check= true;
        for(Origin item: soc.findAll().getListOr()){
            if(item.getName().equals(name)){
                check= false;
            }
        }
        return check;
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
