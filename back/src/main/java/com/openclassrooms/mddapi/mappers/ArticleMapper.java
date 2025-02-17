package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.ArticleDetailDTO;
import com.openclassrooms.mddapi.entities.Article;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ThemeMapper.class})
public interface ArticleMapper {

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorUsername", source = "author.username")
    @Mapping(target = "themeId", source = "theme.id")
    @Mapping(target = "themeName", source = "theme.name")
    ArticleDetailDTO toDetailDto(Article article);

    List<ArticleDetailDTO> toDetailDtoList(List<Article> articles);

    ArticleDTO toDto(Article entity);

    Article toEntity(ArticleDTO dto);

    List<ArticleDTO> toDtoList(List<Article> entities);
}