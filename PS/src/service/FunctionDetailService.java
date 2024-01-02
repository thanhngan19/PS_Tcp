package service;

import model.FunctionDetail;
import repository.FunctionDetailRepository;
import repository.IFunctionDetailRepository;

import java.util.List;

public class FunctionDetailService implements IFunctionDetailService{
    private IFunctionDetailRepository repo= new FunctionDetailRepository();
    @Override
    public List<FunctionDetail> findAll() {
        return repo.findAll();
    }
}
