package com.anhn.hotel_reservation_system.controllers;

import com.anhn.hotel_reservation_system.dtos.CustomerDTO;
import com.anhn.hotel_reservation_system.entities.Customer;
import com.anhn.hotel_reservation_system.services.CustomerService;
import jakarta.annotation.Nullable;
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
        CustomerDTO c = customerService.getCustomer(id);
        if(c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomers(@RequestParam @Nullable String name, @RequestParam @Nullable String address, @RequestParam @Nullable String email) {
        List<Customer> customers = customerService.search(name, address, email);
        if(customers != null) {
            return ResponseEntity.ok(customers);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<CustomerDTO> customers = customerService.getCustomers();
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
        CustomerDTO c = customerService.updateCustomer(id, customer);
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
