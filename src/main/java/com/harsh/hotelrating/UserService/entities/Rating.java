package com.harsh.hotelrating.UserService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingId;
    private UUID userId;
    private String hotelId;
    private Integer rating;
    private String feedback;
}
