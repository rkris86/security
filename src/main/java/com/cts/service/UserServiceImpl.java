package com.cts.service;

import com.cts.dao.UserDao;
import com.cts.model.Login;
import com.cts.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public void register(User user) {
        userDao.register(user);
    }

    @Override
    public User updateUser(User user) {
       return userDao.updateUser(user);
    }


}
