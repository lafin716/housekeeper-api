package com.lafin.housekeeper.repository;


import com.lafin.housekeeper.entity.Member;
import com.lafin.housekeeper.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
