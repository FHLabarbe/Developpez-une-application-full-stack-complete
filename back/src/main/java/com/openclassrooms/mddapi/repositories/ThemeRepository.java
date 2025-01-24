package com.openclassrooms.mddapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.entities.Theme;

public interface ThemeRepository extends JpaRepository<Theme,Integer> {
    
}
