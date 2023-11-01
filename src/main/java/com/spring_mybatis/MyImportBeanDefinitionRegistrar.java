package com.spring_mybatis;

import com.xiang.mapper.UserMapper;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Map;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyMapperScan.class.getName());
        String path = (String) annotationAttributes.get("value");

        MyMapperScanner myMapperScanner = new MyMapperScanner(registry);
        myMapperScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });

        myMapperScanner.scan(path);

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(MyFactoryBean.class);
        //给构造方法添加参数
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);

        registry.registerBeanDefinition("UserMapper",beanDefinition);
    }
}
