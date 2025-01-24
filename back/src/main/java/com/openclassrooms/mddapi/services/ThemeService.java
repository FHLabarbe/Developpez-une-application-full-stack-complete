package com.openclassrooms.mddapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.mappers.ThemeMapper;
import com.openclassrooms.mddapi.repositories.ThemeRepository;

@Service
public class ThemeService {

    private ThemeRepository themeRepository;
    private ThemeMapper themeMapper;

    public ThemeService(ThemeRepository themeRepository, ThemeMapper themeMapper) {
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
    }

    public List<ThemeDTO> getAllThemes() {
        List<Theme> entities = themeRepository.findAll();
        return themeMapper.toDtoList(entities);
    }

    public ThemeDTO createTheme(ThemeDTO dto) {
        Theme entity = themeMapper.toEntity(dto);
        Theme saved = themeRepository.save(entity);
        return themeMapper.toDto(saved);
    }
}
