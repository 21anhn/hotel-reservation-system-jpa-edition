package com.anhn.hotel_reservation_system.controllers;

import com.anhn.hotel_reservation_system.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{customerId}/{roomId}")
    public ResponseEntity<?> addBooking(@PathVariable Long customerId, @PathVariable Long roomId) {
        boolean check = bookingService.bookingRoom(customerId, roomId);
        if (check) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
