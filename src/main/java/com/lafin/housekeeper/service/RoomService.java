package com.lafin.housekeeper.service;

import com.lafin.housekeeper.dto.request.HouseAddRequest;
import com.lafin.housekeeper.dto.request.RoomAddRequest;
import com.lafin.housekeeper.entity.House;
import com.lafin.housekeeper.entity.Room;
import com.lafin.housekeeper.repository.HouseRepository;
import com.lafin.housekeeper.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> list() {
        return roomRepository.findAll();
    }

    public Room add(RoomAddRequest roomAddRequest) {
        var room = new Room();
        room.setHouseId(roomAddRequest.getHouseId());
        room.setName(roomAddRequest.getName());

        return roomRepository.save(room);
    }
}
