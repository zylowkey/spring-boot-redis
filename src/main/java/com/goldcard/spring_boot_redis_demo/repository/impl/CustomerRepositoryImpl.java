package com.goldcard.spring_boot_redis_demo.repository.impl;

import com.goldcard.spring_boot_redis_demo.mapper.CustomerMapper;
import com.goldcard.spring_boot_redis_demo.pojo.Customer;
import com.goldcard.spring_boot_redis_demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public Customer getCustomer(Long id) {
        return customerMapper.getCustomer(id);
    }

    @Override
    public int insertCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }
}
