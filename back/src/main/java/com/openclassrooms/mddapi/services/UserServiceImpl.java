package com.openclassrooms.mddapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.dto.UserWithoutPasswordDTO;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserWithoutPasswordDTO findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User userPresent = user.get();
            UserWithoutPasswordDTO userDTO = new UserWithoutPasswordDTO(userPresent.getEmail());
            userDTO.setId(id);
            userDTO.setUsername(userPresent.getUsername());
            userDTO.setCreated_at(userPresent.getCreated_at());
            userDTO.setUpdated_at(userPresent.getUpdated_at());
            return userDTO;
        }
        return null;
    }
}