package com.goldcard.spring_boot_redis_demo.service;


import com.goldcard.spring_boot_redis_demo.pojo.Customer;

public interface CustomerService {
    Customer getCustomer(Long id);
    int insertCustomer(Customer customer);
}
