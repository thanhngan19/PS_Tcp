package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.List;

public class RomHandle implements IRomHandle{
    ISocketClient soc= new Log_In();
    @Override
    public List<Rom> findAll() {
        return soc.findAll().getListRom();
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
soc.listEdit(deteleList);
    }

    @Override
    public boolean checkUp(int name){
        boolean check= true;
        for(Rom item: soc.findAll().getListRom()){
            if(item.getName().equals(name)){
                check= false;
            }
        }
        return check;
    }

    @Override
    public int selectByName(String name) {
        int result =0;
        for(Rom cl: soc.findAll().getListRom()){
            if(cl.getName().equals(name)){
                result= cl.getId();
            }
        }
        return result;
    }
    @Override
    public String[] transListToArr() {
        List<Rom> list= soc.findAll().getListRom();
        String [] arr= new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i).getName();
        }
        return  arr;
    }

    @Override
    public Rom findById(int id) {
        Rom ri= new Rom();
        for(Rom r : soc.findAll().getListRom()){
            if(r.getId()==id){
                r=ri;
            }
        }
        return ri;
    }
}
