package com.nice.service.impl;

import com.nice.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if (name == null || name.trim() == "") {
            throw new RuntimeException("parameter is null!!");
        }
        System.out.println("hello" + name);
    }
}
