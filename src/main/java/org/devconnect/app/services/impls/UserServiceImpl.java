package org.devconnect.app.services.impls;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.devconnect.app.constants.ExceptionMessage;
import org.devconnect.app.dtos.user.UserCreateDto;
import org.devconnect.app.dtos.user.UserDto;
import org.devconnect.app.dtos.user.UserUpdateDto;
import org.devconnect.app.entities.User;
import org.devconnect.app.exceptions.NotFoundException;
import org.devconnect.app.mapper.user.UserMapper;
import org.devconnect.app.repositories.UserRepository;
import org.devconnect.app.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        User user = userMapper.toCreateEntity(userCreateDto);
        User createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessage.USER_NOT_FOUND, id)));

        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        User existUser = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessage.USER_NOT_FOUND, id)));

        userMapper.toUpdateEntity(userUpdateDto, existUser);
        User updatedUser = userRepository.save(existUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
        boolean isExistUser = userRepository.existsById(id);
        if (!isExistUser) {
            throw new NotFoundException(String.format(ExceptionMessage.USER_NOT_FOUND, id));
        }
        userRepository.deleteById(id);
    }
}
