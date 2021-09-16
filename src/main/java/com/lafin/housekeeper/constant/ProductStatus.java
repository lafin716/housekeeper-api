package com.lafin.housekeeper.constant;

public enum ProductStatus implements EnumMessage{
    STABLE("STABLE", ""),
    WARN("WARN", "재고가 부족합니다"),
    EMPTY("EMPTY", "재고가 비어있습니다");

    private String code;

    private String message;

    ProductStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
