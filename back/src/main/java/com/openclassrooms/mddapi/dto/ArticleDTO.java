package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private Integer id;

    @NotNull
    @Size(max = 200)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Integer themeId;

    private Integer authorId;

    private LocalDateTime createdAt;
}