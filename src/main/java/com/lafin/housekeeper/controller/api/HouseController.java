package com.lafin.housekeeper.controller.api;

import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.request.HouseAddRequest;
import com.lafin.housekeeper.dto.request.HouseModifyRequest;
import com.lafin.housekeeper.io.ResponseUtils;
import com.lafin.housekeeper.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/list")
    public ResponseEntity<Message> list() {
        var houseList = houseService.list();

        return ResponseUtils.success("집 목록 조회", houseList);
    }

    @PostMapping("/add")
    public ResponseEntity<Message> add(@RequestBody HouseAddRequest houseAddRequest) {
        var house = houseService.add(houseAddRequest);

        return ResponseUtils.success("집 추가 성공", house);
    }

    @PutMapping("/modify/{houseId}")
    public ResponseEntity<Message> add(@PathVariable Long houseId, @RequestBody HouseModifyRequest houseModifyRequest) {
        var house = houseService.modify(houseId, houseModifyRequest);

        return ResponseUtils.success("집 수정 성공", house);
    }

    @DeleteMapping("/delete/{houseId}")
    public ResponseEntity<Message> add(@PathVariable Long houseId) {
        houseService.delete(houseId);

        return ResponseUtils.success("집 삭제 성공");
    }
}
