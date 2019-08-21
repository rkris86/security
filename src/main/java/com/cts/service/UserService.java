package com.cts.service;

import com.cts.model.Login;
import com.cts.model.User;

public interface UserService {

    void register(User user);

    User updateUser(User user);
}
