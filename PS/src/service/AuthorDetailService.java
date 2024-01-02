package service;

import model.AuthorDetail;
import repository.AuthorDetailRepository;
import repository.IAuthorDetailRepository;

import java.util.List;

public class AuthorDetailService implements IAuthorDetailService{
    private IAuthorDetailRepository repo= new AuthorDetailRepository();
    @Override
    public List<AuthorDetail> findAll() {
        return repo.findAll();
    }
}
