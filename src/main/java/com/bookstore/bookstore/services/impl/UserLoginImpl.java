package com.bookstore.bookstore.services.impl;

import com.bookstore.bookstore.bo.LoginBO;
import com.bookstore.bookstore.bo.UserBO;
import com.bookstore.bookstore.entities.Users;
import com.bookstore.bookstore.exception.handle.ExceptionDataNotFound;
import com.bookstore.bookstore.exception.handle.ExceptionUnauthorized;
import com.bookstore.bookstore.repositories.UserRepository;
import com.bookstore.bookstore.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginImpl implements UserLoginService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginBO getUserLogin() throws Exception{
        Optional<Users> rs = userRepository.findById(getPrincipal());
        if(rs.isPresent()) {
            Users login = rs.get();
            return LoginBO.builder()
                    .username(login.getUsername())
                    .password(login.getPassword())
                    .userInfo(UserBO.builder()
                            .surname(login.getSurname())
                            .name(login.getName())
                            .dateOfBirth(login.getDateOfBirth())
                            .build())
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
        userRepository.deleteById(username);
    }

    @Override
    public void login(LoginBO request) throws Exception {
        Optional<Users> rs = this.userRepository.findById(request.getUsername());
        if(!rs.isPresent() || !rs.get().getPassword().equals(request.getPassword())){
            throw new ExceptionUnauthorized("Login Fail");
        }
    }
}
