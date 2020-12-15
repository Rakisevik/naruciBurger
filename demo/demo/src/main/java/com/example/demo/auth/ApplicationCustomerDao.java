package com.example.demo.auth;

import java.util.Optional;


public interface ApplicationCustomerDao {

    Optional<ApplicationCustomer> selectApplicationCustomerByUsername(String username);

}
