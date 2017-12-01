package cn.gxufe.spring.study.scan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;

public class MyFactoryBean<T> implements InitializingBean, FactoryBean<T>,ApplicationContextAware {

    private String className;
    private ApplicationContext applicationContext;
    private  Scope scope;
    private Class<?> aClass;
    public void setClassName(String className) throws ClassNotFoundException {
        this.className = className;
        aClass = Class.forName(this.className);
        scope = aClass.getAnnotation(Scope.class);
    }


    public T getObject() throws Exception {
        Class<?> aClass = Class.forName(this.className);
        if(aClass.isInterface()){
            throw new IllegalArgumentException( aClass.getName() +" must be class not interface"  );
        }else {
            Object o = aClass.newInstance();
            return (T)o;
        }
    }

    public Class<?> getObjectType() {
        if(this.aClass == null){
            return null;
        }
        return aClass;
    }

    public boolean isSingleton() {
        if(scope == null){
            return true;
        }
        if(ConfigurableBeanFactory.SCOPE_SINGLETON.equals(scope.value())){
            return true;
        }
        return false;
    }

    public void afterPropertiesSet() throws Exception {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
