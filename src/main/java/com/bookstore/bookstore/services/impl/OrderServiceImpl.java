package com.bookstore.bookstore.services.impl;

import com.bookstore.bookstore.bo.OrderBO;
import com.bookstore.bookstore.entities.Order;
import com.bookstore.bookstore.exception.handle.ExceptionDataNotFound;
import com.bookstore.bookstore.repositories.OrderRepository;
import com.bookstore.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderBO> listOrder(final Integer userId) throws Exception {
        List<Order> rs = orderRepository.findByUserId(userId);
        if(rs != null && rs.size()>0){
            List<OrderBO> orderListBO = new ArrayList<>();
            for(Order oder: rs){
                orderListBO.add(OrderBO.builder()
                        .id(oder.getId())
                        .bookId(oder.getBookId())
                        .userId(oder.getUserId())
                        .build());
            }
            return orderListBO;
        }
        throw new ExceptionDataNotFound("search order by userId",userId);
    }
}
