package com.itwill.springboot2.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot2.domain.Department;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	// TODO: DEPT 테이블과 매핑되는 엔티티 클래스를 설계, 리포지토리 인터페이스 작성
		// 단위 테스트 클래스 작성. 
		@Test
		public void findByTest() {
			// 부서번호(deptno 컬럼, id 필드)로 검색 테스트
			// 부서번호가 테이블에 있는 경우:
			Department dept1 = deptRepo.findById(10).orElseThrow();
			assertThat(dept1.getId()).isEqualTo(10);
			log.info("dept1: {}", dept1);
			
			// 부서번호가 테이블에 없는 경우:
			boolean isEmpty = deptRepo.findById(100).isEmpty();
			assertThat(isEmpty);
			
		}

}
