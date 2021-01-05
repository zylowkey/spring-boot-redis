package com.goldcard.spring_boot_redis_demo.repository;


import com.goldcard.spring_boot_redis_demo.pojo.Customer;

public interface CustomerRepository {
    Customer getCustomer(Long id);
    int insertCustomer(Customer customer);
}
