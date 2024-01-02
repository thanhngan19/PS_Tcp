package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.List;

public class RamHandle implements IRamHandle{
    ISocketClient soc= new Log_In();
    @Override
    public List<Ram> findAll() {
        return soc.findAll().getListRam();
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
    public boolean checkUp(int name) {
        boolean checkSize=false;
        for(Ram check : findAll()){
            if(!check.getName().equals(name)){
                checkSize= true;
            }
        }
        return checkSize;
    }

    @Override
    public int selectByName(String name) {
        int result =0;
        for(Ram cl: soc.findAll().getListRam()){
            if(cl.getName().equals(name)){
                result= cl.getId();
            }
        }
        return result;
    }
    @Override
    public String[] transListToArr() {
        List<Ram> list= soc.findAll().getListRam();
        String [] arr= new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i).getName();
        }
        return  arr;
    }

    @Override
    public Ram findById(int id) {
        Ram ramF = new Ram();
        for(Ram ramFind: soc.findAll().getListRam()){
            if(ramFind.getId()==id){
                ramF= ramFind;
            }
        }
        return ramF;
    }
}
