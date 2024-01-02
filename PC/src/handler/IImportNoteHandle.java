package handler;

import model.ImportNote;
import model.ImportNoteDetail;

import java.util.List;

public interface IImportNoteHandle {
    List<ImportNote> findAll();
   long getTongTien(List<ImportNoteDetail> list);


}
