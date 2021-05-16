package com.bookstore.bookstore.intf.model;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Book {
    private Integer id;
    private String book_name;
    private String author_name;
    private Integer price;
}
