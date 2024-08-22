package com.anhn.hotel_reservation_system.services;

import com.anhn.hotel_reservation_system.entities.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    boolean deleteCustomer(Long id);
    Customer getCustomer(Long id);
    List<Customer> getCustomers();
}
