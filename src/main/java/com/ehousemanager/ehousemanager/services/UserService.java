package com.ehousemanager.ehousemanager.services;

import com.ehousemanager.ehousemanager.dtos.users.CreateUserDto;
import com.ehousemanager.ehousemanager.dtos.users.UserDto;
import com.ehousemanager.ehousemanager.entities.User;
import com.ehousemanager.ehousemanager.exceptions.UserException;
import com.ehousemanager.ehousemanager.mappers.UserMapper;
import com.ehousemanager.ehousemanager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_ALREADY_EXISTS_TEMPLATE = "User already exists - email: %s";
    private static final String USER_NOT_FOUND_ID_TEMPLATE = "User not found - id: %s";
    private static final String USER_NOT_FOUND_EMAIL_TEMPLATE = "User not found - email: %s";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto create(CreateUserDto request) {
        validateUser(request.email());

        return userMapper.toDto(userRepository.save(userMapper.toEntity(request)));
    }

    public Page<UserDto> readAll(Pageable pageable) {
        return userRepository.findAllByOrderByName(pageable)
                .map(userMapper::toDto);
    }

    public UserDto read(UUID id) {
        return userMapper.toDto(getById(id));
    }

    public UserDto searchByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserException(String.format(USER_NOT_FOUND_EMAIL_TEMPLATE, email)));
    }

    public Page<UserDto> searchByNameLike(String name, Pageable pageable) {
        return userRepository.findByNameLikeIgnoreCaseOrderByName(name, pageable)
                .map(userMapper::toDto);
    }

    public UserDto update(UUID id, CreateUserDto request) {
        User user = getById(id);

        String email = request.email();
        if (!email.equals(user.getEmail())) {
            validateUser(email);
        }

        return userMapper.toDto(userRepository.save(userMapper.update(request, user)));
    }

    private User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException(String.format(USER_NOT_FOUND_ID_TEMPLATE, id)));
    }

    private void validateUser(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserException(String.format(USER_ALREADY_EXISTS_TEMPLATE, email));
        }
    }
}
