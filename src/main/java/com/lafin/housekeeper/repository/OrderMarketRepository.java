package com.lafin.housekeeper.repository;


import com.lafin.housekeeper.entity.Member;
import com.lafin.housekeeper.entity.OrderMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMarketRepository extends JpaRepository<OrderMarket, Long> {

    public List<OrderMarket> findOrderMarketByProductId(Long productId);
}
