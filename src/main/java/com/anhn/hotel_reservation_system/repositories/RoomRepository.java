package com.anhn.hotel_reservation_system.repositories;

import com.anhn.hotel_reservation_system.entities.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class RoomRepository {

    @PersistenceContext
    private EntityManager em;

    //Create
    public void save(Room room) {
        em.persist(room);
    }

    public void update(Room room) {
        em.merge(room);
    }

    public void delete(Room room) {
        em.remove(room);
    }

    public Room findById(Long id) {
        return em.find(Room.class, id);
    }

    public List<Room> findAll() {
        return em.createQuery("FROM Room", Room.class).getResultList();
    }
}
