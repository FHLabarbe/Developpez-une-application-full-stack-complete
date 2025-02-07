package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.entities.Article;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDTO toDto(Article entity);

    Article toEntity(ArticleDTO dto);

    List<ArticleDTO> toDtoList(List<Article> entities);
}