package com.harsh.hotelrating.UserService.dtos;

import com.harsh.hotelrating.UserService.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;

    private String email;

    private String password;

    private String phone;

    private List<Rating> ratings;
}
