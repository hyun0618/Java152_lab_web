package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CommentRepositoryTest {
	// CommentRepository의 CRUD 기능을 단위 테스트 
	@Autowired private CommentRepository commentRepo;
	@Autowired private PostRepository postRepo;
	
//	@Test
	public void testDependencyInjection() {
		assertThat(commentRepo).isNotNull();
	}
	
//	@Test
	public void testFindAll() {
		List<Comment> list = commentRepo.findAll();
		assertThat(list.size()).isEqualTo(4);
		
		list.forEach(System.out::println);
	}
	
//	@Test
	public void testSave() {
		Post post = postRepo.findById(372L).orElseThrow();
		assertThat(post).isNotNull();
		
		Comment entity = Comment.builder()
					.post(post)
					.ctext("comment5")
					.writer("test5")
					.build();
		
		commentRepo.save(entity);
	}
	
//	@Test
//	public void testUpdate() {
//		Comment entity = commentRepo.findById(4L).orElseThrow();
//		
//		entity.update("comment6", "test6");
//		entity = commentRepo.save(entity);
//	}
	
//	@Test
	public void testDelete() {
		commentRepo.deleteById(3L);
	}
	
}
