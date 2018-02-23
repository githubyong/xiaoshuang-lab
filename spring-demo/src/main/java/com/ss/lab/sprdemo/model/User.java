package com.ss.lab.sprdemo.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yong on 2018/2/23.
 */
public class User {
    private long id;
    private String name;
    private String age;

    public User() {
    }

    public User(long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    //分隔符
    private static String SEPARATOR = "|";

    /**
     * 把val转换成User对象
     *
     * @param val
     * @return
     */
    public static User valueOf(String val) {
        if (StringUtils.isNotBlank(val)) {
            String[] arr = val.split("\\|");
            User user = new User(Long.valueOf(arr[0]), arr[1], arr[2]);
            return user;
        }
        return null;
    }

    /**
     * 把User对象转用于存储的txt字符串
     *
     * @return
     */
    public String fromTxtStr() {
        return this.id + "|" + this.name + "|" + this.age + "|";
    }
}
