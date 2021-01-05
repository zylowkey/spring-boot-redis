package com.goldcard.spring_boot_redis_demo.service.impl;

import com.goldcard.spring_boot_redis_demo.pojo.Customer;
import com.goldcard.spring_boot_redis_demo.repository.CustomerRepository;
import com.goldcard.spring_boot_redis_demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Isolation.READ_COMMITTED 读写提交隔离级别
 * Isolation.SERIALIZABLE 序列化隔离级别阻塞其他事务进行并发，它只能用在低并发而又保证数据一致性的场景
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public Customer getCustomer(Long id) {
        return customerRepository.getCustomer(id);
    }

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    //设置事务传播行为，子方法创建新事务
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW ,timeout = 1)
    public int insertCustomer(Customer customer) {
        return customerRepository.insertCustomer(customer);
    }
}
