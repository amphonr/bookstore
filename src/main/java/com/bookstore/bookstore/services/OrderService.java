package com.bookstore.bookstore.services;

import com.bookstore.bookstore.bo.OrderBO;

import java.util.List;

public interface OrderService {
    List<OrderBO> listOrder(final Integer userId) throws Exception;
}
