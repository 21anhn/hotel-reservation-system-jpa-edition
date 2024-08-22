package com.anhn.hotel_reservation_system.dtos;

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
    private List<BookingDTO> bookings;
}
