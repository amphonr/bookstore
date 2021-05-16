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
    public List<OrderBO> findByUsername(final String username) throws Exception {
        List<Order> rs = orderRepository.findByUsername(username);
        if(rs != null && rs.size()>0){
            List<OrderBO> orderListBO = new ArrayList<>();
            for(Order oder: rs){
                orderListBO.add(OrderBO.builder()
                        .id(oder.getId())
                        .bookId(oder.getBookId())
                        .username(oder.getUsername())
                        .build());
            }
            return orderListBO;
        }
        throw new ExceptionDataNotFound("search order by userId",username);
    }

    @Override
    public void deleteByUsername(String username) throws Exception{
        try {
            Long result = orderRepository.deleteByUsername(username);
            if(result<=0){
                throw new Exception("delete order by username : data not found");
            }
        }catch (Exception ex){
            throw new Exception("delete order by username Error :"+ex.getMessage());
        }
    }
}
