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
    List<Customer> getCustomersByName(String name);
    List<Customer> getCustomersByAddress(String address);
    List<Customer> getCustomersByEmail(String email);
    List<Customer> search(String name, String address, String email);
    CustomerDTO login(String email, String password);
}
