package com.snowinpluto.demo.interceptor;

import com.snowinpluto.demo.entity.User;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddUserInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AddUserInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result = methodInvocation.proceed();

        if (result instanceof User) {
            User user = (User) result;
            log.info("Add User:" + user.toString());
        }

        return result;
    }
}
