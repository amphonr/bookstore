package com.bookstore.bookstore.intf;

import com.bookstore.bookstore.intf.model.BookIntfModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "books", url = "${scbTestBook-service.baseURL}")
public interface BookClient {
    @RequestMapping(method = RequestMethod.GET, value = "${scbTestBook-service.books}")
    public List<BookIntfModel> listBook();

    @RequestMapping(method = RequestMethod.GET, value = "${scbTestBook-service.books.recommendation}")
    public List<BookIntfModel> listRecommendation();
}
