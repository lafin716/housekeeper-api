package com.lafin.housekeeper.repository;


import com.lafin.housekeeper.entity.House;
import com.lafin.housekeeper.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
