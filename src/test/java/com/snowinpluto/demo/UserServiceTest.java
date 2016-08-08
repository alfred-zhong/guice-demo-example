package com.snowinpluto.demo;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class UserServiceTest {

    private UserService userService;

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new AppModule(), new AppMyBatisModule());
        userService = injector.getInstance(UserService.class);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("Adam");
        user.setAge(30);

        System.out.println(userService.add(user));
    }

    @Test
    public void testFindById() {
        System.out.println(userService.findById(3));
    }

    @Test
    public void testFindByNames() {
        System.out.println(userService.findByNames(Lists.newArrayList("Jack", "Adam")));
    }
}
