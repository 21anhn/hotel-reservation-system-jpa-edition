package com.anhn.hotel_reservation_system.controllers;

import com.anhn.hotel_reservation_system.entities.Customer;
import com.anhn.hotel_reservation_system.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Customer c = customerService.getCustomer(id);
        if(c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Customer> customers = customerService.getCustomers();
        if(customers == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer) {
        Customer c = customerService.updateCustomer(id, customer);
        if(c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean check = customerService.deleteCustomer(id);
        if(check) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
