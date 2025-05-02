package com.kola.management.event.user.repository;


import com.kola.management.event.kernel.repository.BaseKernelRepository;
import com.kola.management.event.user.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends BaseKernelRepository<User> {

    User findUserByUserId(Long userId);

    User findUserByUsername(String username);

    List<User> findUserByEmail(String email);

    List<User> findUserByCreationDate(LocalDateTime creationDate);
}
