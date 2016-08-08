package com.snowinpluto.demo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.snowinpluto.demo.dao.UserDao;
import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.service.UserService;

import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByNames(List<String> names) {
        return userDao.findByNames(names);
    }
}
