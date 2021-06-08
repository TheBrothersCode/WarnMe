package com.thedariusz.warnme.user.repository;

import com.thedariusz.warnme.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
