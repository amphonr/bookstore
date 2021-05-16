package com.bookstore.bookstore.intf.model;

import com.bookstore.bookstore.common.CurrencyDeserializer;
import com.bookstore.bookstore.common.CurrencySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BookIntfModel {
    private Integer id;
    private String book_name;
    private String author_name;

    @JsonDeserialize(using = CurrencyDeserializer.class)
    @JsonSerialize(using = CurrencySerializer.class)
    private BigDecimal price;
}
