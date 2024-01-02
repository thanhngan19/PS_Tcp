package service;

import model.GrAuthor;

import java.util.List;

public interface IGrAuthorService {
    List<GrAuthor> findAll();
    void addNew(GrAuthor author);
    void editAuthor(GrAuthor author);
    void deleteAuthor(int id);
    GrAuthor selectById(int id);
}
