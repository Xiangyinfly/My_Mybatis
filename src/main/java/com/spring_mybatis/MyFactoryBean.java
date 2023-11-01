package com.spring_mybatis;

import com.xiang.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyFactoryBean implements FactoryBean {
//    @Override
//    public Object getObject() throws Exception {
//        Object instance = Proxy.newProxyInstance(MyFactoryBean.class.getClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        });
//
//        return instance;
//    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }

    public MyFactoryBean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    private SqlSession sqlSession;
    private Class mapperClass;

    @Autowired
    public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().addMapper(mapperClass);
        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Override
    public Object getObject() throws Exception {
        //UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return sqlSession.getMapper(mapperClass);
    }
}
