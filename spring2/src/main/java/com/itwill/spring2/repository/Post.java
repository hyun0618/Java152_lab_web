package com.itwill.spring2.repository;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// MVC 아키텍처에서 Model 담당 클래스. 데이터베이스의 posts 테이블의 구조.
@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor @Builder
public class Post {
	private Integer id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdTime; // "컬럼 이름: 스네이크 표기법", "필드 이름: 카멜 표기법"
	private LocalDateTime modifiedTime;
	
}
