package com.nice.test;

import com.nice.config.ProxyBean;
import com.nice.intercept.MyInterceptor;
import com.nice.service.HelloService;
import com.nice.service.impl.HelloServiceImpl;

/**
 * @author ningh
 */
public class TestApplication {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        //按约定获取proxy
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService,new MyInterceptor());
        proxy.sayHello("nice");
        System.out.println("\n########name is null!!!!!!!#########\n");
        proxy.sayHello(null);
    }
}
