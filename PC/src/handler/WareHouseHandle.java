package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.WareHouse;

import java.util.List;

public class WareHouseHandle implements IWareHouseHandle{
    private ISocketClient soc= new Log_In();
    @Override
    public List<WareHouse> findAll() {
        return soc.findAll().getListWare();
    }

    @Override
    public String[] transListToArr() {
        List<WareHouse> list= soc.findAll().getListWare();
        String [] arr= new String[list.size()];
         for(int i=0;i<list.size();i++){
             arr[i]=list.get(i).getName();

         }
        return  arr;
    }

    @Override
    public WareHouse findById(int id) {
        WareHouse b= new WareHouse();
        for(WareHouse br : soc.findAll().getListWare()){
            if(br.getId()==id){
                b= br;
            }
        }
        return b;
    }
}
