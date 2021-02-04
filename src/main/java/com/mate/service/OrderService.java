package com.mate.service;

import com.mate.model.Order;
import com.mate.model.ShoppingCart;
import com.mate.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
