package com.kola.management.event.user.dto.user;

import java.util.ArrayList;
import java.util.List;

public record UserDto(
        Long userId,
        String firstname,
        String lastname,
        String email,
        String username,
        String password,
        String confirmpassword,
        Boolean isadmin,
        List<String> roles
) implements IUserDto {
    public UserDto(){
        this(null,null,null,null,null,null,null,null,new ArrayList<>(0));
    }
    public UserDto(boolean isadmin){
        this(null,null,null,null,null,null,null,isadmin,new ArrayList<>(0));
    }
    public UserDto(boolean isadmin,List<String> roles){
        this(null,null,null,null,null,null,null,isadmin,roles);
    }
}
