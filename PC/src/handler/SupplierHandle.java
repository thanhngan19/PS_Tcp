package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.Supplier;

import java.util.List;

public class SupplierHandle implements ISupplierHandle{
    private ISocketClient soc= new Log_In();
    @Override
    public String[] transListToArr() {
        List<Supplier> list= soc.findAll().getSup();
        String [] arr= new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i).getName();
        }
        return  arr;
    }

    @Override
    public List<Supplier> findAll() {
        return soc.findAll().getSup();
    }

    @Override
    public Supplier findById(int id) {
        Supplier supFind= new Supplier();
        for(Supplier item : soc.findAll().getSup()){
            if(item.getId() == id){
                supFind= item;
            }
        }
        return supFind;
    }
}
