package cn.gxufe.spring.study.scan.service;

import cn.gxufe.spring.study.scan.annotation.MyAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.*;

@MyAnnotation
//@Scope("thread")
public class UserService implements HelloInterface<Integer>{


    private DemoService demoService;

    public void sayHello(String name) {
        System.out.println("hello " + name + "\t"+ demoService);
    }

    @Autowired
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }
}
