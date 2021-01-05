package com.goldcard.spring_boot_redis_demo.service.impl;

import com.goldcard.spring_boot_redis_demo.pojo.User;
import com.goldcard.spring_boot_redis_demo.repository.UserRepository;
import com.goldcard.spring_boot_redis_demo.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisUserServiceImpl implements MybatisUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUser(Long id) {
        return userRepository.getUser(id);
    }
}
