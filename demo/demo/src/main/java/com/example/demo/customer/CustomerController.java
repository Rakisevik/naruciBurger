package com.example.demo.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private static final List<Customer> CUSTOMERS = Arrays.asList(
            new Customer(1, "Stefan","Rakicevic","069727818","stefan.rakicevic.4228@metropolitan.ac.rs"),
            new Customer(2,"Nikola","Veljic","063377380","nikola.veljic.3393@metropolitan.ac.rs")
    );

    @GetMapping(path = "{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) {
        return CUSTOMERS.stream()
                .filter(customer -> customerId.equals(customer.getCustomerId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException(" Customer " + customerId + "does not exists"));
    }
}
