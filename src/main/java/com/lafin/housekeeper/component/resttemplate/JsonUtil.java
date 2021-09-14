package com.lafin.housekeeper.component.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    public String objectToJson(Object object) {
        var objectMapper = new ObjectMapper();
        String result = null;

        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
