package com.anhn.hotel_reservation_system.dtos;

import com.anhn.hotel_reservation_system.entities.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private String roomNumber, type;
    private List<Booking> bookings;
}
