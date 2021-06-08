package com.thedariusz.warnme.user.repository;

import com.thedariusz.warnme.user.Role;
import com.thedariusz.warnme.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

public class PostgresUserRepositoryDao implements UserDao {

    private final UserJpaRepository userDao;
    private final RoleRepositoryDao roleDao;
    private final BCryptPasswordEncoder encoder;

    public PostgresUserRepositoryDao(UserJpaRepository userDao, RoleRepositoryDao roleDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleDao.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userDao.save(user);
    }
}
