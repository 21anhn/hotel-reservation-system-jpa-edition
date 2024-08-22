package com.anhn.hotel_reservation_system.services.impl;

import com.anhn.hotel_reservation_system.entities.Booking;
import com.anhn.hotel_reservation_system.entities.Customer;
import com.anhn.hotel_reservation_system.entities.Room;
import com.anhn.hotel_reservation_system.repositories.BookingRepository;
import com.anhn.hotel_reservation_system.services.BookingService;
import com.anhn.hotel_reservation_system.services.CustomerService;
import com.anhn.hotel_reservation_system.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public boolean bookingRoom(Long customerId, Long roomId) {
        Customer customerInDb = customerService.getCustomer(customerId);
        Room roomIbDb = roomService.getRoomById(roomId);

        if(customerInDb == null || roomIbDb == null) {
            return false;
        }

        Booking booking = new Booking();
        booking.setCustomer(customerInDb);
        booking.setRoom(roomIbDb);
        bookingRepository.booking(booking);
        return true;
    }
}
