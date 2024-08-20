package com.anhn.hotel_reservation_system.services.impl;

import com.anhn.hotel_reservation_system.entities.Room;
import com.anhn.hotel_reservation_system.repositories.RoomRepository;
import com.anhn.hotel_reservation_system.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void saveRoom(Room room) {
        if (getRoomById(room.getId()) != null) {
            roomRepository.save(room);
        }
    }

    @Override
    public void deleteRoom(Room room) {
        if(getRoomById(room.getId()) != null) {
            roomRepository.delete(room);
        }
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
