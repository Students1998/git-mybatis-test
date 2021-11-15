package com.atguigu.mybatisMBG.junit;

import com.atguigu.mybatisMBG.dao.UserMapper;
import com.atguigu.mybatisMBG.pojo.User;
import com.atguigu.mybatisMBG.pojo.UserExample;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MybatisTest {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    /*
        生成逆向工程
     */
    @Test
    public void testMbg() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }
    /*
        MyBatis3Simple:生成简单版的CRUD，简单版的逆向工程
     */
/*    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = mapper.selectAll();
            for (User user : users) {
                System.out.println(user.getId());
            }

            User user = mapper.selectByPrimaryKey(1);
            System.out.println(user.getUsername());

        }finally {
            sqlSession.close();
        }
    }*/

    /*
        MyBatis3:豪华版逆向工程
     */
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = mapper.selectByExample(null);
            for (User user : users) {
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
            }
        }finally {
            sqlSession.close();
        }
    }

    /*
        用豪华版逆向工程，会生成一个xxxExample 这个就是用来封装查询条件的
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            UserExample userExample = new UserExample();
            // 这个criteria就是拼装查询条件的
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUsernameLike("%张%");
            criteria.andPasswordEqualTo("10086");

            UserExample.Criteria criteria1 = userExample.createCriteria();
            criteria1.andIdEqualTo(1);

            userExample.or(criteria1);

            List<User> userList = mapper.selectByExample(userExample);
            for (User user : userList) {
                System.out.println(user);
            }
        }finally {
            sqlSession.close();
        }
    }

    /*
        PageHelper分页插件小test
     */
    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Page<Object> page = PageHelper.startPage(2,5);
            List<User> userList = mapper.findAllByPageHelp();
            for (User user : userList) {
                System.out.println(user);
            }
            System.out.println("总页数:"+page.getPages());
            System.out.println("当前页数:"+page.getPageNum());
            System.out.println("总记录数:"+page.getTotal());
            System.out.println("每页的记录数:"+page.getPageSize());
        }finally {
            sqlSession.close();
        }
    }


    /*
        验证批量操作
     */
    @Test
    public void test05() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //获取一个可以执行批量操作的sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try{
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            long open = System.currentTimeMillis();
            for (int i = 0;i<10000;i++){
                mapper.addUsers(new User(null,UUID.randomUUID().toString().substring(0, 5),i+""));
            }
            sqlSession.commit();
            long end = System.currentTimeMillis();
            System.out.println("一共耗时："+ (end-open));
        }finally{
            sqlSession.close();
        }
    }

}
