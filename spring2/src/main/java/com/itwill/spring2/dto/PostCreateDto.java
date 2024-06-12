package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Post;

import lombok.Data;

// 새 글 작성 요청에서 요청 파라미터를 저장하기 위한 DTO
@Data
public class PostCreateDto {
	
	// 필드 이름을 요청 파라미터 이름과 같게 선언하면,
	// 기본 생성자, getter, setter 생성. ==> dispatcher servlet이 사용 가능.
	private String title;
	private String content;
	private String author;
	
	// PostDao의 int insertPost(Post post);을 이용하기 위해
	// Post 타입의 메서드 생성.
	public Post toEntity() { 
		return Post.builder().title(title).content(content).author(author).build();
	}
	
}
