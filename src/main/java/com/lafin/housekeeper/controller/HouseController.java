package com.lafin.housekeeper.controller;

import com.lafin.housekeeper.constant.Result;
import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.request.HouseAddRequest;
import com.lafin.housekeeper.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/list")
    public ResponseEntity<Message> list() {
        var message = new Message();
        var headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        var houseList = houseService.list();

        message.setStatus(Result.OK);
        message.setData(houseList);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Message> add(@RequestBody HouseAddRequest houseAddRequest) {
        var message = new Message();
        var headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        var house = houseService.add(houseAddRequest);

        message.setStatus(Result.OK);
        message.setData(house);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
    }
}
