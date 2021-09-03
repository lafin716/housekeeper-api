package com.lafin.housekeeper.controller.api;

import com.lafin.housekeeper.constant.Result;
import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.request.MemberJoinRequest;
import com.lafin.housekeeper.io.ResponseUtils;
import com.lafin.housekeeper.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<Message> list() {
        var memberList = memberService.list();

        return ResponseUtils.success("회원 목록 조회 성공", memberList);
    }

    @PostMapping("/join")
    public ResponseEntity<Message> join(@RequestBody MemberJoinRequest memberJoinRequest) {
        var message = new Message();
        var member = memberService.join(memberJoinRequest);
        var headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(Result.OK);
        message.setMessage("회원 저장 성공");
        message.setData(member);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
