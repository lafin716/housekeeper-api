package com.lafin.housekeeper.controller;

import com.lafin.housekeeper.constant.Result;
import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.request.ProductAddRequest;
import com.lafin.housekeeper.dto.request.RoomAddRequest;
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
        var message = new Message();
        var headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        var productList = productService.list();

        message.setStatus(Result.OK);
        message.setData(productList);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Message> add(@RequestBody ProductAddRequest productAddRequest) {
        var message = new Message();
        var headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        var product = productService.add(productAddRequest);

        message.setStatus(Result.OK);
        message.setData(product);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
    }
}