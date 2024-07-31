package com.itwill.springboot5.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostQuerydslTest {
	
	@Autowired private PostRepository postRepo; 
	// PostRepository가 PostQuerydsl도 상속받고 있기 때문에 PostRepository에 대한 변수만 설정하면 된다. 
	
	@Test
	public void testSearchById() {
		Post entity = postRepo.searchById(2L);
		log.info("entity = {}", entity);
	}
	
	
	
}
