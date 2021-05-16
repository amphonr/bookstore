package com.bookstore.bookstore.intf;

import com.bookstore.bookstore.config.AppClientConfiguration;
import com.bookstore.bookstore.intf.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "books", url = "https://scb-test-book-publisher.herokuapp.com")
public interface BookClient {
    @RequestMapping(method = RequestMethod.GET, value = "/books")
    List<Book> listBook();
}
