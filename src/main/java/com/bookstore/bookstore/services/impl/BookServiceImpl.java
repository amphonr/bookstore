package com.bookstore.bookstore.services.impl;

import com.bookstore.bookstore.intf.BookClient;
import com.bookstore.bookstore.intf.model.Book;
import com.bookstore.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookClient bookClient;

    @Override
    public List<Book> listBook() {
       List list = bookClient.listBook();
        return list;
    }
}
