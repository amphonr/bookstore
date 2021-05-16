package com.bookstore.bookstore.bo;

import com.bookstore.bookstore.common.DateDeserializer;
import com.bookstore.bookstore.common.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CreateUserBO {

    @Id
    private String username;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    private Date dateOfBirth;

    @NotEmpty
    private String password;
}
