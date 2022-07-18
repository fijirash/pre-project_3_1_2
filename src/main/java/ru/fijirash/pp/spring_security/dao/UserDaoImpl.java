package ru.fijirash.pp.spring_security.dao;


import org.springframework.stereotype.Repository;
import ru.fijirash.pp.spring_security.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery(
                "select user from User user", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        try {
            return entityManager.createQuery("select u from User u where u.name =: name", User.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            System.out.printf("User %s not found", name);
        }
        return null;
    }

    @Override
    public void updateUser(int id, User newUser) {
        entityManager.merge(newUser);
    }
}
