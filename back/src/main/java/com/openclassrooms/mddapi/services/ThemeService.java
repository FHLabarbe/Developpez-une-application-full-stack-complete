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

    public List<SubscriptionDTO> getSubscriptionsByUserId(String userEmail){
        User user = userRepository.findByEmail(userEmail);
        Optional<List<Subscription>> subscriptionsOpt = subscriptionRepository.findByUser(user);
        if (subscriptionsOpt.isEmpty()){
            throw new RuntimeException("Aucun abonnement trouvé");
        }
        List<Subscription> subscriptions = subscriptionsOpt.get();
        return subscriptionMapper.toDtoList(subscriptions);
    }

    @Transactional
    public SubscriptionDTO subscribeUserToTheme(Integer themeId, String email) {
        Optional<Theme> themeOpt = themeRepository.findById(themeId);
        if (themeOpt.isEmpty()) {
            throw new RuntimeException("Thème introuvable.");
        }
        Theme theme = themeOpt.get();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Utilisateur introuvable.");
        }

        Optional<Subscription> existing = subscriptionRepository.findByThemeAndUser(theme, user);
        if (existing.isPresent()) {
            throw new RuntimeException("L'utilisateur est déjà abonné à ce thème.");
        }

        Subscription subscription = new Subscription();
        subscription.setTheme(theme);
        subscription.setUser(user);

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        return subscriptionMapper.toDto(savedSubscription);
    }

    @Transactional
    public void unsubscribeUserFromTheme(Integer themeId, String email) {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Thème introuvable"));

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Utilisateur introuvable");
        }

        Optional<Subscription> existingSubscription = subscriptionRepository.findByThemeAndUser(theme, user);
        if (existingSubscription.isEmpty()) {
            throw new RuntimeException("Aucun abonnement trouvé pour ce thème et cet utilisateur");
        }

        subscriptionRepository.delete(existingSubscription.get());
    }
}
