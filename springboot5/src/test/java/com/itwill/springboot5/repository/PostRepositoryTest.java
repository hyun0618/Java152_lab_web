package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {
	// PostRepository 의존성 주입
	@Autowired private PostRepository postRepo;
	
	@Test
	public void testDependencyInjection() {
		assertThat(postRepo).isNotNull(); // postRepo 객체가 null이 아니면 테스트 성공.
		log.info("postRepo = {}", postRepo);
	}
}
