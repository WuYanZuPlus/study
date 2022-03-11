package com.jianghu.winter.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * JDK动态代理：其代理对象必须是某个接口的实现，它是通过在运行期间创建一个接口的实现类来完成对目标对象的代理。
 * <p>
 * 由于java的单继承，动态生成的代理类已经继承了Proxy类的，就不能再继承其他的类，所以只能靠实现被代理类的接口的形式，故JDK的动态代理必须有接口。
 * 另外，为何调用代理类的方法就会自动进入InvocationHandler 的 invoke（）方法呢？
 * 其实是因为在动态代理类的定义中，构造函数是含参的构造，参数就是我们invocationHandler 实例，
 * 而每一个被代理接口的方法都会在代理类中生成一个对应的实现方法，并在实现方法中最终调用invocationHandler 的invoke方法，
 * 这就解释了为何执行代理类的方法会自动进入到我们自定义的invocationHandler的invoke方法中，
 * 然后在我们的invoke方法中再利用jdk反射的方式去调用真正的被代理类的业务方法，而且还可以在方法的前后去加一些我们自定义的逻辑。比如切面编程AOP等。
 *
 * @Author: daniel.hu
 * @Date: 2022/3/8 16:49
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        Say say = new SayImpl();
        Say proxy = (Say) JDKInvocationHandler.newProxyInstance(say);
        proxy.sayHello();
        // generateProxyClass(say);
    }

    /**
     * 生成动态代理对象, 方便理解底层实现invoke方法调用
     */
    private static void generateProxyClass(Say say) {
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", say.getClass().getInterfaces());
        String path = "D:/proxy.class";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}
