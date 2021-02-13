package com.mate.service.impl;

import com.mate.exception.AuthenticationException;
import com.mate.model.User;
import com.mate.service.AuthenticationService;
import com.mate.service.ShoppingCartService;
import com.mate.service.UserService;
import com.mate.util.PasswordUtil;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(
                PasswordUtil.generateSecurePassword(password, user.get().getSalt()))) {
            return user.get();
        }
        throw new AuthenticationException("Invalid login or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
