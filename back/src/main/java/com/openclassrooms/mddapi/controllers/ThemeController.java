package com.openclassrooms.mddapi.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.SubscriptionDTO;
import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.services.ThemeService;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public List<ThemeDTO> getAll() {
        return themeService.getAllThemes();
    }

    @PostMapping
    public ThemeDTO createTheme(@RequestBody ThemeDTO dto) {
        return themeService.createTheme(dto);
    }

    @PostMapping("/{id}/subscribe")
    public ResponseEntity<SubscriptionDTO> subscribeToTheme(@PathVariable Integer id, Principal principal) {
        SubscriptionDTO subscription = themeService.subscribeUserToTheme(id, principal.getName());
        return ResponseEntity.ok(subscription);
    }
}