package com.anhn.hotel_reservation_system.services;

import com.anhn.hotel_reservation_system.dtos.CustomerDTO;
import com.anhn.hotel_reservation_system.entities.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);
    CustomerDTO updateCustomer(Long id, Customer customer);
    boolean deleteCustomer(Long id);
    CustomerDTO getCustomer(Long id);
    List<CustomerDTO> getCustomers();
}
