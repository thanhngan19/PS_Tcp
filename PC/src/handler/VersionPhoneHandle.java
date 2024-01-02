package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class VersionPhoneHandle implements IVersionPhoneHandle {
    ISocketClient soc= new Log_In();
    @Override
    public List<VersionPhone> findAll() {
        return soc.findAll().getListVer();
    }

    @Override
    public List<VersionPhone> findByMaSP(int msp) {
        List<VersionPhone> list = new ArrayList<VersionPhone>();
        for(VersionPhone ver : soc.findAll().getListVer()){
        if(ver.getPhone().getId()==msp){
            System.out.println(ver.getPhone().getId());
            list.add(ver);
        }
        }
        return list;
    }

    @Override
    public VersionPhone findById(int id) {
        VersionPhone item= new VersionPhone();
        System.out.println(findAll().size());
       for(VersionPhone ver : findAll()){
           if(ver.getId()==id){
            item= ver;
           }
       }
       return item;
    }

    @Override
    public boolean checkUp(VersionPhone name) {
        boolean checkSize=false;
        for(VersionPhone check : findAll()){
            if(!check.equals(name)){
                checkSize= true;
            }
        }
        return checkSize;
    }

    @Override
    public void editVer(ListTransfer list) {

    }

    @Override
    public void deleteVer(ListTransfer id) {

    }

    @Override
    public void addVer(ListTransfer list) {

    }


}
