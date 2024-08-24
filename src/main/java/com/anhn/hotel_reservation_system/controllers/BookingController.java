package com.anhn.hotel_reservation_system.controllers;

import com.anhn.hotel_reservation_system.dtos.BookingDTO;
import com.anhn.hotel_reservation_system.repositories.BookingRepository;
import com.anhn.hotel_reservation_system.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooking(@PathVariable Long id) {
        //BookingDTO booking = bookingService.getBooking(id);
        return new ResponseEntity<>(bookingService.getBooking(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @PostMapping("/{customerId}/{roomId}")
    public ResponseEntity<?> addBooking(@PathVariable Long customerId, @PathVariable Long roomId, @RequestBody BookingDTO bookingDTO) {
        boolean check = bookingService.bookingRoom(customerId, roomId, bookingDTO);
        if (check) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable Long bookingId, @RequestBody BookingDTO bookingDTO) {
        BookingDTO check = bookingService.updateBooking(bookingId, bookingDTO);
        if (check != null) {
            return new ResponseEntity<>(check, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok().build();
    }
}
