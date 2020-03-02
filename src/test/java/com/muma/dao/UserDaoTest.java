package com.muma.dao;


import java.util.List;


import com.muma.entity.User;
import com.muma.enums.RoalEnum;
import com.muma.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author yingjun
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
    @Autowired
	private UserService userService;
	
	@Test
	public void testQueryById() {
//		User user=userDao.queryByPhone(18768128888L);
//		System.out.println(user);
		System.out.println("--------------------------");
	}

	@Test
	public void testQueryAll() {
//		List<User> list=userDao.queryAll(0, 100);
//		for (User user : list) {
//			System.out.println(user);
//		}
	}
	
	@Test
	public void testAddScore() {
		User user = new User();
		user.setRegPhone("15725369561");
		user.setPassword("520114");
//		user.setRoalId(RoalEnum.SYSTEM_ROAL);
		user.setCreateBy("测试1");
		userService.register("15725369061","123456","MRJJAJ8","0");
//		userDao.addUser(user);
		System.out.println(user.getId());
	}



}
