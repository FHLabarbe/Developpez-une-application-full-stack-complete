package com.openclassrooms.mddapi.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.mddapi.dto.SubscriptionDTO;
import com.openclassrooms.mddapi.entities.Subscription;

@Mapper(componentModel = "spring", uses = { ThemeMapper.class })
public interface SubscriptionMapper {

    @Mapping(target = "userId", source = "user.id")
    SubscriptionDTO toDto(Subscription entity);
    
    @Mapping(target = "theme", ignore = true)
    @Mapping(target = "user", ignore = true)
    Subscription toEntity(SubscriptionDTO dto);

    List<SubscriptionDTO> toDtoList(List<Subscription> entities);
}
