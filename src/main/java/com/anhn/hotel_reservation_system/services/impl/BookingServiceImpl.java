package com.anhn.hotel_reservation_system.services.impl;

import com.anhn.hotel_reservation_system.dtos.BookingDTO;
import com.anhn.hotel_reservation_system.entities.Booking;
import com.anhn.hotel_reservation_system.entities.Customer;
import com.anhn.hotel_reservation_system.entities.Room;
import com.anhn.hotel_reservation_system.repositories.BookingRepository;
import com.anhn.hotel_reservation_system.services.BookingService;
import com.anhn.hotel_reservation_system.services.CustomerService;
import com.anhn.hotel_reservation_system.services.RoomService;
import com.anhn.hotel_reservation_system.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean bookingRoom(Long customerId, Long roomId, BookingDTO bookingDTO) {
        Customer customerInDb = customerService.getCustomer(customerId);
        Room roomIbDb = roomService.getRoomById(roomId);

        if (customerInDb == null || roomIbDb == null) {
            return false;
        }

        Booking booking = new Booking();

        booking.setCheckInDate(Utils.convertStringToLocalDate(bookingDTO.getCheckInDate(), "dd/MM/yyyy"));
        booking.setCheckOutDate(Utils.convertStringToLocalDate(bookingDTO.getCheckOutDate(), "dd/MM/yyyy"));
        booking.setCustomer(customerInDb);
        booking.setRoom(roomIbDb);
        bookingRepository.booking(booking);
        return true;
    }

    @Override
    public BookingDTO updateBooking(Long bookingId, BookingDTO bookingDTO) {
        Booking bookingInDb = bookingRepository.getBooking(bookingId);

        if (bookingInDb == null) {
            return null;
        }

        modelMapper.map(bookingDTO, bookingInDb);
        bookingInDb.setCheckInDate(Utils.convertStringToLocalDate(bookingDTO.getCheckInDate(), "dd/MM/yyyy"));
        bookingInDb.setCheckOutDate(Utils.convertStringToLocalDate(bookingDTO.getCheckOutDate(), "dd/MM/yyyy"));
        bookingRepository.updateBooking(bookingInDb);
        return modelMapper.map(bookingInDb, BookingDTO.class);
    }
}
