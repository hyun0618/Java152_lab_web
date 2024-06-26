package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*  
 * application-context.xml에 <context:component-scan base-package="com.itwill.spring2.service" />이 있어야 함. 
 */

@Slf4j
@RequiredArgsConstructor // final 필드들을 초기화하는 아규먼트를 갖는 생성자.
@Service // 스프링 컨테이너에 서비스 컴포넌트로 등록.
public class PostService {
	
// 의존성 주입
	// 1. 애너테이션을 사용한 의존성 주입(DI - Dependency Injection): 
		// @Autowired 
		// private PostDao postDao;
			
	// 2. 생성자에 의한 의존성 주입: 
		// (1) final 필드 선언. (2) final 필드를 초기화하는 생성자 작성. 
		private final PostDao postDao;
		// public PostService(PostDao postDao) { // ==> @RequiredArgsConstructor 애너테이션을 붙이면 자동으로 생성되는 메서드
		// 		this.postDao = postDao;
		// }

// 리스트
		public List<PostListDto> read() {
			log.debug("read()");
		
			List<Post> list = postDao.selectOrderByIdDesc();
				
			return list.stream() 					// stream: list의 원소(x)들을 하나씩 보내는 통로 
				.map(PostListDto::fromEntity) 	// map((x) -> PostListDto.fromEntity(x)) "클래스이름::메서드이름"
				.toList();
		
			// ==> 위의 코드를 반복문으로 표현.
//			List<PostListDto> result = new ArrayList<>();
//			for (Post p : list) {
//				result.add(PostListDto.fromEntity(p));
//			}
			
		}
	
// 상세보기
		public Post read(Integer id) {
			log.debug("read(id={})", id);
		
			return postDao.selectById(id);
		}
	
// 작성
		public int create(PostCreateDto dto) {
			log.debug("create({})", dto);
			
			int result = postDao.insertPost(dto.toEntity());
			log.debug("insert 결과 = {}", result);
			
			return result;
		}
		
// 삭제
		public int delete(Integer id) {
			log.debug("delete(id={})", id);
			
			// repository 컴포넌트의 메서드를 호출해서 delete 쿼리를 실행.
			int result = postDao.deletePost(id);
			log.debug("delete 결과 = {}", result);
			
			return result;
		}

// 업데이트
		public int update(PostUpdateDto dto) {
			int result = postDao.updatePost(dto.toEntity());
			return result;
		}
		
// 검색
		public List<PostListDto> search(PostSearchDto dto) {
			List<Post> list = postDao.search(dto);
			
			return list.stream() 					
					.map(PostListDto::fromEntity) 	
					.toList();
	    }

	
}
