package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.SubscriptionDTO;
import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.entities.Subscription;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.mappers.SubscriptionMapper;
import com.openclassrooms.mddapi.mappers.ThemeMapper;
import com.openclassrooms.mddapi.repositories.SubscriptionRepository;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public ThemeService(ThemeRepository themeRepository, ThemeMapper themeMapper, UserRepository userRepository,
            SubscriptionRepository subscriptionRepository,
            SubscriptionMapper subscriptionMapper) {
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
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

    @Transactional
    public SubscriptionDTO subscribeUserToTheme(Integer themeId, String email) {
        Optional<Theme> themeOpt = themeRepository.findById(themeId);
        User user = userRepository.findByEmail(email);

        if (themeOpt.isEmpty()) {
            throw new RuntimeException("Thème introuvable.");
        }
        Theme theme = themeOpt.get();

        Optional<Subscription> existingSubscription = subscriptionRepository.findByThemeAndUser(theme, user);
        if (existingSubscription.isPresent()) {
            throw new RuntimeException("L'utilisateur est déjà abonné à ce thème.");
        }
        Subscription subscription = new Subscription();
        subscription.setTheme(theme);
        subscription.setUser(user);
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        return subscriptionMapper.toDto(savedSubscription);
    }
}
