package com.lafin.housekeeper.constant;

public enum Result implements EnumMessage {
    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");

    int statusCode;

    String message;

    Result(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }


    @Override
    public String getCode() {
        return new StringBuilder().append(this.statusCode).toString();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
