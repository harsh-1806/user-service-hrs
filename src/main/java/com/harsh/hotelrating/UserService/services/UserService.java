package com.harsh.hotelrating.UserService.services;


import com.harsh.hotelrating.UserService.dtos.UserDTO;
import com.harsh.hotelrating.UserService.entities.User;

import java.util.List;
import java.util.UUID;


public interface UserService {
    // create
    void saveUser(User user);

    // getAllUsers
    List<UserDTO> getAllUsers();

    // getSingleUserWithUserId
    UserDTO getUser(UUID userId);

    // deleteUser
    UserDTO deleteUser(UUID userId);

    // updateUser
    UserDTO updateUser(UUID userId, UserDTO userDTO);
}
