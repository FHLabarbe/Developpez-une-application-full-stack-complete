package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO {

    private Integer id;

    @NotNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String password;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
