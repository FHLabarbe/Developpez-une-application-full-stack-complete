package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.UserWithoutPasswordDTO;

public interface UserService {
    public UserWithoutPasswordDTO findUserById(Integer id);
}
