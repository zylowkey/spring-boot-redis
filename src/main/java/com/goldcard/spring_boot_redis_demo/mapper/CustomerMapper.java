package com.goldcard.spring_boot_redis_demo.mapper;


import com.goldcard.spring_boot_redis_demo.pojo.Customer;

public interface CustomerMapper {
    Customer getCustomer(Long id);
    int insertCustomer(Customer customer);
}
