package com.anhn.hotel_reservation_system.repositories;

import com.anhn.hotel_reservation_system.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public Customer getById(Long id) {
        return em.find(Customer.class, id);
    }

    public void save(Customer customer) {
        em.persist(customer);
    }

    public List<Customer> getAll() {
        return em.createQuery("FROM Customer", Customer.class).getResultList();
    }

    public void update(Customer customer) {
        em.merge(customer);
    }

    public void delete(Long id) {
        em.remove(getById(id));
    }

    public List<Customer> getCustomersByName(String name) {
        String sql = "FROM Customer WHERE name LIKE :name";
        TypedQuery<Customer> query = em.createQuery(sql, Customer.class)
                .setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    public List<Customer> getCustomersByAddress(String address) {
        String sql = "FROM Customer WHERE address LIKE :address";
        TypedQuery<Customer> query = em.createQuery(sql, Customer.class)
                .setParameter("address", "%" + address + "%");
        return query.getResultList();
    }

    public List<Customer> getCustomersByEmail(String email) {
        String sql = "FROM Customer WHERE email LIKE :email";
        TypedQuery<Customer> query = em.createQuery(sql, Customer.class)
                .setParameter("email", "%" + email + "%");
        return query.getResultList();
    }

    public Customer login(String email, String password) {
        String sql = "FROM Customer WHERE email = :email AND password = :password";
        TypedQuery<Customer> query = em.createQuery(sql, Customer.class)
                .setParameter("email", email)
                .setParameter("password", password);
        return query.getSingleResult();
    }
}
