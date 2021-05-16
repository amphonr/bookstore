package com.bookstore.bookstore.services.impl;

import com.bookstore.bookstore.bo.LoginBO;
import com.bookstore.bookstore.entities.UserLogin;
import com.bookstore.bookstore.exception.handle.ExceptionDataNotFound;
import com.bookstore.bookstore.exception.handle.ExceptionUnauthorized;
import com.bookstore.bookstore.repositories.UserLoginRepository;
import com.bookstore.bookstore.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLoginImpl implements UserLoginService {
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public LoginBO getUserLogin() throws Exception{
        Optional<UserLogin> rs = userLoginRepository.findByUserName(getPrincipal());
        if(rs.isPresent()) {
            UserLogin login = rs.get();
            return LoginBO.builder()
                    .userId(login.getUserId())
                    .username(login.getUsername())
                    .password(login.getPassword())
                    .build();
        }
        throw new ExceptionDataNotFound("userLogin", getPrincipal());
    }

    private String getPrincipal(){
        Object  principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return  ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @Override
    public void deleteByUsername(String username) {
        userLoginRepository.deleteById(username);
    }

    @Override
    public void login(LoginBO request) throws Exception {
        Optional<UserLogin> rs = this.userLoginRepository.findById(request.getUsername());
        if(!rs.isPresent() || !rs.get().getPassword().equals(request.getPassword())){
            throw new ExceptionUnauthorized("Login Fail");
        }
    }
}
