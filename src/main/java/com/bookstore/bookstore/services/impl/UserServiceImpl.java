package com.bookstore.bookstore.services.impl;

import com.bookstore.bookstore.bo.LoginBO;
import com.bookstore.bookstore.bo.OrderBO;
import com.bookstore.bookstore.bo.UserBO;
import com.bookstore.bookstore.entities.Users;
import com.bookstore.bookstore.exception.handle.ExceptionDataNotFound;
import com.bookstore.bookstore.repositories.UserRepository;
import com.bookstore.bookstore.services.OrderService;
import com.bookstore.bookstore.services.UserLoginService;
import com.bookstore.bookstore.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private OrderService orderService;

    @Override
    public UserBO getUser() throws Exception{
        LoginBO userLogin = userLoginService.getUserLogin();
        if(null != userLogin){
            UserBO userBO = userLogin.getUserInfo();
            try {
                List<OrderBO> list = orderService.findByUsername(userLogin.getUsername());
                for(OrderBO order: list){
                    userBO.addBooks(order.getBookId());
                }
            }catch (ExceptionDataNotFound orderNotFound){
                log.info(orderNotFound.getMessage());
            }
            return userBO;
        }
        throw new ExceptionDataNotFound("user", "");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser() throws Exception {
        LoginBO userLogin = userLoginService.getUserLogin();
        orderService.deleteByUsername(userLogin.getUsername());
        userRepository.deleteById(userLogin.getUsername());
    }
}
