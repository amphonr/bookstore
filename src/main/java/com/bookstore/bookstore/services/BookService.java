package com.bookstore.bookstore.services;

import com.bookstore.bookstore.bo.BookBO;
import com.bookstore.bookstore.intf.model.BookIntfModel;

import java.util.List;

public interface BookService {
    public List<BookBO> listBook();
}
