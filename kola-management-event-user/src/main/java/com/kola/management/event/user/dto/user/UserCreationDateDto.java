package com.kola.management.event.user.dto.user;


import java.time.LocalDateTime;

public record UserCreationDateDto(LocalDateTime creationDate) implements IUserDto {
}
