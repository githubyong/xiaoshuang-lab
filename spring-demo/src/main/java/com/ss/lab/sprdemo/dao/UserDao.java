package com.ss.lab.sprdemo.dao;

import com.ss.lab.sprdemo.model.User;

import java.util.List;

/**
 * Created by yong on 2018/2/23.
 */
public interface UserDao {

    Long save(User user);

    User queryById(Long id);

    List<User> findAll();

    Long update(User user);

    void delete(Long userId);
}
