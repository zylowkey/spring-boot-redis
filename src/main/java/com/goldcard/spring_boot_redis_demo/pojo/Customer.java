package com.goldcard.spring_boot_redis_demo.pojo;

import org.apache.ibatis.type.Alias;

@Alias(value = "customer")
public class Customer {
    private Long id;

    private String name;

    private String note;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String node) {
        this.note = node;
    }
}
