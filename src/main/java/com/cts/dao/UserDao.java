package com.cts.dao;

import com.cts.model.Login;
import com.cts.model.User;

public interface UserDao {
    public void register(User user);

    public User updateUser(User user);
}
