package com.openclassrooms.mddapi.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.entities.UserDetailsImpl;
import com.openclassrooms.mddapi.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(),
                user.getUpdated_at(), user.getCreated_at());
    }
}
