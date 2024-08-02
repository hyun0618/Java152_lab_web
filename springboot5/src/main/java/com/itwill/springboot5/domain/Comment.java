package com.itwill.springboot5.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder
@Getter @ToString(callSuper = true) // --> log.info("save 결과: {}", entity); 에서 시간 출력.
@EqualsAndHashCode(callSuper = true)
@Entity @Table(name = "COMMENTS")
public class Comment extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ToString.Exclude // toString 메서드를 만들 때 제외.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID") // FK 제약조건이 있는 컬럼 이름. 
	private Post post;
	
	@Basic(optional = false)
	private String ctext;
	
	@Basic(optional = false)
	private String writer;
	
	public Comment update(String ctext, String writer) {
		this.ctext = ctext;
		this.writer = writer;
		
		return this;				
	}

}
