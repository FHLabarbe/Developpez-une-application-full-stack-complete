package com.openclassrooms.mddapi.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.entities.Theme;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    ThemeDTO toDto(Theme entity);

    Theme toEntity(ThemeDTO dto);

    List<ThemeDTO> toDtoList(List<Theme> entities);
}
