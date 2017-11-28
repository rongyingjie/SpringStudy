package cn.gxufe.spring.study.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HelloWorldInvocationHandler implements InvocationHandler {
    private HelloWorld helloWorld;
    public HelloWorldInvocationHandler(HelloWorld helloWorld){
        this.helloWorld = helloWorld;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("==========HelloWorldInvocationHandler===========args = " + Arrays.toString(args));
        Object invoke = method.invoke(helloWorld, args);
        return invoke;
    }
}