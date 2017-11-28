package cn.gxufe.spring.study.aop.jdk;
import java.lang.reflect.Proxy;


public class JdkProxy {
    public static void main(String[] args){
        HelloWorldInvocationHandler handler = new HelloWorldInvocationHandler(new HelloWorldImpl());
        HelloWorld helloWorld = (HelloWorld)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{HelloWorld.class},handler);
        helloWorld.sayHello("ha ha ");
    }
}