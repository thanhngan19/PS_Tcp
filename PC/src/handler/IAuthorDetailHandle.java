package handler;

import model.AuthorDetail;

import java.util.List;

public interface IAuthorDetailHandle {
    List<AuthorDetail> findById(int id);

}
