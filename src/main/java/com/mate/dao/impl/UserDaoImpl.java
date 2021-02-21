package com.mate.dao.impl;

import com.mate.dao.UserDao;
import com.mate.exception.DataProcessingException;
import com.mate.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory factory;

    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> get(Long id) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM User u JOIN FETCH u.roles WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("User with id " + id + " not found", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM User u JOIN FETCH u.roles WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("User with email " + email + " not found", e);
        }
    }
}
