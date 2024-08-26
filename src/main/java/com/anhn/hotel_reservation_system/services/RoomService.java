package com.anhn.hotel_reservation_system.services;

import com.anhn.hotel_reservation_system.dtos.RoomDTO;
import com.anhn.hotel_reservation_system.entities.Room;

import java.util.List;

public interface RoomService {

    void addRoom(Room room);
    Room saveRoom(Long id, Room room);
    void deleteRoom(Long id);
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    RoomDTO getRoomByRoomNumber(String roomNumber);
    List<RoomDTO> getRoomByType(String type);
}
