package com.itwill.springboot5.repository;

import java.util.List;

import com.itwill.springboot5.domain.Post;

/*
 * Querydsl 사용:
 * 1. build.gradle 파일에 의존성과 빌드 옵션을 추가.
 * 2. 프로젝트 이름에서 오른쪽 클릭 --> Gradle > Refresh Gradle Project
 * 3. Window > Show View > Other... > Gradle Executions, Gradle Tasks 탭 오픈
 * 4. Gradle Tasks 창에서 프로젝트 선택 --> build > clean > Run Gradle Tasks
 * 5. Gradle Tasks 창에서 프로젝트 선택 --> build > build > Run Gradle Tasks 
 * 6. 프로젝트 이름에서 오른쪽 클릭 --> Gradle > Refresh Gradle Project
 *    build/generated/querydsl 폴더에 com.itwill.springboot5.domain 패키지가 생성.
 * 	  패키지에는 Q클래스들(QBaseTimeEntity.java, QPost.java)이 자동으로 생성.
 * 7. 인터페이스(예: PostQuerydsl) 작성
 * 8. 인터페이스를 구현하는 클래스(PostQuerydslImpl)를 작성.
 *    (1) QuerydslRepositorySupport 상속
 *        - 상위 클래스가 기본 생성자를 갖고 있지 않아서, 아규먼트를 갖는 super(arg)를 명시적으로 호출한다. 
 *    (2) PostQuerydsl 인터페이스를 구현
 *        - 추상 메서드의 body를 구현한다. 
 * 9. JpaRepository를 상속받는 인터페이스(PostRepository)에서 PostQuerydsl 인터페이스를 상속.
 * 
 */

public interface PostQuerydsl {
	
	// id가 일치하는 엔터티 검색
	Post searchById(Long id);
	
	// 제목에 포함된 문자열을 대소문자 구분없이 검색
	List<Post> searchByTitle(String keyword); // --> 인터페이스에서는 메서드 앞에 public abstract 생략.
	
	// 내용에 포함된 문자열을 대소문자 구분없이 검색 
	List<Post> searchByContent(String keyword);
}
