package com.snowinpluto.demo.service.impl;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.mybatis.dao.MyBatisDao;
import com.snowinpluto.demo.mybatis.page.Page;
import com.snowinpluto.demo.mybatis.page.PageRequest;
import com.snowinpluto.demo.service.UserService;

import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    @Inject
    private MyBatisDao myBatisDao;

    @Override
    public User add(User user) {
        myBatisDao.insert("addUser", user);
        return user;
    }

    @Override
    public User findById(long id) {
        List<User> list = myBatisDao.findForList("findUserById", id);

        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Page<User> findUserPage(int pageNum, int pageSize) {
        return myBatisDao.findForPage("findUser", new PageRequest(pageNum, pageSize, Maps.newHashMap()));
    }
}
