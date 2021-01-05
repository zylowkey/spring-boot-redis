package com.goldcard.spring_boot_redis_demo.service;


import com.goldcard.spring_boot_redis_demo.pojo.Customer;

import java.util.List;

public interface CustomerBatchService {
    public int insertCustomer(List<Customer> customerList);
}
