package com.bookstore.bookstore.bo;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LoginBO {
    private String username;
    private String password;
    private Integer userId;
}
