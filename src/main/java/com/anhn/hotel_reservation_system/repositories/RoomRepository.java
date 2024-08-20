package com.anhn.hotel_reservation_system.repositories;

import com.anhn.hotel_reservation_system.entities.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class RoomRepository {

    @PersistenceContext
    private EntityManager em;

    //Create/Update
    public void save(Room room) {
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
