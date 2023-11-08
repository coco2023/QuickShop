package com.umistore.imsys.security.service;

import com.umistore.imsys.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Your user retrieval logic here
        if ("manager".equals(username)) {
            return User
                    .withUsername("manager")
                    .password("password") // password should be encoded
                    .authorities("ROLE_MANAGER")
                    .build();
        } else if ("customer".equals(username)) {
            return User
                    .withUsername("customer")
                    .password("password") // password should be encoded
                    .authorities("ROLE_CUSTOMER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
