package com.lafin.housekeeper.controller;

import com.lafin.housekeeper.constant.Result;
import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.request.HouseAddRequest;
import com.lafin.housekeeper.dto.request.RoomAddRequest;
import com.lafin.housekeeper.service.HouseService;
import com.lafin.housekeeper.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;


@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/list")
    public ResponseEntity<Message> list() {
        var message = new Message();
        var headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        var roomList = roomService.list();

        message.setStatus(Result.OK);
        message.setData(roomList);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Message> add(@RequestBody RoomAddRequest roomAddRequest) {
        var message = new Message();
        var headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        var room = roomService.add(roomAddRequest);

        message.setStatus(Result.OK);
        message.setData(room);

        return new ResponseEntity<Message>(message, headers, HttpStatus.OK);
    }
}

