package cn.gxufe.spring.study.scan.service;

import cn.gxufe.spring.study.scan.annotation.MyAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@MyAnnotation
@Scope("thread")
public class DemoService implements HelloInterface<String>{


    public void sayHello(String name) {
        System.out.println("hello " + name + "\t" );
    }

}
