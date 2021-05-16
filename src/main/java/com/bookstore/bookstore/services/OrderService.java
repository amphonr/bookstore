package com.bookstore.bookstore.services;

import com.bookstore.bookstore.bo.CreateOrderBO;
import com.bookstore.bookstore.bo.OrderBO;

import java.util.List;

public interface OrderService {
    public  List<OrderBO> findByUsername(final String username) throws Exception;
    public void deleteByUsername(final String username) throws Exception;
    public CreateOrderBO createOrder(final CreateOrderBO request) throws Exception;
}
