package com.burger.narucibruger.service;

import com.burger.narucibruger.dto.UserRegistrationDto;
import com.burger.narucibruger.model.ERole;
import com.burger.narucibruger.model.Role;
import com.burger.narucibruger.model.User;
import com.burger.narucibruger.repository.RoleRepository;
import com.burger.narucibruger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;

@CrossOrigin(origins = "http://localhost:3000")
@Service
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getName(),
                registrationDto.getSurname(),registrationDto.getMail(),
                registrationDto.getPhone(),registrationDto.getAddress(),
                registrationDto.getUsername(),passwordEncoder.encode(registrationDto.getPassword()),
                roleRepository.getOne(1L));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),Arrays.asList(mapRolesToAuthorities(user.getRole())));
    }

    private GrantedAuthority mapRolesToAuthorities(Role role) {

        return new SimpleGrantedAuthority(role.getRole().toString());

    }
}
