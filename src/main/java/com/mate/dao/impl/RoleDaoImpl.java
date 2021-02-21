package com.mate.dao.impl;

import com.mate.dao.RoleDao;
import com.mate.exception.DataProcessingException;
import com.mate.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory factory;

    public RoleDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Role add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            role.setName(role.getName().toUpperCase());
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert role " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Role> getByName(String name) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM Role WHERE name LIKE :name", Role.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("User with name " + name + " not found", e);
        }
    }
}
