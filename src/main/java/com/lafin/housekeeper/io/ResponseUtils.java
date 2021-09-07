package com.lafin.housekeeper.io;

import com.lafin.housekeeper.constant.Result;
import com.lafin.housekeeper.dto.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;

public class ResponseUtils {

    public static ResponseEntity<Message> success(String message) {
        var messageObj = new Message();
        messageObj.setResult(true);
        messageObj.setStatus(Result.OK);
        messageObj.setMessage(message);

        return new ResponseEntity<Message>(messageObj, JsonHeader.getHeader(), HttpStatus.OK);
    }

    public static <T> ResponseEntity<Message> success(String message, T data) {
        var messageObj = new Message();
        messageObj.setResult(true);
        messageObj.setStatus(Result.OK);
        messageObj.setMessage(message);
        messageObj.setData(data);

        return new ResponseEntity<Message>(messageObj, JsonHeader.getHeader(), HttpStatus.OK);
    }

    public static <T> ResponseEntity<Message> success(String message, T data, Result result) {
        var messageObj = new Message();
        messageObj.setResult(true);
        messageObj.setStatus(result);
        messageObj.setMessage(message);
        messageObj.setData(data);

        return new ResponseEntity<Message>(messageObj, JsonHeader.getHeader(), HttpStatus.OK);
    }

    public static ResponseEntity<Message> fail(String message) {
        var messageObj = new Message();
        messageObj.setResult(false);
        messageObj.setStatus(Result.BAD_REQUEST);
        messageObj.setMessage(message);

        return new ResponseEntity<Message>(messageObj, JsonHeader.getHeader(), HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<Message> fail(String message, T data) {
        var messageObj = new Message();
        messageObj.setResult(false);
        messageObj.setStatus(Result.BAD_REQUEST);
        messageObj.setMessage(message);
        messageObj.setData(data);

        return new ResponseEntity<Message>(messageObj, JsonHeader.getHeader(), HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<Message> fail(String message, T data, Result result) {
        var messageObj = new Message();
        messageObj.setResult(false);
        messageObj.setStatus(result);
        messageObj.setMessage(message);
        messageObj.setData(data);

        return new ResponseEntity<Message>(messageObj, JsonHeader.getHeader(), HttpStatus.BAD_REQUEST);
    }

}
