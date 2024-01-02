package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.Customer;

import java.util.List;

public class CustomerHandle implements ICustomerHandle{
    private ISocketClient soc= new Log_In();



    @Override
    public List<Customer> findAll() {
        return soc.findAll().getCustomerList();
    }

    @Override
    public Customer findById(int id) {
        Customer cusFind= null;
        for(Customer cus: soc.findAll().getCustomerList()){
            if(cus.getId() == id){
                cusFind=cus;

            }
        }
        return cusFind;
    }

    @Override
    public String[] transListToArr() {
        List<Customer> list= soc.findAll().getCustomerList();
        String [] arr= new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i).getName();
        }
        return  arr;
    }

}
