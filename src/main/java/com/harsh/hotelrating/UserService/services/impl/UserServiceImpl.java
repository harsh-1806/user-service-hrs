package com.harsh.hotelrating.UserService.services.impl;

import com.harsh.hotelrating.UserService.dtos.UserDTO;
import com.harsh.hotelrating.UserService.entities.User;
import com.harsh.hotelrating.UserService.exceptions.ResourceNotFoundException;
import com.harsh.hotelrating.UserService.repositories.UserRepository;
import com.harsh.hotelrating.UserService.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        mapToUserDto.apply(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(mapToUserDto).toList();
    }

    @Override
    public UserDTO getUser(UUID userId) {
        User user = null;
        try {
            user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found with userId : " + userId));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User Not found with userId : " + userId.toString());
        }
        return mapToUserDto.apply(user);
    }

    @Override
    public UserDTO deleteUser(UUID userId) {
        User user = userRepository.getReferenceById(userId);
        userRepository.deleteById(userId);
        return mapToUserDto.apply(user);
    }

    @Override
    public UserDTO updateUser(UUID userId, UserDTO userDTO) {
        User user = userRepository.getReferenceById(userId);

        if(userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if(userDTO.getPhone() != null) {
            user.setPhone(userDTO.getPhone());
        }
        if(userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }

        User savedUser = userRepository.save(user);

        return mapToUserDto.apply(savedUser);
    }

    private final Function<User, UserDTO> mapToUserDto = (user) -> {
        if(Objects.nonNull(user)) {
            return new UserDTO(
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getRatings()
            );
        }

        return null;
    };
}
