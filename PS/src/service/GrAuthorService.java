package service;

import model.GrAuthor;
import repository.GrAuthorRepository;
import repository.IGrAuthorRepository;

import java.util.List;

public class GrAuthorService implements IGrAuthorService{
    private IGrAuthorRepository repo= new GrAuthorRepository();
    @Override
    public List<GrAuthor> findAll() {
        return repo.findAll();
    }

    @Override
    public void addNew(GrAuthor author) {
   repo.addNew(author);
    }

    @Override
    public void editAuthor(GrAuthor author) {
   repo.editAuthor(author);
    }

    @Override
    public void deleteAuthor(int id) {
repo.deleteAuthor(id);
    }

    @Override
    public GrAuthor selectById(int id) {
        return repo.selectById(id);
    }
}
