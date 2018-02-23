package com.ss.lab.sprdemo.service;

import com.ss.lab.sprdemo.dao.UserDao;
import com.ss.lab.sprdemo.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yong on 2018/2/23.
 */
@Service
public class UserService {

    @Resource
    UserDao userDao;//ioc 依赖注入(通过spring的容器自动初始化);不用spring时候的通常写法 为 UserDao userDao = new UserDaoTxtImpl();

    public Long save(User user) {
        return this.userDao.save(user);
    }

    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    public List<User> findAll() {
        return this.userDao.findAll();
    }

    public Long update(User user) {
        return this.userDao.update(user);
    }

    public void delete(Long userId) {
        this.userDao.delete(userId);
    }
}
