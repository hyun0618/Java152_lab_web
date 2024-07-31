package com.itwill.springboot5.dto;

import lombok.Data;

@Data
public class PostSearchRequestDto {
	private String category;
	private String keyword;
	
	private int p; // 검색 결과 목록의 페이지 번호(0부터 시작) 
		
	// category는 null, keyword는 null, p는 0이 기본값이다.
	// 기본생성자는 타입의 기본값으로 호출한다. (--> p는 0으로 초기화) 
	
}
