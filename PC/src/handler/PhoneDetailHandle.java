package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.PhoneDetail;

import java.util.List;

public class PhoneDetailHandle implements IPhoneDetailHandle{
    ISocketClient soc= new Log_In();
    @Override
    public List<PhoneDetail> findAll() {
        return soc.findAll().getPhoneDetail();
    }

}
