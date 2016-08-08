package com.snowinpluto.demo.dao;

import com.google.inject.ImplementedBy;
import com.snowinpluto.demo.dao.impl.UserDaoImpl;
import com.snowinpluto.demo.entity.User;

import java.util.List;

@ImplementedBy(UserDaoImpl.class)
public interface UserDao {

    /**
     * 新增
     * @param user
     * @return
     */
    public User add(User user);

    /**
     * 根据 ID 查找
     * @param id
     * @return
     */
    public User findById(Long id);

    /**
     * 根据姓名列表查找
     * @param names
     * @return
     */
    public List<User> findByNames(List<String> names);
}
