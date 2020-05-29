package com.bsu.famcs.anytask.service.interfaces;

import com.bsu.famcs.anytask.entity.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    User create(User entity);

    void update(User entity);
}