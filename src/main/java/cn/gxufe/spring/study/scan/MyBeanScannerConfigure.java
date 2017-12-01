package cn.gxufe.spring.study.scan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.support.GenericApplicationContext;

public class MyBeanScannerConfigure implements BeanFactoryPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;



    private String [] basePackages;
    public MyBeanScannerConfigure(String... basePackages){
        this.basePackages = basePackages;
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        MyClassPathBeanDefinitionScanner myClassPathBeanDefinitionScanner = new MyClassPathBeanDefinitionScanner(defaultListableBeanFactory);
        myClassPathBeanDefinitionScanner.setResourceLoader(this.applicationContext);
        myClassPathBeanDefinitionScanner.scan(this.basePackages);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
