package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class PhoneHandle implements IPhoneHandle{
    ISocketClient soc = new Log_In();
    @Override
    public List<Phone> findAll() {
        return soc.findAll().getListPhone();
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
    public void  deletePhone(ListTransfer deteleList) {

         soc.listDelete(deteleList);
    }

    @Override
    public List<Phone> searchPhone(String search) {
        List<Phone> list = new ArrayList<Phone>();
        search = search.toLowerCase();
        for (Phone i : soc.findAll().getListPhone()) {
            if (Integer.toString(i.getId()).toLowerCase().contains(search) || i.getName().toLowerCase().contains(search)) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public Phone searchById(int id) {
        Phone phoneObj= null;
        for(Phone phone : soc.findAll().getListPhone()){
            if(phone.getId()==id){
                phoneObj= phone;
            }
        }
        return phoneObj;
    }

    @Override
    public List<Phone> selectPhoneByWare(int id) {
        List<Phone> phoneList = new ArrayList<>();
        for(Phone phone : findAll()){
            if(phone.getWareHouse().getId()== id){
                phoneList.add(phone);
            }
        }
        return phoneList;
    }

}
