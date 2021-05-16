package com.bookstore.bookstore;

import com.bookstore.bookstore.repositories.OrderRepository;
import com.bookstore.bookstore.services.OrderService;
import com.bookstore.bookstore.services.impl.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @Autowired
    public OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Before
    public void init() {

    }

    @Test
    @Transactional
    public void whenDeleteOrder_thenDeletingShouldBeSuccessful() {
        try {
            orderService.deleteByUsername("john.doe");
            Assert.assertTrue(true);
        }catch (Exception ex){
            Assert.assertTrue(false);
        }
    }

    @Test
    @Transactional
    public void whenDeleteOrder_thenDeletingShouldBeFailure() {
        try {
            orderService.deleteByUsername("john.dxxxxoe");
            Assert.assertTrue(false);
        }catch (Exception ex){
            Assert.assertTrue(true);
        }
    }

}
