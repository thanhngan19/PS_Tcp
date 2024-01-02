package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.List;

public class ColorHandle implements IColorHandle{
    ISocketClient soc= new Log_In();
    @Override
    public List<ColorBr> findAll() {
        return soc.findAll().getListColor();
    }

    @Override
    public void editPhone(ListTransfer editPhone) {

    }

    @Override
    public void addPhone(ListTransfer addList) {


    }

    @Override
    public void deletePhone(ListTransfer deteleList) {

    }

    @Override
    public boolean checkUp(String name) {
        return false;
    }

    @Override
    public String selectByName(int id) {
        String result =null;
        for(ColorBr cl: soc.findAll().getListColor()){
            if(cl.getId()==id){
                result= cl.getName();
            }
        }
        return result;
    }
    @Override
    public String[] transListToArr() {
        List<ColorBr> list= soc.findAll().getListColor();
        String [] arr= new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i).getName();
        }
        return  arr;
    }

    @Override
    public ColorBr findById(int id) {
        ColorBr color= new ColorBr();
        for(ColorBr col:soc.findAll().getListColor()){
            if(col.getId()==id){
                color=col;
            }
        }
        return color;
    }
}
