package com.bookstore.bookstore.services;

import com.bookstore.bookstore.bo.LoginBO;

public interface UserLoginService {
   public LoginBO getUserLogin() throws Exception;
   public void login(LoginBO request) throws Exception;
   public void deleteByUsername(String username);
}
