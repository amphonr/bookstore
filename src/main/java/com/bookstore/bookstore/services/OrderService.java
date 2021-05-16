package com.bookstore.bookstore.services;

import com.bookstore.bookstore.bo.OrderBO;

import java.util.List;

public interface OrderService {
    public  List<OrderBO> findByUsername(final String username) throws Exception;
    public void deleteByUsername(String username) throws Exception;
}
