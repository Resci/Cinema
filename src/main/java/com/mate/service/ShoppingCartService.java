package com.mate.service;

import com.mate.model.MovieSession;
import com.mate.model.ShoppingCart;
import com.mate.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
