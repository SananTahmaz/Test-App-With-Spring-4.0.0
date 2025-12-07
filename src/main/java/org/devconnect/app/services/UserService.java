package org.devconnect.app.services;

import org.devconnect.app.dtos.user.UserCreateDto;
import org.devconnect.app.dtos.user.UserDto;
import org.devconnect.app.dtos.user.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto create(UserCreateDto userCreateDto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto update(Long id, UserUpdateDto userUpdateDto);
    void delete(Long id);
}
