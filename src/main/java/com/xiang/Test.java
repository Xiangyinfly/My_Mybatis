package com.xiang;

import com.spring_mybatis.MyFactoryBean;
import com.xiang.mapper.UserMapper;
import com.xiang.service.UserService;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = (UserService) applicationContext.getBean( "userService");
        userService.test();
//        System.out.println(applicationContext.getBean("myFactoryBean"));
//        System.out.println(applicationContext.getBean("&myFactoryBean"));
    }
}
