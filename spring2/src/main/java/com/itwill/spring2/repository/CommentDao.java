package com.itwill.spring2.repository;

import java.util.List;

// mapper.xml 파일과 연결
public interface CommentDao {
	
	// 포스트의 상세보기에 달려 있는 모든 댓글 검색
	List<Comment> selectByPostId(Integer postId);
	
	// 포스트에 새로운 댓글 작성
	int insert(Comment comment); 
	
	// 댓글 수정(내용, 시간 업데이트)
	int update(Comment comment);
	
	// 댓글 삭제(댓글 아이디)
	int deleteById(Integer id);
	
	// 포스트에 달려 있는 모든 댓글 삭제
	int deleteByPostId(Integer postId);
	
	// TODO: 포스트에 달려 있는 댓글의 개수 검색
	Integer selectCommentCount(Integer postId);
	
	// TODO: 댓글 아이디(PK)로 검색
	Comment selectById(Integer id);
	
}
