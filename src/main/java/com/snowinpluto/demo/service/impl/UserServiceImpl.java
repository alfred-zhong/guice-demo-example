package com.snowinpluto.demo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.snowinpluto.demo.annotation.AddUser;
import com.snowinpluto.demo.dao.UserDao;
import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.service.UserService;
import org.mybatis.guice.transactional.Transactional;

import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @AddUser
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

    @Transactional
    @Override
    public void addExtra(User user) {
        userDao.add(user);

        user.setName(user.getName().substring(0, user.getName().length() - 1));
        userDao.add(user);
    }
}
