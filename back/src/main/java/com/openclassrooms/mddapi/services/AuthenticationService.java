package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.UserDTO;

public interface AuthenticationService {
    void register(UserDTO userDTO);

    String login(UserDTO userDTO);
}
