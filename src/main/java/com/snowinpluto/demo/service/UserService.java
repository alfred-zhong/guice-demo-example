package com.snowinpluto.demo.service;

import com.google.inject.ImplementedBy;
import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.mybatis.page.Page;
import com.snowinpluto.demo.service.impl.UserServiceImpl;

import java.util.List;

@ImplementedBy(UserServiceImpl.class)
public interface UserService {

    public User add(User user);

    public User findById(long id);

    public Page<User> findUserPage(int pageNum, int pageSize);
}
