package org.devconnect.app.controllers;

import lombok.RequiredArgsConstructor;
import org.devconnect.app.common.ApiResponse;
import org.devconnect.app.dtos.user.UserCreateDto;
import org.devconnect.app.dtos.user.UserDto;
import org.devconnect.app.dtos.user.UserUpdateDto;
import org.devconnect.app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<UserDto> create(@RequestBody UserCreateDto userCreateDto) {
        UserDto userDto = userService.create(userCreateDto);
        return ApiResponse.success(userDto, "User was created successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDto> getById(@PathVariable Long id) {
        UserDto user = userService.getById(id);
        return ApiResponse.success(user, "User was fetched successfully");
    }

    @GetMapping
    public ApiResponse<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAll();
        return ApiResponse.success(users, "Users were fetched successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> update(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        UserDto updated = userService.update(id, userUpdateDto);
        return ApiResponse.success(updated, "User was updated successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ApiResponse.success(null, "User was deleted successfully");
    }
}