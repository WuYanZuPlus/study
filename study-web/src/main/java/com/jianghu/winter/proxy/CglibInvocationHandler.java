package com.jianghu.winter.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: daniel.hu
 * @Date: 2022/3/8 16:47
 */
public class CglibInvocationHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("【CGLIB】invoke method");
        System.out.println("【CGLIB】Method name : " + method.getName());
        return methodProxy.invokeSuper(obj, args);
    }

    public static Object newProxyInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new CglibInvocationHandler());
        return enhancer.create();
    }
}
