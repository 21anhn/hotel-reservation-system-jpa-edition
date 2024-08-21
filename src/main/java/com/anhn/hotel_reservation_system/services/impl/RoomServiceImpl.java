package com.anhn.hotel_reservation_system.services.impl;

import com.anhn.hotel_reservation_system.entities.Room;
import com.anhn.hotel_reservation_system.repositories.RoomRepository;
import com.anhn.hotel_reservation_system.services.RoomService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Room saveRoom(Long id, Room room) {
        Room roomInDB = getRoomById(id);
        if (roomInDB != null) {
            modelMapper.map(room, roomInDB);
            roomRepository.update(roomInDB);
            return roomInDB;
        }
        return null;
    }

    @Override
    public void deleteRoom(Long id) {
        Room r = getRoomById(id);
        if(r != null) {
            roomRepository.delete(r);
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
