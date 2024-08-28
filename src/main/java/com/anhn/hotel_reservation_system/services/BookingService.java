package com.anhn.hotel_reservation_system.services;

import com.anhn.hotel_reservation_system.dtos.BookingDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    boolean bookingRoom(Long customerId, Long roomId, BookingDTO bookingDTO);
    BookingDTO updateBooking(Long bookingId, BookingDTO bookingDTO);
    boolean deleteBooking(Long bookingId);
    List<BookingDTO> getAllBookings();
    BookingDTO getBooking(Long bookingId);
    List<BookingDTO> findBookingsByCustomerAndDateRange(Long id, LocalDate from, LocalDate to);
}
