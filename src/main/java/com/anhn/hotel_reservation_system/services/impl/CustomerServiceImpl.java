package com.anhn.hotel_reservation_system.services.impl;

import com.anhn.hotel_reservation_system.dtos.CustomerDTO;
import com.anhn.hotel_reservation_system.entities.Customer;
import com.anhn.hotel_reservation_system.repositories.CustomerRepository;
import com.anhn.hotel_reservation_system.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public CustomerDTO updateCustomer(Long id, Customer customer) {
        Customer customerInDB = customerRepository.getById(id);
        if(customerInDB == null) {
            return null;
        }
        modelMapper.map(customer, customerInDB);
        customerRepository.update(customerInDB);
        return modelMapper.map(customerInDB, CustomerDTO.class);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        customerRepository.delete(id);
        return getCustomer(id) == null;
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        Customer c =  customerRepository.getById(id);
        CustomerDTO customerDTO = modelMapper.map(c, CustomerDTO.class);
        customerDTO.setBookings(c.getBookings());
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customerList = customerRepository.getAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setAddress(customer.getAddress());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setBookings(customer.getBookings());
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.getCustomersByName(name);
    }

    @Override
    public List<Customer> getCustomersByAddress(String address) {
        return customerRepository.getCustomersByName(address);
    }

    @Override
    public List<Customer> getCustomersByEmail(String email) {
        return customerRepository.getCustomersByEmail(email);
    }

    @Override
    public List<Customer> search(String name, String address, String email) {
        if (name != null && !name.isEmpty()) {
            return customerRepository.getCustomersByName(name);
        }
        if (address != null && !address.isEmpty()) {
            return getCustomersByAddress(address);
        }
        if (email != null && !email.isEmpty()) {
            return getCustomersByEmail(email);
        }
        return null;
    }

    @Override
    public CustomerDTO login(String email, String password) {
        Customer c = customerRepository.login(email, password);
        if (c == null) {
            return null;
        }
        return modelMapper.map(c, CustomerDTO.class);
    }
}
