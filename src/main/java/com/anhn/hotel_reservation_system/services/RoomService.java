package com.anhn.hotel_reservation_system.services;

import com.anhn.hotel_reservation_system.entities.Room;

import java.util.List;

public interface RoomService {

    void addRoom(Room room);
    void saveRoom(Room room);
    void deleteRoom(Room room);
    Room getRoomById(Long id);
    List<Room> getAllRooms();
}
