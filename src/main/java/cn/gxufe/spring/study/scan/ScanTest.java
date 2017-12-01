package cn.gxufe.spring.study.scan;

import cn.gxufe.spring.study.scan.annotation.MyAnnotation;
import cn.gxufe.spring.study.scan.service.DemoService;
import cn.gxufe.spring.study.scan.service.HelloInterface;
import cn.gxufe.spring.study.scan.service.UserService;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

/***
 * 整体功能说明：
 *  1、自定义注解(不是继承spring的)
 *  2、编写注解处理器，将其注入spring
 *
 * 涉及到的知识:
 *  1、包扫描处理
 *  2、bean创建
 */

@Configuration
public class ScanTest {

    @Bean
    public static MyBeanScannerConfigure getMyBeanScannerConfigure(){
        return new MyBeanScannerConfigure("cn.gxufe.spring.study.scan.service");
    }

    @Bean
    public static Scope getSimpleThreadScope(ApplicationContext applicationContext){
        GenericApplicationContext context = (GenericApplicationContext) applicationContext;
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Scope threadScope = new SimpleThreadScope();
        beanFactory.registerScope("thread", threadScope);
        return threadScope;
    }

    public static void main(String[] args) throws Exception {


       final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScanTest.class);
        UserService userService = applicationContext.getBean(UserService.class);
       userService.sayHello("jimmy");
     //   System.out.println( Thread.currentThread().getName() + "->"+ userService);

        new Thread(new Runnable() {
            public void run() {
                UserService userService = applicationContext.getBean(UserService.class);
            //    System.out.println( Thread.currentThread().getName() + "->"+ userService);
            }
        }).start();


        DemoService bean = applicationContext.getBean(DemoService.class);
        System.out.println(Thread.currentThread().getName() + "->"+bean);
        bean.sayHello("zhangsan ");
        new Thread(new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        DemoService userService = applicationContext.getBean(DemoService.class);
                     //   System.out.println( Thread.currentThread().getName() + "->"+ userService);
                    }
                }).start();
            }
        }).start();


        String[] beanNamesForType = applicationContext.getBeanNamesForType(HelloInterface.class);


        System.out.println(beanNamesForType);

        Map<String, HelloInterface> beansOfType = applicationContext.getBeansOfType(HelloInterface.class);
        System.out.println(beansOfType);
        Iterator<String> iterator = beansOfType.keySet().iterator();

        while (iterator.hasNext()){
            String next = iterator.next();
            HelloInterface helloInterface = beansOfType.get(next);
            Class<? extends HelloInterface> aClass = helloInterface.getClass();
            System.out.println(aClass);
            Type[] genericInterfaces = aClass.getGenericInterfaces();
            Type type = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments()[0];
          //  System.out.println("type =====> "+type);

            if(type == String.class){
                System.out.println("type =====> "+"String");
            }else if (type == Integer.class){
                System.out.println("type =====> "+"Integer");
            }

        }


//        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MyAnnotation.class);
//        System.out.println(beansWithAnnotation);
//        System.out.println(beanNamesForType);



        //    userService.
//        userService = applicationContext.getBean(UserService.class);
//        System.out.println(userService);
    }

}
