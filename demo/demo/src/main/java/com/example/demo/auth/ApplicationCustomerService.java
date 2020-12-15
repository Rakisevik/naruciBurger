package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationCustomerService implements UserDetailsService {

    private final ApplicationCustomerDao applicationCustomerDao;

    @Autowired
    public ApplicationCustomerService(@Qualifier("fake") ApplicationCustomerDao applicationCustomerDao) {
        this.applicationCustomerDao = applicationCustomerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationCustomerDao.selectApplicationCustomerByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s not fount",username)));
    }
}
