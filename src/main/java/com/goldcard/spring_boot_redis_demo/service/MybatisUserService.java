package com.goldcard.spring_boot_redis_demo.service;


import com.goldcard.spring_boot_redis_demo.pojo.User;

public interface MybatisUserService {
    public User getUser(Long id);
}
