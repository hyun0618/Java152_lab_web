package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostSearchRequestDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostQuerydslTest {
	
	@Autowired private PostRepository postRepo; 
	// PostRepository가 PostQuerydsl도 상속받고 있기 때문에 PostRepository에 대한 변수만 설정하면 된다. 
	
//	@Test
	public void testSearchById() {
		Post entity = postRepo.searchById(2L);
		log.info("entity = {}", entity);
	}
	
//	@Test
	public void test() {
		List<Post> result = null;
//		result = postRepo.searchByTitle("DUMM");
//		result = postRepo.searchByContent("tes");
//		result = postRepo.searchByTitleOrContent("tes");
//		result = postRepo.searchByModifiedTime(LocalDateTime.of(2024, 7, 29, 12, 0), LocalDateTime.of(2024, 7, 30, 12, 0));
		
//		LocalDateTime from = LocalDateTime.of(2024, 7, 29, 0, 0);
//		LocalDateTime to = LocalDateTime.of(2024, 7, 29, 23, 59);
//		result = postRepo.searchByModifiedTime(from, to);
		
//		result = postRepo.searchByAuthorAndTitle("guest", "1");
		
//		PostSearchRequestDto dto = new PostSearchRequestDto();
//		dto.setCategory("tc");
//		dto.setKeyword("dum title");
//		result = postRepo.searchByCategory(dto);
		
		String[] keywords = "ad date".split(" "); // {"dum", "title"};
//		result = postRepo.searchByKeywords(keywords);
		
		Pageable pageable = PageRequest.of(0, 5, Sort.by("modifiedTime").descending());
		Page<Post> page = postRepo.searchByKeywords(keywords, pageable);
		page.forEach(System.out::println);
		
		// result 전체 출력. 
		result.forEach(System.out::println);
		
		// result 5개만 출력.
//		for (int i = 0; i < 5; i++) {
//			log.info("{}", result.get(i));
//		}
		
	}
	
}
