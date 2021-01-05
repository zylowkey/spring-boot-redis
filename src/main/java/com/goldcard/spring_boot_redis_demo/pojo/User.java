package com.goldcard.spring_boot_redis_demo.pojo;

import com.goldcard.spring_boot_redis_demo.common.SexEnum;
import org.apache.ibatis.type.Alias;

@Alias(value = "user")//Mybatis指定别名
public class User {
    private Long id;

    private String userName;

    //性别枚举，使用typeHandler进行转换
    private SexEnum sex;

    private String node;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}
