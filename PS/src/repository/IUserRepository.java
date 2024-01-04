package repository;

import model.User;

import java.util.List;

public interface IUserRepository {
    List<User> list();
    void editUser(User user);
    List<User> selectUser();
    int getUser(String name);
    void setStatusAccount(int id);
    void setStatusAccount2(int id);
}
