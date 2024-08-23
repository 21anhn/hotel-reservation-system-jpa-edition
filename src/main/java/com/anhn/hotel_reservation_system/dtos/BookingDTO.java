package com.anhn.hotel_reservation_system.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private String checkInDate, checkOutDate;
    private CustomerDTO customer;
    private RoomDTO room;
}
