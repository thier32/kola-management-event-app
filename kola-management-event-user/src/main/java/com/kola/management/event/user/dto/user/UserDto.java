package com.kola.management.event.user.dto.user;

public record UserDto(
        String firstname,
        String lastname,
        String email,
        String username,
        String password,
        String confirmpassword
) implements IUserDto {
    public UserDto(){
        this(null,null,null,null,null,null);
    }
}
