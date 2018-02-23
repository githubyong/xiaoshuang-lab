package com.ss.lab.sprdemo.dao;

import com.ss.lab.sprdemo.model.User;
import com.ss.lab.sprdemo.util.TxtDBUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao interface的一个接口实现类，这块用txt文件来模拟下数据库操作。
 * <p>
 * Created by yong on 2018/2/23.
 */
@Repository
public class UserDaoTxtImpl implements UserDao {

    private TxtDBUtil txtDB = TxtDBUtil.getInstance();

    @Override
    public Long save(User user) {
        return txtDB.save(user);
    }

    @Override
    public User queryById(Long id) {
        return txtDB.findById(id);
    }

    @Override
    public List<User> findAll() {
        return txtDB.findAll();
    }

    @Override
    public Long update(User user) {
        return txtDB.update(user);
    }

    @Override
    public void delete(Long userId) {
        txtDB.deleteById(userId);
    }


}
