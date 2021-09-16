package com.lafin.housekeeper.service;

import com.lafin.housekeeper.constant.StockType;
import com.lafin.housekeeper.entity.InventoryLog;
import com.lafin.housekeeper.entity.OrderMarket;
import com.lafin.housekeeper.entity.Product;
import com.lafin.housekeeper.repository.InventoryLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ApiService<LinkedHashMap> apiService;

    private final OrderMarketService orderMarketService;

    private final InventoryLogService inventoryLogService;

    public void order(Product product) {
        var marketList = orderMarketService.getMarketList(product.getId());

        // 차례로 주문 처리할 때 하나라도 성공하면 종료
        for (OrderMarket orderMarket : marketList) {
            if (orderFromMarket(product, orderMarket)) {
                break;
            }
        }
    }

    public boolean orderFromMarket(Product product, OrderMarket orderMarket) {
        var result = apiService.get(orderMarket.getUrl(), HttpHeaders.EMPTY, LinkedHashMap.class).getBody();
        System.out.println(result);

        inventoryLogService.addBuyLog(product);
        return true;
    }
}
