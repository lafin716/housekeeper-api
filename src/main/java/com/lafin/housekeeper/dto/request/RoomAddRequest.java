package com.lafin.housekeeper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAddRequest {

    private Long houseId;

    private String name;
}
