package com.lafin.housekeeper.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token {

    private boolean result = true;

    private String accessToken;

    private String message;
}
