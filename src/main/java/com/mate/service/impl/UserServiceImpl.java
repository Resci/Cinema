package com.mate.service.impl;

import com.mate.dao.UserDao;
import com.mate.lib.Inject;
import com.mate.lib.Service;
import com.mate.model.User;
import com.mate.service.UserService;
import com.mate.util.PasswordUtil;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final int SALT_LENGTH = 10;
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        String salt = PasswordUtil.getSalt(SALT_LENGTH);
        String securePassword = PasswordUtil.generateSecurePassword(user.getPassword(), salt);
        user.setPassword(securePassword);
        user.setSalt(salt);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
