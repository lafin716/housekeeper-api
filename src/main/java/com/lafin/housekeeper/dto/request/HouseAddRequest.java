package com.lafin.housekeeper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseAddRequest {

    private Long memberId;

    private String name;
}
