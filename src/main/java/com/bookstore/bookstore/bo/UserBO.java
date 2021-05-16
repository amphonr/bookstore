package com.bookstore.bookstore.bo;

import com.bookstore.bookstore.common.DateDeserializer;
import com.bookstore.bookstore.common.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserBO {
    private String name;
    private String surname;
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date dateOfBirth;

    private List<Integer> books;
    public void addBooks(Integer bookId){
        if(this.books==null) {
            this.books = new ArrayList<>();
        }
        this.books.add(bookId);
    }
}
