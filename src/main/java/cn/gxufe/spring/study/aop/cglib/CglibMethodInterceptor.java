package cn.gxufe.spring.study.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CglibMethodInterceptor implements MethodInterceptor {
    private SayHello sayHello;
    public CglibMethodInterceptor(SayHello sayHello){
        this.sayHello = sayHello;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("=====CglibMethodInterceptor==== args = " + Arrays.toString(objects));
        Object invoke = methodProxy.invoke(sayHello, objects);
        return invoke;
    }

}
