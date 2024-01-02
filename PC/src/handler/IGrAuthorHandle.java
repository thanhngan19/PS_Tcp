package handler;

import model.GrAuthor;

import java.util.ArrayList;
import java.util.List;

public interface IGrAuthorHandle {
    ArrayList<GrAuthor> search(String text);
    List<GrAuthor> findAll();
    GrAuthor tranfer(int id);
    boolean checkPermisson(int id, String chucnang, String hanhdong);
}
