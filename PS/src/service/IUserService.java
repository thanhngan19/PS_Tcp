package service;

import model.User;

import java.util.List;

public interface IUserService {
    List<User> list();
    void editUser(User user);
    List<User> selectUser();
    int getUser(String name);
}
