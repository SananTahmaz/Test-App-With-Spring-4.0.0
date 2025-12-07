package org.devconnect.app.mapper.user;

import org.devconnect.app.dtos.user.UserCreateDto;
import org.devconnect.app.dtos.user.UserDto;
import org.devconnect.app.dtos.user.UserUpdateDto;
import org.devconnect.app.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedDate", ignore = true)
    User toCreateEntity(UserCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", expression = "java(java.time.LocalDateTime.now())")
    User toUpdateEntity(UserUpdateDto dto);

    UserDto toDto(User user);
}
