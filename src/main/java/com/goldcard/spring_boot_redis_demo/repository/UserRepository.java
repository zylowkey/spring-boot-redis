package com.goldcard.spring_boot_redis_demo.repository;


import com.goldcard.spring_boot_redis_demo.pojo.User;

public interface UserRepository {
    User getUser(Long id);
}
