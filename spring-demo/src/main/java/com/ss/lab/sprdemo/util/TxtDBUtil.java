package com.ss.lab.sprdemo.util;

import com.ss.lab.sprdemo.model.User;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * txt 模拟数据库的一个工具类
 * Created by yong on 2018/2/23.
 */
public class TxtDBUtil {

    //定义 txt 的名字，从reource.properties中读取txtDB配置项的值，如果值为空返回默认值为"xiaoshuang.txt"
    private static String dbName = PropertyHolder.getProperty("txtDB", "xiaoshuang.txt");

    //用LinkedHashMap 可保证顺序
    static Map<Long, User> userMap = new LinkedHashMap<>();


    /**
     * ----------单例模式-------------
     **/
    private static TxtDBUtil txtDb = new TxtDBUtil();

    private TxtDBUtil() {
        Map<Long, User> storedUsers = loadFromTxt();
        userMap.putAll(storedUsers);
    }

    public static TxtDBUtil getInstance() {
        return txtDb;
    }

    /**
     * ----------单例模式------------- end
     **/

    private static Map<Long, User> loadFromTxt() {
        Map<Long, User> tempMap = new LinkedHashMap<>();
        try {
            Path dbPath = Paths.get(dbName);
            if (!Files.exists(dbPath)) {
                Files.createFile(dbPath);
            }
            List<String> lines = Files.readAllLines(dbPath, StandardCharsets.UTF_8);
            for (String line : lines) {
                User user = User.valueOf(line);
                if (user != null) {
                    tempMap.put(user.getId(), user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempMap;
    }

    public long save(User user) {
        if (userMap.containsKey(user.getId())) {
            System.out.println(String.format("userid=%s 已存在", user.getId()));
            return -1;
        } else {
            userMap.put(user.getId(), user);
            //把新增的用户写到txt文件里面
            saveMapToTxt();
            return user.getId();
        }
    }

    public long update(User user) {
        if (userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
            saveMapToTxt();
            return user.getId();
        } else {
            return -1;
        }
    }

    public void deleteById(Long userId) {
        userMap.remove(userId);
        saveMapToTxt();
    }

    public User findById(Long userId) {
        return userMap.get(userId);
    }

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    /**
     * 把map的内容写入文件
     */
    static void saveMapToTxt() {
        List<String> lines = new ArrayList<>();
        for (Long id : userMap.keySet()) {
            lines.add(userMap.get(id).fromTxtStr());
        }
        try {
            Files.write(Paths.get(dbName), lines, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TxtDBUtil db = TxtDBUtil.getInstance();
        db.deleteById(1l);
        User user = new User(1l, "a", "20");
        db.save(user);
        System.out.println(db.findAll());
        user.setAge("23");
        db.update(user);
        System.out.println(db.findAll());
    }
}
