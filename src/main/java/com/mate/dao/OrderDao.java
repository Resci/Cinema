package com.mate.dao;

import com.mate.model.Order;
import com.mate.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
