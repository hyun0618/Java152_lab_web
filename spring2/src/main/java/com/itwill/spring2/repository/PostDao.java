package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.dto.PostSearchDto;

public interface PostDao {
	
// post-mapper.xml에서 id="selectOrderByIdDesc"인 SQL을 실행하는 메서드.
	
	// 전체보기
	List<Post> selectOrderByIdDesc(); 
	
	// 상세보기
	Post selectById(Integer id);
	
	// 글 작성 
	int insertPost(Post	post); // ==> 아규먼트를 객체 Post로 지정해 title, content, author를 한 번에 불러옴.
	
	// 삭제
	int deletePost(Integer id); 
	
	// 업데이트(title, content, modifiedTime)
	int updatePost(Post post); 
	
	// 검색
	List<Post> search(PostSearchDto dto);
      
}
