package com.lafin.housekeeper.service;

import com.lafin.housekeeper.constant.StockType;
import com.lafin.housekeeper.entity.InventoryLog;
import com.lafin.housekeeper.entity.Product;
import com.lafin.housekeeper.repository.InventoryLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryLogService {

    private final InventoryLogRepository inventoryLogRepository;
    public void addUseLog(Product product) {
        InventoryLog inventoryLog = new InventoryLog();
        inventoryLog.setProductId(product.getId());
        inventoryLog.setProductName(product.getName());
        inventoryLog.setType(StockType.MINUS);
        inventoryLog.setChangeStock(1);
        inventoryLog.setRemainStock(product.getStock());

        inventoryLogRepository.save(inventoryLog);
    }

    public void addBuyLog(Product product) {
        InventoryLog inventoryLog = new InventoryLog();
        inventoryLog.setProductId(product.getId());
        inventoryLog.setProductName(product.getName());
        inventoryLog.setType(StockType.PLUS);
        inventoryLog.setChangeStock(product.getOrderStock());
        inventoryLog.setRemainStock(product.getStock());

        inventoryLogRepository.save(inventoryLog);
    }
}
