package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.ImportNote;
import model.ImportNoteDetail;

import java.util.List;

public class ImportNoteHandle implements IImportNoteHandle{
    private ISocketClient soc= new Log_In();
    @Override
    public List<ImportNote> findAll() {
        return null;
    }

    @Override
    public long getTongTien(List<ImportNoteDetail> list) {
        long result = 0;
        for (ImportNoteDetail item : list) {
            result += item.getPrice() * item.getQuantity();
        }
        return result;
    }


}
