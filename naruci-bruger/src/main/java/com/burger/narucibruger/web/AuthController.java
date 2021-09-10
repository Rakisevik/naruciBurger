package com.burger.narucibruger.web;

import com.burger.narucibruger.config.JwtTokenProvider;
import com.burger.narucibruger.dto.UserLoginRequestDto;
import com.burger.narucibruger.dto.UserRegistrationDto;
import com.burger.narucibruger.repository.UserRepository;
import com.burger.narucibruger.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@CrossOrigin(origins = "*")

public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserDetailsService userService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDto data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, Arrays.asList(this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRole().toString()));
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Greska sa loginom");
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
        try {
            userService.save(registrationDto);
            return ok("Successfully registered");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Greska sa registracijom");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@AuthenticationPrincipal UserDetails userDetails) {
        return ok("TODO");
    }
}
