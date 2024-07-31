package com.itwill.springboot5.dto;

import com.itwill.springboot5.domain.Post;

import lombok.Data;

@Data
public class PostUpdateDto {
	private Long id;
	private String title;
	private String content;
//	private String author;
	
//	public Post toEntity() {
//		return Post.builder()
//				.id(id)
//				.title(title)
//				.content(content)
//				.author(author)
//				.build();
//	}
	
}
