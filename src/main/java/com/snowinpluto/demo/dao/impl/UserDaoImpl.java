package com.snowinpluto.demo.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.snowinpluto.demo.dao.UserDao;
import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.mapper.UserMapper;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class UserDaoImpl implements UserDao {

    @Inject
    private UserMapper userMapper;

    @Override
    public User add(User user) {
        checkNotNull(user);

        userMapper.add(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        checkNotNull(id);

        List<User> userList = userMapper.findById(id);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public List<User> findByNames(List<String> names) {
        checkNotNull(names);
        checkArgument(names.size() > 0);

        return userMapper.findByNames(names);
    }
}
