package ru.fijirash.pp.spring_security.dao;



import ru.fijirash.pp.spring_security.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void addUser(User user);
    void removeUser(int id);
    User getUser(int id);
    User getUserByName(String name);
    void updateUser(int id, User user);
}
