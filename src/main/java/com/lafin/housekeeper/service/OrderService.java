package com.lafin.housekeeper.service;

import com.lafin.housekeeper.repository.InventoryLogRepository;
import com.lafin.housekeeper.repository.OrderMarketRepository;
import com.lafin.housekeeper.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMarketRepository orderMarketRepository;

    private final ProductRepository productRepository;

    private final InventoryLogRepository inventoryLogRepository;


}
