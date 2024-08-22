package com.anhn.hotel_reservation_system.services.impl;

import com.anhn.hotel_reservation_system.entities.Customer;
import com.anhn.hotel_reservation_system.repositories.CustomerRepository;
import com.anhn.hotel_reservation_system.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerInDB = getCustomer(id);
        if(customerInDB == null) {
            return null;
        }
        modelMapper.map(customer, customerInDB);
        customerRepository.update(customerInDB);
        return customerInDB;
    }

    @Override
    public boolean deleteCustomer(Long id) {
        customerRepository.delete(id);
        return getCustomer(id) == null;
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getAll();
    }
}
