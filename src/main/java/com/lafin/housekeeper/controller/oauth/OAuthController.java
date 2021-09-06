package com.lafin.housekeeper.controller.oauth;

import com.lafin.housekeeper.constant.Result;
import com.lafin.housekeeper.dto.Message;
import com.lafin.housekeeper.dto.Token;
import com.lafin.housekeeper.dto.request.MemberJoinRequest;
import com.lafin.housekeeper.dto.request.MemberLoginRequest;
import com.lafin.housekeeper.dto.request.OAuthAuthorizeRequest;
import com.lafin.housekeeper.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Message> join(@RequestBody MemberJoinRequest memberJoinRequest) {
        var message = new Message();
        var member = memberService.join(memberJoinRequest);
        var headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(Result.OK);
        message.setMessage("회원 가입 성공");
        message.setData(member);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @PostMapping("/login")
    public Token authorize(@RequestBody MemberLoginRequest memberLoginRequest) {
        Token token = new Token();

        try {
            token.setAccessToken(memberService.getAccessToken(memberLoginRequest.getEmail(), memberLoginRequest.getPassword()));
        } catch (IllegalArgumentException ie) {
            token.setResult(false);
            token.setMessage(ie.getMessage());
        } catch (Exception e) {
            token.setResult(false);
            token.setMessage(e.getMessage());
        }

        return token;
    }
}
