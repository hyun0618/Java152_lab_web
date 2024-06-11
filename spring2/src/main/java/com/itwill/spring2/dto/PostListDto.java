package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO(Data Transfer Object)
// (뷰 <--> 컨트롤러), (컨트롤러 <--> 서비스) 사이에서 데이터를 주고 받을 때 사용하는 객체.
@Data // ==> @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor
@NoArgsConstructor @AllArgsConstructor @Builder 
// @Builder를 추가하려면 @AllArgsConstructor 필요. 
// 기본생성자가 있으려면 @NoArgsConstructor 필요.
public class PostListDto {
	
	private Integer id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;

	public static PostListDto fromEntity(Post post) { 
	// static --> 클래스 이름으로 메서드를 호출하기 위함.
		return PostListDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.author(post.getAuthor())
				.modifiedTime(post.getModifiedTime())
				.build();
	}

}
