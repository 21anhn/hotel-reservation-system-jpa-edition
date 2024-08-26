package com.anhn.hotel_reservation_system.controllers;

import com.anhn.hotel_reservation_system.dtos.RoomDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        if(room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getRoomByRoomNumber(@RequestParam String roomNumber) {
        RoomDTO roomDTO = roomService.getRoomByRoomNumber(roomNumber);
        if(roomDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

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

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        Room r = roomService.saveRoom(id, room);
        if(r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
