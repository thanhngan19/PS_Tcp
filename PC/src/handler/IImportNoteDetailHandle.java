package handler;

import model.ImportNoteDetail;

public interface IImportNoteDetailHandle {
    boolean findCT(ImportNoteDetail detail);
    boolean checkExist(int mapb);
}
