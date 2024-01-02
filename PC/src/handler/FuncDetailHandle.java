package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.FunctionDetail;

import java.util.List;

public class FuncDetailHandle implements IFuncDetailHandle{
    ISocketClient soc = new Log_In();
    @Override
    public List<FunctionDetail> findAll() {
        return soc.findAll().getfDetail();
    }
}
