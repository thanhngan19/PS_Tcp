package repository;

import model.GrAuthor;

import java.util.List;

public interface IGrAuthorRepository {
    List<GrAuthor> findAll();
    void addNew(GrAuthor author);
    void editAuthor(GrAuthor author);
    void deleteAuthor(int id);
    GrAuthor selectById(int id);
}
