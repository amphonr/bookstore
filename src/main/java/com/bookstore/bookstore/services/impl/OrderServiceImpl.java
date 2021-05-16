package com.bookstore.bookstore.services.impl;

import com.bookstore.bookstore.bo.*;
import com.bookstore.bookstore.entities.Order;
import com.bookstore.bookstore.exception.handle.ExceptionDataNotFound;
import com.bookstore.bookstore.repositories.OrderRepository;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.services.OrderService;
import com.bookstore.bookstore.services.UserLoginService;
import com.bookstore.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserLoginService userLoginService;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateOrderBO createOrder(final CreateOrderBO request) throws Exception{
        LoginBO loginUser = userLoginService.getUserLogin();
        List<BookBO> bookList = bookService.listBook();
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<BookBO> bookOrderList = bookList.stream().filter(f -> {
            Boolean chk = false;
            for (Integer bookId : request.getOrders()) {
               if(bookId == f.getId()){
                   chk = true;
                   continue;
               }
            }
           return chk;
        }).collect(Collectors.toList());

        for(BookBO bookOrder : bookOrderList){
            totalPrice = totalPrice.add(bookOrder.getPrice());
            Order order = Order.builder()
                    .bookId(bookOrder.getId())
                    .username(loginUser.getUsername())
                    .build();
            orderRepository.save(order);
        }

        return CreateOrderBO.builder()
                .orders(request.getOrders())
                .price(totalPrice)
                .build();
    }

}
