package com.anhn.hotel_reservation_system.repositories;

import com.anhn.hotel_reservation_system.entities.Booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class BookingRepository {

    @PersistenceContext
    private EntityManager em;

    public void booking(Booking booking) {
        em.persist(booking);
    }

    public Booking getBooking(Long id) {
        return em.find(Booking.class, id);
    }

    public Booking updateBooking(Booking booking) {
        return em.merge(booking);
    }
}
