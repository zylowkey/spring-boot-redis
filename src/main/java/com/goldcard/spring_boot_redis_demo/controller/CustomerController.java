package com.goldcard.spring_boot_redis_demo.controller;

import com.goldcard.spring_boot_redis_demo.pojo.Customer;
import com.goldcard.spring_boot_redis_demo.service.CustomerBatchService;
import com.goldcard.spring_boot_redis_demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cus")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerBatchService customerBatchService;

    @RequestMapping(value = "/getCus")
    @ResponseBody
    public Customer getCustomer(Long id) {
        return customerService.getCustomer(id);
    }

    @RequestMapping(value = "/insertCus")
    @ResponseBody
    public Map<String, Object> insertCustomer(String name, String note) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setNote(note);
        int count = customerService.insertCustomer(customer);
        Map<String, Object> result = new HashMap<>();
        result.put("success", count == 1);
        result.put("customer", customer);
        return result;
    }

    @RequestMapping(value = "/insertCuss")
    @ResponseBody
    public Map<String, Object> insertCustomers(String name, String note, String name1, String note1) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setNote(note);
        Customer customer1 = new Customer();
        customer1.setName(name1);
        customer1.setNote(note1);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer);
        int count = customerBatchService.insertCustomer(customerList);
        Map<String, Object> result = new HashMap<>();
        result.put("success", count > 0);
        result.put("customer", customerList);
        return result;
    }
}
