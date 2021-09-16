package com.lafin.housekeeper.service;

import com.lafin.housekeeper.dto.request.OrderMarketAddRequest;
import com.lafin.housekeeper.dto.request.OrderMarketModifyRequest;
import com.lafin.housekeeper.entity.OrderMarket;
import com.lafin.housekeeper.entity.Product;
import com.lafin.housekeeper.repository.InventoryLogRepository;
import com.lafin.housekeeper.repository.OrderMarketRepository;
import com.lafin.housekeeper.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMarketService {

    private final OrderMarketRepository orderMarketRepository;

    public List<OrderMarket> getMarketList(Long productId) {
        var result = orderMarketRepository.findOrderMarketByProductId(productId);
        return result;
    }

    public String getFirstMarketUrl(Long productId) {
        var market = orderMarketRepository.findFirstByProductIdOrderByCreatedAsc(productId);
        return market.getUrl();
    }

    public OrderMarket addMarket(Long productId, OrderMarketAddRequest orderMarketAddRequest) {
        OrderMarket orderMarket = new OrderMarket();
        orderMarket.setProductId(productId);
        orderMarket.setName(orderMarketAddRequest.getName());
        orderMarket.setUrl(orderMarketAddRequest.getUrl());

        return orderMarketRepository.save(orderMarket);
    }

    public OrderMarket modifyMarket(Long productId, Long marketId, OrderMarketModifyRequest orderMarketModifyRequest) {
        OrderMarket orderMarket = new OrderMarket();
        orderMarket.setId(marketId);
        orderMarket.setProductId(productId);
        orderMarket.setName(orderMarketModifyRequest.getName());
        orderMarket.setUrl(orderMarketModifyRequest.getUrl());

        return orderMarketRepository.save(orderMarket);
    }

    public void deleteMarket(Long marketId) {
        orderMarketRepository.deleteById(marketId);
    }


}
