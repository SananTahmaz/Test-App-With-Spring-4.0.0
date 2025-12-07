package org.devconnect.app.dtos.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
