package com.lafin.housekeeper.service;

import com.lafin.housekeeper.dto.request.HouseAddRequest;
import com.lafin.housekeeper.dto.request.HouseModifyRequest;
import com.lafin.housekeeper.entity.House;
import com.lafin.housekeeper.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;

    public List<House> list() {
        return houseRepository.findAll();
    }

    public House add(HouseAddRequest houseAddRequest) {
        var house = new House();
        house.setMemberId(houseAddRequest.getMemberId());
        house.setName(houseAddRequest.getName());

        return houseRepository.save(house);
    }

    public House modify(Long houseId, HouseModifyRequest houseModifyRequest) {
        var house = new House();
        house.setId(houseId);
        house.setName(houseModifyRequest.getName());

        return houseRepository.save(house);
    }

    public void delete(Long houseId) {
        houseRepository.deleteById(houseId);
    }
}
