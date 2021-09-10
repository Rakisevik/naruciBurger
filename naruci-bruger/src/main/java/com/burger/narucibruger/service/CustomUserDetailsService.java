package com.burger.narucibruger.service;

import com.burger.narucibruger.dto.UserRegistrationDto;
import com.burger.narucibruger.model.User;
import com.burger.narucibruger.repository.RoleRepository;
import com.burger.narucibruger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository users, RoleRepository roles) {
        this.userRepository = users;
        this.roleRepository = roles;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getName(),
                registrationDto.getSurname(),registrationDto.getMail(),
                registrationDto.getPhone(),registrationDto.getAddress(),
                registrationDto.getUsername(),
                passwordEncoder.encode(registrationDto.getPassword()),
                roleRepository.getOne(1L));
        return userRepository.save(user);
    }
}