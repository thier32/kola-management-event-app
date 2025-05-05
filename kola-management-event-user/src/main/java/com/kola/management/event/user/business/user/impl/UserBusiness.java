package com.kola.management.event.user.business.user.impl;

import com.kola.management.event.kernel.model.BaseKernelModel;
import com.kola.management.event.user.business.exceptions.UserBusinessException;
import com.kola.management.event.user.business.user.IUserBusiness;
import com.kola.management.event.user.dto.user.UserDto;
import com.kola.management.event.user.dto.user.UserReturnDto;
import com.kola.management.event.user.model.Role;
import com.kola.management.event.user.model.User;
import com.kola.management.event.user.services.IRoleService;
import com.kola.management.event.user.services.IUserService;
import com.kola.management.event.user.services.exceptions.UserServiceException;
import com.kola.management.event.user.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness implements IUserBusiness {
    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Override
    public UserReturnDto createUser(UserDto userDto) throws UserBusinessException {

        UserReturnDto userReturnDto = null;
        try{
            User user;
            String ROLE_ADMIN = "ROLE_ADMIN";
            List<String> roles = new ArrayList<>(0);
            roles.add(ROLE_ADMIN);
            UserDto actualUserDto = userDto;
            if (userDto.isadmin() != null){
                actualUserDto = new UserDto(
                        null,
                        userDto.firstname(),
                        userDto.lastname(),
                        userDto.email(),
                        userDto.username(),
                        userDto.password(),
                        userDto.confirmpassword(),
                        userDto.isadmin(),
                        roles
                );
            }

            if (actualUserDto.userId() != null){
                user = userService.updateUser(actualUserDto,actualUserDto.userId());
            }else{
                user = userService.createUser(actualUserDto);
            }
            if (user != null){
                userReturnDto = map(user);
            }
        }catch (UserServiceException userServiceException){
            throw new UserBusinessException(userServiceException.getMessage());
        }
        return userReturnDto;
    }

    UserReturnDto map(BaseKernelModel model) throws UserServiceException {
        UserReturnDto userReturnDto;
        try {
            userReturnDto =
                    userService.mapping(model,UserReturnDto.class);
        }catch (UserServiceException eventServiceException){
            throw new UserServiceException(eventServiceException.getMessage());
        }
        return userReturnDto;
    }
}
