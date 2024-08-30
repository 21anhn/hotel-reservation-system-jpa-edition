package com.anhn.hotel_reservation_system.controllers;

import com.anhn.hotel_reservation_system.dtos.BookingDTO;
import com.anhn.hotel_reservation_system.services.BookingService;
import com.anhn.hotel_reservation_system.utils.Utils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@Tag(name = "Booking Controller")
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

    @GetMapping("/customer")
    public ResponseEntity<?> getBookingStatus(@RequestParam Long customerId, @RequestParam @Nullable String from, @RequestParam @Nullable String to) {
        LocalDate fromDate = Utils.convertStringToLocalDate(from, "dd/MM/yyyy");
        LocalDate toDate = Utils.convertStringToLocalDate(to, "dd/MM/yyyy");
        List<BookingDTO> bookingDTOS = bookingService.findBookingsByCustomerAndDateRange(customerId, fromDate, toDate);
        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }
}
