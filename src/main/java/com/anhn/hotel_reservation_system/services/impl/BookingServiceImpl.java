package com.anhn.hotel_reservation_system.services.impl;

import com.anhn.hotel_reservation_system.dtos.BookingDTO;
import com.anhn.hotel_reservation_system.entities.Booking;
import com.anhn.hotel_reservation_system.entities.Customer;
import com.anhn.hotel_reservation_system.entities.Room;
import com.anhn.hotel_reservation_system.repositories.BookingRepository;
import com.anhn.hotel_reservation_system.repositories.CustomerRepository;
import com.anhn.hotel_reservation_system.repositories.RoomRepository;
import com.anhn.hotel_reservation_system.services.BookingService;
import com.anhn.hotel_reservation_system.services.CustomerService;
import com.anhn.hotel_reservation_system.services.RoomService;
import com.anhn.hotel_reservation_system.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean bookingRoom(Long customerId, Long roomId, BookingDTO bookingDTO) {
        Customer customerInDb = customerRepository.getById(customerId);
        Room roomIbDb = roomService.getRoomById(roomId);

        if (customerInDb == null || roomIbDb == null) {
            return false;
        }

        Booking booking = new Booking();

        booking.setCheckInDate(Utils.convertStringToLocalDate(bookingDTO.getCheckInDate(), "dd/MM/yyyy"));
        booking.setCheckOutDate(Utils.convertStringToLocalDate(bookingDTO.getCheckOutDate(), "dd/MM/yyyy"));
        booking.setCustomer(customerInDb);
        booking.setRoom(roomIbDb);
        customerInDb.getBookings().add(booking);
        roomIbDb.getBookings().add(booking);
        customerRepository.update(customerInDb);
        roomRepository.update(roomIbDb);
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

    @Override
    public boolean deleteBooking(Long bookingId) {
        Booking b = bookingRepository.getBooking(bookingId);
        if (b == null) {
            return false;
        }
        bookingRepository.deleteBooking(b);
        return true;
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.getAll();
        if (bookings == null || bookings.isEmpty()) {
            return null;
        }
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
            bookingDTO.setCheckInDate(Utils.convertLocalDateToString(booking.getCheckInDate(), "dd/MM/yyyy"));
            bookingDTO.setCheckOutDate(Utils.convertLocalDateToString(booking.getCheckOutDate(), "dd/MM/yyyy"));
            bookingDTO.getCustomer().setBookings(booking.getCustomer().getBookings());
            bookingDTO.getRoom().setBookings(booking.getRoom().getBookings());
            bookingDTOs.add(bookingDTO);
        }
        return bookingDTOs;
    }

    @Override
    public BookingDTO getBooking(Long bookingId) {
        Booking booking = bookingRepository.getBooking(bookingId);
        if (booking == null) {
            return null;
        }
        BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
        bookingDTO.setCheckInDate(Utils.convertLocalDateToString(booking.getCheckInDate(), "dd/MM/yyyy"));
        bookingDTO.setCheckOutDate(Utils.convertLocalDateToString(booking.getCheckOutDate(), "dd/MM/yyyy"));
        bookingDTO.getCustomer().setBookings(booking.getCustomer().getBookings());
        bookingDTO.getRoom().setBookings(booking.getRoom().getBookings());
        return bookingDTO;
    }

    @Override
    public List<BookingDTO> findBookingsByCustomerAndDateRange(Long id, LocalDate from, LocalDate to) {
        List<Booking> bookings = bookingRepository.findBookingByCustomerIdAndFromDateToEndDate(id, from, to);
        if (bookings == null || bookings.isEmpty()) {
            return null;
        }
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
            bookingDTO.setCheckInDate(Utils.convertLocalDateToString(booking.getCheckInDate(), "dd/MM/yyyy"));
            bookingDTO.setCheckOutDate(Utils.convertLocalDateToString(booking.getCheckOutDate(), "dd/MM/yyyy"));
            bookingDTO.getCustomer().setBookings(booking.getCustomer().getBookings());
            bookingDTO.getRoom().setBookings(booking.getRoom().getBookings());
            bookingDTOs.add(bookingDTO);
        }
        return bookingDTOs;
    }
}
