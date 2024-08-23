package com.anhn.hotel_reservation_system.repositories;

import com.anhn.hotel_reservation_system.entities.Booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void updateBooking(Booking booking) {
        em.merge(booking);
    }

    public void deleteBooking(Booking booking) {
        em.remove(booking);
    }

    public List<Booking> getAll() {
        return em.createQuery("FROM Booking", Booking.class).getResultList();
    }
}
