package com.lafin.housekeeper.controller.api;

import com.lafin.housekeeper.constant.Result;
import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.request.*;
import com.lafin.housekeeper.io.ResponseUtils;
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
        var roomList = roomService.list();

        return ResponseUtils.success("방 수정 성공", roomList);
    }

    @PostMapping("/add")
    public ResponseEntity<Message> add(@RequestBody RoomAddRequest roomAddRequest) {
        var room = roomService.add(roomAddRequest);

        return ResponseUtils.success("방 수정 성공", room);
    }

    @PutMapping("/modify/{roomId}")
    public ResponseEntity<Message> add(@PathVariable Long roomId, @RequestBody RoomModifyRequest roomModifyRequest) {
        var room = roomService.modify(roomId, roomModifyRequest);

        return ResponseUtils.success("방 수정 성공", room);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Message> add(@PathVariable Long roomId) {
        roomService.delete(roomId);

        return ResponseUtils.success("방 삭제 성공");
    }
}

