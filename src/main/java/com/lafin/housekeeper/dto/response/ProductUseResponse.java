package com.lafin.housekeeper.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lafin.housekeeper.constant.ProductStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class ProductUseResponse {

    private ProductStatus productStatus;

    private String message;

    private String name;

    private String url;

    private Integer remainStock;

    private Integer orderStock;
}
