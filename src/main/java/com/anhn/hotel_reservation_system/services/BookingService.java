package com.anhn.hotel_reservation_system.services;

import com.anhn.hotel_reservation_system.entities.Customer;
import com.anhn.hotel_reservation_system.entities.Room;

public interface BookingService {
    boolean bookingRoom(Long customerId, Long roomId);
}
