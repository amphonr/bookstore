package com.bookstore.bookstore.services.impl;

import com.bookstore.bookstore.bo.BookBO;
import com.bookstore.bookstore.intf.BookClient;
import com.bookstore.bookstore.intf.model.BookIntfModel;
import com.bookstore.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookClient bookClient;

    @Override
    public List<BookBO> listBook() {
       List<BookIntfModel> bookList = bookClient.listBook();
       List<BookIntfModel> recommendationList = bookClient.listRecommendation();
       List<BookBO> listBookResponse = new ArrayList<>();

        bookList.forEach(e->{
            BookBO bo = BookBO.builder()
                            .id(e.getId())
                            .name(e.getBook_name())
                            .author(e.getAuthor_name())
                            .price(e.getPrice())
                            .is_recommended(false)
                            .build();
            for(BookIntfModel re: recommendationList) {
                if(re.getId().equals(e.getId())){
                    bo.setIs_recommended(true);
                    continue;
                }
            }
            listBookResponse.add(bo);
        });

        listBookResponse.sort(Comparator.comparing(BookBO::getIs_recommended)
                    .reversed()
                    .thenComparing(Comparator.comparingInt(BookBO::getId)));
        return listBookResponse;
    }
}
