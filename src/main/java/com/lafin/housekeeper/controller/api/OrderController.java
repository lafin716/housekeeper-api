package com.lafin.housekeeper.controller.api;

import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.request.OrderMarketAddRequest;
import com.lafin.housekeeper.dto.request.OrderMarketModifyRequest;
import com.lafin.housekeeper.io.ResponseUtils;
import com.lafin.housekeeper.service.OrderMarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/markets")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMarketService orderMarketService;

    @GetMapping("/{productId}")
    public ResponseEntity<Message> marketList(@PathVariable Long productId) {
        var marketList = orderMarketService.getMarketList(productId);

        return ResponseUtils.success("조회 성공", marketList);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Message> addMarket(@PathVariable Long productId, @RequestBody OrderMarketAddRequest orderMarketAddRequest) {
        var market = orderMarketService.addMarket(productId, orderMarketAddRequest);

        return ResponseUtils.success("저장 성공", market);
    }

    @PutMapping("/{productId}/market/{marketId}")
    public ResponseEntity<Message> modifyMarket(@PathVariable Long productId, @PathVariable Long marketId, @RequestBody OrderMarketModifyRequest orderMarketModifyRequest) {
        var market = orderMarketService.modifyMarket(productId, marketId, orderMarketModifyRequest);

        return ResponseUtils.success("수정 성공", market);
    }

    @DeleteMapping("/{marketId}")
    public ResponseEntity<Message> deleteMarket(@PathVariable Long marketId) {
        orderMarketService.deleteMarket(marketId);

        return ResponseUtils.success("삭제 성공");
    }
}
