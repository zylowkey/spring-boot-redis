package com.goldcard.spring_boot_redis_demo.repository.impl;

import com.goldcard.spring_boot_redis_demo.mapper.UserMapper;
import com.goldcard.spring_boot_redis_demo.pojo.User;
import com.goldcard.spring_boot_redis_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }
}
