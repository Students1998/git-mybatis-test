package com.atguigu.mybatisMBG.junit;

import com.atguigu.mybatisMBG.dao.UserMapper;
import com.atguigu.mybatisMBG.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class GitTest {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectByPrimaryKey(1);
            System.out.println(user);
        }finally {
            sqlSession.close();
        }
    }

    /*
        模拟 hot-fix 分支下的更新
     */
    @Test
    public void test01() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectByPrimaryKey(6);
            System.out.println(user);
            System.out.println(user.getPassword());
            System.out.println("master test");
            System.out.println("hot-fix test");
        }finally {
            sqlSession.close();
        }
    }
}
