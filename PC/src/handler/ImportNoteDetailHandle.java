package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.ImportNoteDetail;

public class ImportNoteDetailHandle implements IImportNoteDetailHandle{
    private ISocketClient soc= new Log_In();


    @Override
    public boolean findCT(ImportNoteDetail detail) {
        boolean check = false;
        for(ImportNoteDetail item : soc.findAll().getImNoteDe()){
            if(item.equals(detail)){
                check = true;
            }
        }
        return check;
    }

    @Override
    public boolean checkExist(int mapb) {
        boolean check = false;
        for(ImportNoteDetail item : soc.findAll().getImNoteDe()){
            if(item.getVer().getId()== mapb){
                check = true;
            }
        }
        return check;
    }
}
