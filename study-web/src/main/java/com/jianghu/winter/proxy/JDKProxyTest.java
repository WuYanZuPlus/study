package com.jianghu.winter.proxy;

/**
 * JDK动态代理：其代理对象必须是某个接口的实现，它是通过在运行期间创建一个接口的实现类来完成对目标对象的代理。
 *
 * @Author: daniel.hu
 * @Date: 2022/3/8 16:49
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        Say say = new SayImpl();
        Say proxy = (Say) JDKInvocationHandler.newProxyInstance(say);
        proxy.sayHello();
    }
}
