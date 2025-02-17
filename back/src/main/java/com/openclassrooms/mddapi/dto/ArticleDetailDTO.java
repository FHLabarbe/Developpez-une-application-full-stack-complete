package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailDTO {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Integer authorId;
    private String authorUsername;
    private Integer themeId;
    private String themeName;
    private List<CommentDTO> comments;
}