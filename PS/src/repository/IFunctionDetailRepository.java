package repository;

import model.FunctionDetail;

import java.util.List;

public interface IFunctionDetailRepository {
    List<FunctionDetail> findAll();
}
