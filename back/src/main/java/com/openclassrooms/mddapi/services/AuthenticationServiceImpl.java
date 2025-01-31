package com.openclassrooms.mddapi.services;

import java.security.Principal;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.entities.UserDetailsImpl;
import com.openclassrooms.mddapi.repositories.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void register(UserDTO userDTO) {
      if (userRepository.findByEmail(userDTO.getEmail()) != null) {
        throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
      }
      userDTO.setCreated_at(LocalDateTime.now());
      userDTO.setUpdated_at(LocalDateTime.now());
      userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
      User user = modelMapper.map(userDTO, User.class);
      userRepository.save(user);
    }

    @Override
    public String login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return jwtService.generateToken(userDTO);
        }
        throw new RuntimeException("Invalid Credentials");
    }

  public UserDTO getCurrentUser(Principal principal) {
    if (principal == null) {
      throw new AccessDeniedException("User is not authenticated");
    }

    String email = principal.getName();
    UserDetailsImpl userDetails = (UserDetailsImpl) customUserDetailsService.loadUserByUsername(email);

    UserDTO userDTO = new UserDTO(userDetails.getEmail(), email);
    userDTO.setId(userDetails.getId());
    userDTO.setUsername(userDetails.getName());
    userDTO.setUpdated_at(userDetails.getUpdatedAt());
    userDTO.setCreated_at(userDetails.getCreatedAt());

    return userDTO;
    }
  }
