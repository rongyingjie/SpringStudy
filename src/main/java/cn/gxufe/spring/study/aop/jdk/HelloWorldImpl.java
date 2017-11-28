package cn.gxufe.spring.study.aop.jdk;

public class HelloWorldImpl implements HelloWorld{
    public void sayHello(String name) {
        System.out.println("==========HelloWorldImpl=========== name ="  + name);
    }
}
