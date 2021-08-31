package com.lafin.housekeeper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddRequest {

    private Long roomId;

    private String name;

    private Integer count;

    private Integer orderCount;

    private Integer minimumCount;
}
