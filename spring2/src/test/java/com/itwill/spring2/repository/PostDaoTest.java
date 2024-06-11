package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
)
public class PostDaoTest {
	
	@Autowired // 의존성 주입(Dependency Injection)
	private PostDao postDao;
	
//	@Test
	public void testSelectAll() {
		Assertions.assertNotNull(postDao);
		
		List<Post> list = postDao.selectOrderByIdDesc();
		for (Post p : list) {
			System.out.println("\t*****\t" + p);
		}
	}
	
//	@Test
	public void testSelectById() {
		Post post1 = postDao.selectById(35); // DB테이블에 id가 있는 경우
		Assertions.assertNotNull(post1);
		log.debug(post1.toString());
		
		Post post2 = postDao.selectById(1); // DB테이블에 id가 없는 경우
		Assertions.assertNull(post2);
	}
	
//	@Test
	public void testInsertPost() {
		Post post = Post.builder()
				.title("MyBatis 테스트")
				.content("MyBatis-Spring insert 테스트")
				.author("admin")
				.build();
		int result = postDao.insertPost(post);
		Assertions.assertEquals(1, result);
	}
	
	@Test
	public void testUpdatePost() {
		// 업데이트 할 포스트 객체 만들기:
		Post post = Post.builder()
				.id(41)
				.title("modifiedTime")
				.content("modifiedTime 업데이트")
				.build(); // modifiedTime은 입력할 필요가 없다. 
		int result = postDao.updatePost(post);
		Assertions.assertEquals(1, result);
	}
	
//	@Test
	public void testDeletePost() {
		int result = postDao.deletePost(42);
		Assertions.assertEquals(1, result);
	}
	
}
