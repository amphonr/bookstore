package com.bookstore.bookstore.services;

import com.bookstore.bookstore.bo.LoginBO;
import com.bookstore.bookstore.entities.UserLogin;

import java.util.List;

public interface UserLoginService {
   public LoginBO getUserLogin() throws Exception;
   public void deleteByUsername(String username);
   public void login(LoginBO request) throws Exception;
}
