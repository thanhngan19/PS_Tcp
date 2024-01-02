package repository;

import model.AuthorDetail;

import java.util.List;

public interface IAuthorDetailRepository {
    List<AuthorDetail> findAll();
}
