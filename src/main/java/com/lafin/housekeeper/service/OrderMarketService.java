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

    private final OrderService orderService;

    private final OrderMarketRepository orderMarketRepository;

    public List<OrderMarket> getMarketList(Long productId) {
        var result = orderMarketRepository.findOrderMarketByProductId(productId);

        return result;
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

    public void doOrder(Product product) {
        var marketList = getMarketList(product.getId());

        // 차례로 주문 처리할 때 하나라도 성공하면 종료
        for (OrderMarket orderMarket : marketList) {
            if (orderService.order(product, orderMarket)) {
                break;
            }
        }
    }
}
