package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

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
	// --> QuerydslRepositorySupport가 기본생성자를 갖고 있지 않아서 명시적으로 호출하고 엔터티 클래스를 아규먼트로 줌. 

	@Override
	public Post searchById(Long id) {
		log.info("searchById(id={})", id);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post); // ==> 'select p from Post p'
		query.where(post.id.eq(id)); // ==> query + 'where id = ?'
		Post entity = query.fetchOne();	
		
		return entity;
	}
	
	@Override
	public List<Post> searchByTitle(String keyword) {
		log.info("searchByTitle(keyword={})", keyword);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post); // ==> select p from Post
		query.where(post.title.containsIgnoreCase(keyword)); // where 절 
		query.orderBy(post.modifiedTime.desc()); // ==> order by 절 
			
		List<Post> result = query.fetch();
		
		return result;
	}
	
	@Override
	public List<Post> searchByContent(String keyword) {
		log.info("searchByContent(keyword={})", keyword);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post)
				.where(post.content.containsIgnoreCase(keyword))
				.orderBy(post.modifiedTime.desc());
		
		return query.fetch();
	}
	
	@Override
	public List<Post> searchByTitleOrContent(String keyword) {
		log.info("searchByTitleOrContent(keyword={})", keyword);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		query.where(
				post.title.containsIgnoreCase(keyword)
				.or(post.content.containsIgnoreCase(keyword))
		);
		
		return query.fetch();
	}
	
	@Override
	public List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to) {
		log.info("searchByModifiedTime(from={}, to={})", from, to);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post)
				.where(post.modifiedTime.between(from, to));
		
		return query.fetch();
	}
	
}
