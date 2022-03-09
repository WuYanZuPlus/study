package com.jianghu.winter.proxy;

/**
 * @Author: daniel.hu
 * @Date: 2022/3/8 16:47
 */
public class SayImpl implements Say{

    @Override
    public String sayHello() {
        System.out.println("Hello World");
        return "success";
    }
}
