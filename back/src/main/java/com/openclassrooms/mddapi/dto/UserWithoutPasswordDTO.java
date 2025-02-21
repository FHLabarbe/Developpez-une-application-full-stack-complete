package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutPasswordDTO {

    private Integer id;

    @NotNull
    private String username;

    @NonNull
    private String email;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}
