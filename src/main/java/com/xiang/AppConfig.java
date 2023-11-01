package com.xiang;

import com.spring_mybatis.MyImportBeanDefinitionRegistrar;
import com.spring_mybatis.MyMapperScan;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.io.InputStream;

@ComponentScan("com.xiang")
@MyMapperScan("com.xiang.mapper")
public class AppConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory;
    }
}
