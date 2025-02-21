package com.openclassrooms.mddapi.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserWithoutPasswordDTO;
import com.openclassrooms.mddapi.services.UserServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/me")
public class MeController {
    private final UserServiceImpl userServiceImpl;

    public MeController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public UserWithoutPasswordDTO getUserInformations(Principal principal) {
        return userServiceImpl.findByEmail(principal.getName());
    }

    @PutMapping
    public UserWithoutPasswordDTO updateUser(@RequestBody UserWithoutPasswordDTO userDTO, Principal principal) {
        return userServiceImpl.updateUser(principal.getName(), userDTO);
    }


}
