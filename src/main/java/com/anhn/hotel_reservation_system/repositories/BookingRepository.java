package com.anhn.hotel_reservation_system.repositories;

import com.anhn.hotel_reservation_system.entities.Booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    public List<Booking> findBookingByCustomerIdAndFromDateToEndDate(Long id, LocalDate checkInDate, LocalDate checkOutDate) {
        StringBuilder queryBuilder = new StringBuilder("FROM Booking b WHERE b.customer.id = :id");
        if (checkInDate != null) {
            queryBuilder.append(" AND b.checkInDate >= :checkInDate");
        }
        if (checkOutDate != null) {
            queryBuilder.append(" AND b.checkOutDate <= :checkOutDate");
        }

        TypedQuery<Booking> query = em.createQuery(queryBuilder.toString(), Booking.class);
        query.setParameter("id", id);

        if (checkInDate != null) {
            query.setParameter("checkInDate", checkInDate);
        }
        if (checkOutDate != null) {
            query.setParameter("checkOutDate", checkOutDate);
        }

        return query.getResultList();
    }


}
