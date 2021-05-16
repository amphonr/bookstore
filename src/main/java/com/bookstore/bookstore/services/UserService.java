package com.bookstore.bookstore.services;

import com.bookstore.bookstore.bo.UserBO;
import com.bookstore.bookstore.entities.Users;

import java.util.List;

public interface UserService {
    public UserBO getUser() throws Exception;
    public void deleteUser() throws Exception;
}
