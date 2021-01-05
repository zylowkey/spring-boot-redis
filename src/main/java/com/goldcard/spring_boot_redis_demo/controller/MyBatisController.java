package com.goldcard.spring_boot_redis_demo.controller;

import com.goldcard.spring_boot_redis_demo.pojo.User;
import com.goldcard.spring_boot_redis_demo.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mybatis")
public class MyBatisController {
	
	@Autowired
	private MybatisUserService myBatisUserService;
	
	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(Long id) {
		return myBatisUserService.getUser(id);
	}
}