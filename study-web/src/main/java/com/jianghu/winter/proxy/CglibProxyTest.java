package com.jianghu.winter.proxy;

/**
 * 实现原理类似于JDK动态代理，只是它在运行期间生成的代理对象是针对目标类扩展的子类+
 * （对指定的类生成一个子类，覆盖其中的方法。因为是继承，所以目标类或方法最好不要声明成final
 *
 * @Author: daniel.hu
 * @Date: 2022/3/8 16:49
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        Say say = new SayImpl();
        Say proxy = (Say) CglibInvocationHandler.newProxyInstance(say);
        proxy.sayHello();
    }
}
