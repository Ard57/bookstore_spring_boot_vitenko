package com.vitenko.bookstore.data.repository.impl;

import com.vitenko.bookstore.data.entity.User;
import com.vitenko.bookstore.data.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.vitenko.bookstore.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
        entityManager.detach(user);
        return user;
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " was not found.");
        }
        entityManager.detach(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = entityManager.createQuery("from User ", User.class).getResultList();
        return users;
    }

    @Override
    public boolean delete(Long id) {
        boolean deleted = false;
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        TypedQuery<User> query = entityManager.createQuery("from User where email=:email", User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            throw new UserNotFoundException("User with email " + email + " was not found.");
        }
    }

    @Override
    public List<User> findByLastName(String lastName) {
        TypedQuery<User> query = entityManager.createQuery("from User where lastName=:lastName", User.class);
        query.setParameter("lastName", lastName);
        List<User> users = query.getResultList();
        return users;
    }
}
