package com.example.demo.customer;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/customers")
public class CustomerManagementController {

    private static final List<Customer> CUSTOMERS = Arrays.asList(
            new Customer(1, "Stefan","Rakicevic","069727818","stefan.rakicevic.4228@metropolitan.ac.rs"),
            new Customer(2,"Nikola","Veljic","063377380","nikola.veljic.3393@metropolitan.ac.rs")
    );

//  hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Customer> getAllCustomers() {
        System.out.println("getAllCustomers");
        return CUSTOMERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('customer:write')")
    public void registerNewCustomer(@RequestBody Customer customer) {
        System.out.println("registerCustomer");
        System.out.println(customer);
    }

    @DeleteMapping(path = "{customerId}")
    @PreAuthorize("hasAuthority('customer:write')")
    public void deleteCustomer (@PathVariable("customerId") Integer customerId) {
        System.out.println("deleteCustomer");
        System.out.println(customerId);
    }


    @PutMapping(path = "{customerId}")
    @PreAuthorize("hasAuthority('customer:write')")
    public void updateCustomer(@PathVariable("customerId") Integer customerId,@RequestBody Customer customer) {
        System.out.println("updateCustomer");
        System.out.println(String.format("%s %s", customerId, customer));
    }
}
