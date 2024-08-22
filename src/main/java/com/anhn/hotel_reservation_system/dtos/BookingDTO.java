package com.anhn.hotel_reservation_system.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private CustomerDTO customer;
    private RoomDTO room;
}
