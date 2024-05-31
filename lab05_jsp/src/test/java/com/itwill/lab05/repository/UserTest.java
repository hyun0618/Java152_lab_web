package com.itwill.lab05.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	
	private final UserDao userDao = UserDao.INSTANCE;
	
//	@Test
	public void testSignIn() {
		// userid, password 모두 일치:
		User test = User.builder().userid("111").password("111").build();
		User user = userDao.selectByUseridAndPassword(test);
		Assertions.assertNotNull(user);
		
		// userid, password 불일치:
		User test2 = User.builder().userid("111").password("222").build();
		User user2 = userDao.selectByUseridAndPassword(test2);
		Assertions.assertNull(user2);
		
		
	}
	
	
}
