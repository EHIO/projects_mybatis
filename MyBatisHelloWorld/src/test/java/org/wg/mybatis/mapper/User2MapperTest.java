package org.wg.mybatis.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.wg.mybatis.po.User;
import org.wg.mybatis.po.UserCustom;
import org.wg.mybatis.po.UserQueryVo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class User2MapperTest {

    private SqlSessionFactory sqlSessionFactory;

    // 此方法是在执行testFindUserById之前执行
    @Before
    public void setUp() throws Exception {
        // 创建sqlSessionFactory

        // mybatis配置文件
        String resource = "SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void testFindUserByName() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        User2Mapper userMapper = sqlSession.getMapper(User2Mapper.class);

        //调用userMapper的方法
        User user = new User();
        user.setUsername("小明");
        user.setSex("1");
        List<User> list = userMapper.listUser(user);

        sqlSession.close();

        System.out.println(list);


    }

}
