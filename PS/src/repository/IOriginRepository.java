package repository;

import model.Customer;
import model.Origin;

import java.util.List;

public interface IOriginRepository {
    Origin findById(int id);
    List<Origin> findAll();
    void editOr(Origin or);
    void addOr(Origin or);
    void deleteOr(int id);

}
