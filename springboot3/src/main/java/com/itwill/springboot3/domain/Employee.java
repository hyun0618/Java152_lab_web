package com.itwill.springboot3.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor // 기본생성자 
@Getter @ToString @EqualsAndHashCode // override
@Entity @Table(name = "EMPLOYEES") // EMPLOYEES 테이블에 맵핑되는 엔터티.
public class Employee {
	
	@Id // primary key
	@Column(name = "EMPLOYEE_ID")
	private Integer id;
	
	// JPA는 camel 표기법의 엔터티 필드 이름을 snake 표기법의 컬럼 이름으로 자동 맵핑한다. 
	// 필드: firstName <--> 컬럼 이름: first_name(FIRST_NAME)
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDate hireDate;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_ID") // JOBS 테이블
	private Job job; // 엔터티 타입으로 선언.
	
	private Double salary;
	
	private Double commissionPct;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID") // EMPLOYEES 테이블 셀프 조인
	private Employee manager;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID") // DEPARTMENTS 테이블
	private Department department;
	
}