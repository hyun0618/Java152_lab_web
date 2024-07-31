package com.itwill.springboot5.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.domain.QPost;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostQuerydslImpl extends QuerydslRepositorySupport implements PostQuerydsl {
	
	public PostQuerydslImpl() { 
		super(Post.class);
	}
	// --> 기본생성자를 갖고 잊지 않아서 명시적으로 호출하고 엔터티 클래스를 아규먼트로 줌. 

	@Override
	public Post searchById(Long id) {
		log.info("searchById(id={})", id);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post); // ==> 'select p from Post p'
		query.where(post.id.eq(id)); // ==> query + 'where id = ?'
		Post entity = query.fetchOne();	
		
		return entity;
	}
}
