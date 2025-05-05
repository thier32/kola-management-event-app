package com.kola.management.event.user.business.user;

import com.kola.management.event.user.business.exceptions.UserBusinessException;
import com.kola.management.event.user.dto.user.UserDto;
import com.kola.management.event.user.dto.user.UserReturnDto;

public interface IUserBusiness {
    UserReturnDto createUser(UserDto userDto) throws UserBusinessException;
}
