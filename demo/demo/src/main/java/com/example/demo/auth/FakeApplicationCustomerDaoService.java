package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationCustomerRole.ADMIN;
import static com.example.demo.security.ApplicationCustomerRole.CUSTOMER;

@Repository("fake")
public class FakeApplicationCustomerDaoService implements ApplicationCustomerDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationCustomerDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationCustomer> selectApplicationCustomerByUsername(String username) {
        return getApplicationCustomers()
                .stream()
                .filter(applicationCustomer -> username.equals(applicationCustomer.getUsername()))
                .findFirst();
    }

    private List<ApplicationCustomer> getApplicationCustomers() {
        List<ApplicationCustomer> applicationCustomers = Lists.newArrayList(
                new ApplicationCustomer(
                        CUSTOMER.getGrantedAuthorities(),
                        passwordEncoder.encode("Stefan"),
                        "Rakicevic",
                        true,
                        true,
                        true,
                        true

                ),

                new ApplicationCustomer(
                        ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("password123"),
                        "Veljic",
                        true,
                        true,
                        true,
                        true

                )
        );

        return applicationCustomers;
    }
}
