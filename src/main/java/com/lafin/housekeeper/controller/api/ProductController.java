package com.lafin.housekeeper.controller.api;

import com.lafin.housekeeper.constant.Result;
import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.request.HouseModifyRequest;
import com.lafin.housekeeper.dto.request.ProductAddRequest;
import com.lafin.housekeeper.dto.request.ProductModifyRequest;
import com.lafin.housekeeper.dto.request.RoomAddRequest;
import com.lafin.housekeeper.io.ResponseUtils;
import com.lafin.housekeeper.service.ProductService;
import com.lafin.housekeeper.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<Message> list() {
        var productList = productService.list();

        return ResponseUtils.success("물건 목록 조회 성공", productList);
    }

    @PostMapping("/add")
    public ResponseEntity<Message> add(@RequestBody ProductAddRequest productAddRequest) {
        var product = productService.add(productAddRequest);

        return ResponseUtils.success("물건 추가 성공", product);
    }

    @PutMapping("/modify/{productId}")
    public ResponseEntity<Message> add(@PathVariable Long productId, @RequestBody ProductModifyRequest houseModifyRequest) {
        var house = productService.modify(productId, houseModifyRequest);

        return ResponseUtils.success("물건 수정 성공", house);
    }

    @PutMapping("/use/{productId}")
    public ResponseEntity<Message> use(@PathVariable Long productId) throws Exception {
        var house = productService.useProduct(productId);

        return ResponseUtils.success("물건 사용처리 성공", house);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Message> add(@PathVariable Long productId) {
        productService.delete(productId);

        return ResponseUtils.success("물건 삭제 성공");
    }
}