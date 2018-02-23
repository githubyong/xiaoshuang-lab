package com.ss.lab.sprdemo.test;

import com.ss.lab.sprdemo.model.User;
import com.ss.lab.sprdemo.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yong on 2018/2/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring-context.xml"})
public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);


    @Resource
    UserService userService;//ioc

    @Test
    public void addUser(){
        User user = new User(1l,"a1","20");
        userService.save(user);
    }

    @Test
    public void testFindUser() {
        User user = userService.queryById(1l);
        logger.info("user = " + user);
    }

    @Test
    public void updateUser(){
        User user = userService.queryById(1l);
        user.setAge("22");
        userService.update(user);
    }
}
