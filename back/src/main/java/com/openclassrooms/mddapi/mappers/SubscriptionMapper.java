package com.openclassrooms.mddapi.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.openclassrooms.mddapi.dto.SubscriptionDTO;
import com.openclassrooms.mddapi.entities.Subscription;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionDTO toDto(Subscription entity);

    Subscription toEntity(SubscriptionDTO dto);

    List<SubscriptionDTO> toDtoList(List<Subscription> entities);
}
