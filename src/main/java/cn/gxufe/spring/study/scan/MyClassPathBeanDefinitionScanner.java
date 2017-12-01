package cn.gxufe.spring.study.scan;

import cn.gxufe.spring.study.scan.annotation.MyAnnotation;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import java.util.HashSet;
import java.util.Set;

public class MyClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private BeanDefinitionRegistry registry;
    public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
        this.registry = registry;
    }
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(MyAnnotation.class));
    }

//    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
//        Set<BeanDefinitionHolder> beanDefinitions =   super.doScan(basePackages);
//        Set<BeanDefinitionHolder> beanDefinitionsFactoryBean = new HashSet<BeanDefinitionHolder>(beanDefinitions.size());
//        for (BeanDefinitionHolder holder : beanDefinitions) {
//            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
//            String factoryBeanName =  holder.getBeanName()+"FactoryBean";
//            definition.setFactoryBeanName(factoryBeanName);
//            BeanDefinition beanDefinitionFactoryBean = new RootBeanDefinition();
//            beanDefinitionFactoryBean.setBeanClassName(MyFactoryBean.class.getName());
//            beanDefinitionFactoryBean.getPropertyValues().add("className",definition.getBeanClassName());
//            BeanDefinitionHolder beanDefinitionFactoryBeanHolder = new BeanDefinitionHolder(beanDefinitionFactoryBean,factoryBeanName);
//            beanDefinitionsFactoryBean.add(beanDefinitionFactoryBeanHolder);
//            BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionFactoryBeanHolder, registry);
//        }
//        return beanDefinitionsFactoryBean;
//    }
//
//    protected void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) { // 忽略父类注册的bean
//
//    }

    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return true;
    }

}
