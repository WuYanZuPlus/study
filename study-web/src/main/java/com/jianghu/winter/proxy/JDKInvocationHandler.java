package com.jianghu.winter.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: daniel.hu
 * @Date: 2022/3/8 16:47
 */
public class JDKInvocationHandler implements InvocationHandler {
    private Object target;

    /**
     * @param target 被代理的目标对象
     */
    public JDKInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy  代理对象
     * @param method 代理方法
     * @param args   方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("【JDK】invoke method");
        System.out.println("【JDK】Method name : " + method.getName());
        return method.invoke(target, args);
    }

    public static Object newProxyInstance(Object target) {
        // 类加载器、接口类、InvocationHandler接口实现类
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new JDKInvocationHandler(target));
    }
}
