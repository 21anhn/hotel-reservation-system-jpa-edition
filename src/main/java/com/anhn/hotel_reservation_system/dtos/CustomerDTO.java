package com.anhn.hotel_reservation_system.dtos;

import com.anhn.hotel_reservation_system.entities.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name, address, email;
    private List<Booking> bookings;
}
