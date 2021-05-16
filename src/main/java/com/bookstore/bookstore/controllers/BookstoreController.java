package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.bo.CreateOrderBO;
import com.bookstore.bookstore.bo.CreateUserBO;
import com.bookstore.bookstore.bo.LoginBO;
import com.bookstore.bookstore.bo.UserBO;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.services.OrderService;
import com.bookstore.bookstore.services.UserLoginService;
import com.bookstore.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookstoreController {
    private UserLoginService userLoginService;
    private UserService userService;
    private BookService bookService;
    private OrderService orderService;

    @Autowired
    public BookstoreController(UserLoginService userLoginService,
                               UserService userService,
                               BookService bookService,
                               OrderService orderService){
        this.userLoginService = userLoginService;
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @PostMapping("/login")
    public  void login(@RequestBody LoginBO request) throws Exception{
        this.userLoginService.login(request);
    }

    @GetMapping("/users")
    public ResponseEntity getUser() throws Exception{
        UserBO users = this.userService.getUser();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users")
    public ResponseEntity deleteUser() throws Exception{
        this.userService.deleteUser();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody CreateUserBO request)throws Exception{
        this.userService.createUser(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/orders")
    public ResponseEntity createOrders(@RequestBody CreateOrderBO request)throws Exception{
        CreateOrderBO orderCreated = this.orderService.createOrder(request);
        return ResponseEntity.ok(orderCreated);
    }

    @GetMapping("/books")
    public ResponseEntity listBook(){
        List list = this.bookService.listBook();
        return ResponseEntity.ok(list);
    }
}
