package com.kola.management.event.user.services.impl;

import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.services.BaseKernelService;
import com.kola.management.event.user.dto.user.*;
import com.kola.management.event.user.model.User;
import com.kola.management.event.user.repository.UserRepository;
import com.kola.management.event.user.services.IUserService;
import com.kola.management.event.user.services.exceptions.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService extends BaseKernelService<User> implements IUserService {

    private PasswordEncoder passwordEncoder;

    public UserService(){
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();;
    }

    @Override
    public User createUser(User user) throws UserServiceException {
        checkMandatoryProperty(user);
        verifyUserExists(user);
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            user =  this.save(user);
        }catch (KernelException exception){
            throw new UserServiceException(exception.getMessage());
        }
        return user;
    }

    @Override
    public User createUser(UserDto userDto) throws UserServiceException {
        User user = null;
        try{
            if (!userDto.confirmpassword().equalsIgnoreCase(userDto.password())){
                throw new UserServiceException(String.format(
                        "%s %s must be identical",User.passwordProp,User.confirmPasswordProp
                ));
            }

            user = this.mapping(userDto,User.class);
            user = this.createUser(user);
        }catch (KernelException kernelException){
            throw new UserServiceException(kernelException.getMessage());
        }
        return user;
    }

    @Override
    public User updateUser(User user, Long utilisateurId) throws UserServiceException {
        try {
            user = update(user, utilisateurId);
        } catch (KernelException kernelException) {
            throw new UserServiceException(kernelException.getMessage());
        }
        return user;
    }

    @Override
    public User updateUser(IUserDto utilisateurDto, Long utilisateurId) throws UserServiceException {
        User user;
        try {
            user = update(utilisateurDto, utilisateurId);
        } catch (KernelException kernelException) {
            throw new UserServiceException(kernelException.getMessage());
        }
        return user;
    }

    @Override
    public User updateUsernameUser(String username, Long userId) throws UserServiceException {
        UserUsernameDto dto = new UserUsernameDto(username);
        return updateUser(dto, userId);
    }

    @Override
    public User updateUserEmail(String email, Long userId) throws UserServiceException {
        UserEmailDto dto = new UserEmailDto(email);
        return updateUser(dto, userId);
    }

    @Override
    public User updateUserPassword(String password, Long userId) throws UserServiceException {
        UserPasswordDto dto = new UserPasswordDto(password);
        return updateUser(dto, userId);
    }

    @Override
    public User updateUserStatus(boolean isActive, Long userId) throws UserServiceException {
        UserIsActiveDto dto = new UserIsActiveDto(isActive);
        return updateUser(dto, userId);
    }


    @Override
    public boolean verifyUserExists(Long utilisateurId) {
        return findUserById(utilisateurId) != null;
    }

    @Override
    public boolean verifyUserExists(User user) {
        User foundUser = ((UserRepository) getDefaultRepository()).findUserByUsername(user.getUsername());
        return foundUser != null;
    }

    @Override
    public boolean verifyUserExists(String username) {
        return ((UserRepository) getDefaultRepository()).findUserByUsername(username) != null;
    }

    @Override
    public User findUserById(Long utilisateurId) {
        return ((UserRepository) getDefaultRepository()).findUserByUserId(utilisateurId);
    }

    @Override
    public User findUserByUsername(String username) {
        return ((UserRepository) getDefaultRepository()).findUserByUsername(username);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return ((UserRepository) getDefaultRepository()).findUserByEmail(email);
    }

    @Override
    public List<User> findUserByCreationDate(LocalDateTime creationDate) {
        return ((UserRepository) getDefaultRepository()).findUserByCreationDate(creationDate);
    }


    @Override
    public User checkUserExists(long userId) throws UserServiceException {
        User user = findUserById(userId);
        if (user == null) {
            throw new UserServiceException(
                    String.format(NOT_FOUND_PROPERTY_TEMPLATE,User.class.getSimpleName()
                    ,User.userIdProp,userId)
            );
        }
        return user;
    }


    @Override
    public void checkMandatoryProperty(User user) throws UserServiceException {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new UserServiceException(
            String.format(MANDATORY_PROPERTY_TEMPLATE,User.userNameProp,User.class.getSimpleName())
            );
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new UserServiceException(
                    String.format(MANDATORY_PROPERTY_TEMPLATE,User.emailProp,User.class.getSimpleName())
            );
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new UserServiceException(
                    String.format(MANDATORY_PROPERTY_TEMPLATE,User.passwordProp,User.class.getSimpleName())
            );
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
