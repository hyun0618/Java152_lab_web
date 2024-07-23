package com.itwill.springboot2.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ORM(Object Relation Mapping) --> JPA(Java Persistence API) --> Hibernate 

@NoArgsConstructor @Getter @ToString @EqualsAndHashCode
@Entity // 데이터베이스 테이블과 매핑하는 자바 객체 
@Table(name = "EMP") // 클래스 이름과 실제 테이블 이름이 다를 때.
public class Employee {
	@Id // Primary Key
	@Column(name = "EMPNO") // 필드 이름과 실제 컬럼 이름이 다를 때. 
	private Integer id; 
	
	private String ename;
	
	private String job;
	
//	@Column(name = "MGR")
//	private Integer manager;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MGR")
	private Employee manager;
	
	private LocalDate hiredate;
	
	@Column(name = "SAL")
	private Double salary;
	
	@Column(name = "COMM")
	private Double commission;
	
//	private Integer deptno;
	
	@ToString.Exclude // toString 메서드에서 출력 문자열에서 제외.
//	@ManyToOne(fetch = FetchType.EAGER) // 처음부터 조인
	@ManyToOne(fetch = FetchType.LAZY) // 필요할 때 조인. 권장
	@JoinColumn(name = "DEPTNO") // EMP 테이블에서 DEPT 테이블과 join하는 컬럼 이름. 
	private Department department; // 한쪽 방향에서만 엔터티를 설정하는 것이 좋다.

}
