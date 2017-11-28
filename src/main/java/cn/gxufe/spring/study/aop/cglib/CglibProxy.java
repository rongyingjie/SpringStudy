package cn.gxufe.spring.study.aop.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CglibProxy {
    public static void main(String[] args){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SayHello.class);
        enhancer.setCallback(new CglibMethodInterceptor(new SayHello()));
        SayHello sayHello = (SayHello)enhancer.create();
        sayHello.sayHello(" test ");

    }
}
