package com.vitenko.bookstore.data.repository.impl;

import com.vitenko.bookstore.data.entity.Order;
import com.vitenko.bookstore.data.entity.User;
import com.vitenko.bookstore.data.repository.OrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.vitenko.bookstore.exception.order.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Order save(Order order) {
        if (order.getId() != null) {
            entityManager.merge(order);
        } else {
            entityManager.persist(order);
        }
        entityManager.detach(order);
        return order;
    }

    @Override
    public Order findById(Long id) throws OrderNotFoundException{
        Order order = entityManager.find(Order.class, id);
        if (order == null) {
            throw new OrderNotFoundException("Order with id " + id + " was not found");
        }
        entityManager.detach(order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = entityManager.createQuery("from Order ", Order.class).getResultList();
        orders.forEach(entityManager::detach);
        return orders;
    }

    @Override
    public boolean delete(Long id) {
        boolean deleted = false;
        Order order = entityManager.find(Order.class, id);
        if (order != null) {
            entityManager.remove(order);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public List<Order> findByUserEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("from User where email=:email", User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if (users.size() > 0) {
            User user = users.get(0);
            TypedQuery<Order> orderQuery =
                    entityManager.createQuery("from Order where user=:user", Order.class);
            orderQuery.setParameter("user", user);
            return orderQuery.getResultList();
        } else {
            return null;
        }
    }
}
