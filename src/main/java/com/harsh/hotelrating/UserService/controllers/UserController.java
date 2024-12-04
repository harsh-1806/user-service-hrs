package com.harsh.hotelrating.UserService.controllers;

import com.harsh.hotelrating.UserService.dtos.UserDTO;
import com.harsh.hotelrating.UserService.entities.User;
import com.harsh.hotelrating.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable(name = "userId")
            String userId
    ) {
        return new ResponseEntity<>(userService.getUser(UUID.fromString(userId)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> saveUser(
            @RequestBody
            UserDTO userDTO
    ) {
        userService.saveUser(mapToUser.apply(userDTO));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    private final Function<UserDTO, User> mapToUser = (userDTO) -> {
        if(Objects.nonNull(userDTO)) {
            return User.builder()
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .phone(userDTO.getPhone())
                    .username(userDTO.getUsername())
                    .ratings(userDTO.getRatings())
                    .build();
        }

        return null;
    };
}
