package handler;

import GUI.Log_In;
import connect.ISocketClient;
import model.AuthorDetail;
import model.GrAuthor;

import java.util.ArrayList;
import java.util.List;

public class GrAuthorHandle implements IGrAuthorHandle {
    private ISocketClient soc = new Log_In();
    List<GrAuthor> list = soc.findAll().getGrAuthor();
    private IAuthorDetailHandle detailHandle = new AuthorDetailHandle();

    @Override
    public ArrayList<GrAuthor> search(String text) {
        ArrayList<GrAuthor> result = new ArrayList<>();
        for (GrAuthor i : list) {
            if (Integer.toString(i.getId()).contains(text) || i.getName().toLowerCase().contains(text.toLowerCase())) {
                result.add(i);
            }
        }
        return result;
    }

    @Override
    public List<GrAuthor> findAll() {
        return soc.findAll().getGrAuthor();
    }

    @Override
    public GrAuthor tranfer(int id) {
        GrAuthor tranfer = null;
        List<GrAuthor> list = soc.findAll().getGrAuthor();
        for (GrAuthor author : list) {
            if (author.getId() == id) {
                tranfer = author;
            }
        }
        return tranfer;
    }

    @Override
    public boolean checkPermisson(int id, String chucnang, String hanhdong) {
        List<AuthorDetail> ctquyen = detailHandle.findById(id);
        boolean check = false;
        int i = 0;
        while (i < ctquyen.size() && check == false) {
            if (ctquyen.get(i).getName().equals(chucnang) && ctquyen.get(i).getAction().equals(hanhdong)) {
                check = true;
            } else {
                i++;
            }
        }
        return check;
    }
}

