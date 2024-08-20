package com.anhn.hotel_reservation_system.controllers;

import com.anhn.hotel_reservation_system.entities.Room;
import com.anhn.hotel_reservation_system.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }



}
