package com.goldcard.spring_boot_redis_demo.mapper;

import com.goldcard.spring_boot_redis_demo.pojo.User;


/**** imports ****/
public interface UserMapper {
	User getUser(Long id);
}