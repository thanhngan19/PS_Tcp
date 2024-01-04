package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.List;

public class OsHandle implements IOsHandle{
    private ISocketClient soc= new Log_In();
    @Override
    public List<Os> findAll() {
        return soc.findAll().getListOs();
    }

    @Override
    public void editPhone(ListTransfer editPhone) {

     soc.listEdit(editPhone);
    }

    @Override
    public void addPhone(ListTransfer addList) {
    soc.listAdd(addList);

    }

    @Override
    public void deletePhone(ListTransfer deteleList) {

        soc.listDelete(deteleList);
    }

    @Override
    public boolean checkUp(String name) {
        boolean check= true;
        for(Os item: soc.findAll().getListOs()){
            if(item.getName().equals(name)){
                check= false;
            }
        }
        return check;
    }

    @Override
    public String[] transListToArr() {
        List<Os> list= soc.findAll().getListOs();
        String [] arr= new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i).getName();
        }
        return  arr;
    }

    @Override
    public Os findById(int id) {
        Os or= new Os();
        for(Os orF: soc.findAll().getListOs()){
            if(orF.getId()==id){
                or= orF;
            }
        }
        return or;
    }
}
