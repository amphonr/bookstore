package com.bookstore.bookstore;

import com.bookstore.bookstore.bo.CreateUserBO;
import com.bookstore.bookstore.services.UserService;
import org.junit.Assert;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Autowired
    public UserService userService;

    @Before
    public void init() {

    }

    @Test
    @Transactional
    public void whenCreateUser_thenCreatingShouldBeSuccessful() throws Exception{
        // given
        String dateOfBirthStr = "15/01/1985";
        SimpleDateFormat formatDate= new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateOfBirst = formatDate.parse(dateOfBirthStr);
        CreateUserBO createUserBO = CreateUserBO.builder()
                .username("john.doe")
                .password("thisismysecret")
                .name("john")
                .surname("doe")
                .dateOfBirth(new Date(dateOfBirst.getTime()))
                .build();

        try {
            userService.createUser(createUserBO);
            Assert.assertTrue(true);
        }catch (Exception ex){
            Assert.assertTrue(false);
        }
    }

    @Test
    @Transactional
    public void whenCreateUser_thenCreatingShouldBeBeFailure() {
        // given
        CreateUserBO createUserBO = CreateUserBO.builder()
                .name("john")
                .surname("doe")
                .build();
        try {
            userService.createUser(createUserBO);
            Assert.assertTrue(false);
        }catch (Exception ex){
            Assert.assertTrue(true);
        }
    }


}
