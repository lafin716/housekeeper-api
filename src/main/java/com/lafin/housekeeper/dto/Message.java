package com.lafin.housekeeper.dto;


import com.lafin.housekeeper.constant.Result;
import lombok.Data;

@Data
public class Message {

    private Result status;
    private String message;
    private Object data;

    public Message() {
        this.status = Result.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}