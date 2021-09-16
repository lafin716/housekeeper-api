package com.lafin.housekeeper.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMarketRepositoryTest {

    @Autowired
    private OrderMarketRepository orderMarketRepository;

    @Test
    void findOneByProductId() {
        Long productId = 1L;
        var result = orderMarketRepository.findFirstByProductIdOrderByCreatedAsc(productId);

        System.out.println(result);
    }

}