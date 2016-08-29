package com.snowinpluto.demo;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.mybatis.page.Page;
import com.snowinpluto.demo.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class UserServiceTest {

    @Inject
    private UserService userService;

    @Before
    public void init() {
        Guice.createInjector(new AppModule(), new AppMyBatisModule()).injectMembers(this);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("Lily");
        user.setAge(23);

        System.out.println(userService.add(user));
    }

    @Test
    public void testFindById() {
        System.out.println(userService.findById(18L));
    }

    @Test
    public void testFindPage() {
        Page<User> list = userService.findUserPage(2, 2);
        System.out.println(list);
    }

    /*@Test
    public void testFindByNames() {
        System.out.println(userService.findByNames(Lists.newArrayList("Jack", "Adam")));
    }

    @Test
    public void testAddExtra() {
        User user = new User();
        user.setName("Jack");
        user.setAge(20);

        userService.addExtra(user);
    }*/
}
