package com.kola.management.event.user.services;


import com.kola.management.event.user.dto.user.IUserDto;
import com.kola.management.event.user.dto.user.UserDto;
import com.kola.management.event.user.model.User;
import com.kola.management.event.user.services.exceptions.UserServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IUserService extends UserDetailsService {

    String NOT_FOUND_PROPERTY_TEMPLATE = "%s with %s %s not found.";

    String MANDATORY_PROPERTY_TEMPLATE = "Property %s is mandatory on %s.";

    User updateUser(User user, Long utilisateurId) throws UserServiceException;

    User updateUser(IUserDto utilisateurDto, Long utilisateurId) throws UserServiceException;

    User updateUsernameUser(String nomUtilisateur, Long utilisateurId) throws UserServiceException;

    User updateUserEmail(String email, Long userId) throws UserServiceException;

    User updateUserPassword(String password, Long userId) throws UserServiceException;

    User updateUserStatus(boolean isActive, Long userId) throws UserServiceException;

    boolean verifyUserExists(Long utilisateurId);

    boolean verifyUserExists(User user);

    boolean verifyUserExists(String nomUtilisateur);

    User findUserById(Long userId);

    User findUserByUsername(String username);

    List<User> findUserByEmail(String email);

    List<User> findUserByCreationDate(LocalDateTime creationDate);


    /***
     * Verify whether a Utilisateur has the provided UtilisateurId
     *
     * @param userId
     * @return User
     * @throws UserServiceException
     */
    public User checkUserExists(long userId) throws UserServiceException;

    /**
     * Verify whether the mandatory params are provided
     *
     * @param user
     * @throws UserServiceException
     */
    void checkMandatoryProperty(User user) throws UserServiceException;

    User createUser(User user) throws UserServiceException;

    User createUser(UserDto userDto) throws UserServiceException;
}
