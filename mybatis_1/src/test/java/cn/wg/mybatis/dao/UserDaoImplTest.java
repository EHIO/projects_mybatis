package cn.wg.mybatis.dao;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.wg.mybatis.dao.UserDao;
import cn.wg.mybatis.dao.UserDaoImpl;
import cn.wg.mybatis.po.User;

public class UserDaoImplTest {

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
	public void testFindUserById() throws Exception {
		// 创建UserDao的对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);

		// 调用UserDao的方法
		User user = userDao.findUserById(1);
		
		System.out.println(user);
	}

	@Test
	public void testFindUserByName() throws Exception {
		// 创建UserDao的对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);

		// 调用UserDao的方法
		List<User> user = userDao.findUserByName("小明");
		
		System.out.println(user);
	}
	
	@Test
	public void testInsertUser() throws Exception {
		// 创建UserDao的对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);

		// 插入用户对象
		User user = new User();
		user.setUsername("王小军");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("河南郑州");
		// 调用UserDao的方法
		userDao.insertUser(user);
		
		System.out.println(user.getId());
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		// 创建UserDao的对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		userDao.deleteUser(28);
	}
	
}
