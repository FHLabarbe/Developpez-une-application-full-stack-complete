package com.openclassrooms.mddapi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {
    private Integer id;
    private Integer themeId;
    private Integer userId;
}