package com.thedariusz.warnme.user;

import com.thedariusz.warnme.user.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public boolean existUser(UserDto userDto) {
        User user = dao.findByUserName(
                User.toUser(userDto)
                        .getUsername()
        );
        return user!=null;
    }

    public void saveUser(UserDto userDto) {
        dao.saveUser(User.toUser(userDto));
    }
}
