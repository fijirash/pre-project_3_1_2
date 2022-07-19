package ru.fijirash.pp.spring_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fijirash.pp.spring_security.dao.RoleDao;
import ru.fijirash.pp.spring_security.dao.UserDao;
import ru.fijirash.pp.spring_security.models.User;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleDao.getRoleById(2)));
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        if (!user.getPassword().equals(userDao.getUser(id).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.updateUser(id, user);
    }
}
