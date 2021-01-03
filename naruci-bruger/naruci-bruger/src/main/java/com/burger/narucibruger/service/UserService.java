package com.burger.narucibruger.service;

import com.burger.narucibruger.dto.UserRegistrationDto;
import com.burger.narucibruger.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
