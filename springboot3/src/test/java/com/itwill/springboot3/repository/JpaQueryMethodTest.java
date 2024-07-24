package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JpaQueryMethodTest {
	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	public void test() {
		List<Employee> list; 
		// 1. 
//		list = empRepo.findByDepartmentId(30);
		
		// 2.
//		list = empRepo.findByFirstName("Steven");
		
		// 3. 
//		list = empRepo.findByFirstNameContaining("te");
//		list = empRepo.findByFirstNameLike("%te%");
		
		// 4.
//		list = empRepo.findByFirstNameContainingIgnoreCase("st");
		
		// 5.
//		list = empRepo.findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc("st");
		
		// 6. 
//		list = empRepo.findBySalaryGreaterThan(10_000.0);
		
		// 7.
//		list = empRepo.findBySalaryLessThan(2_500.);
		
		// 8.
//		list = empRepo.findBySalaryBetween(10_000., 15_000.);
		
		// 9.
//		list = empRepo.findByHireDateBefore(LocalDate.of(2002, 6, 7)); // 미만
		
		// 10.
//		list = empRepo.findByHireDateAfter(LocalDate.of(2008, 2, 6)); // 초과
		
		// 11.
//		list = empRepo.findByHireDateBetween(LocalDate.of(2003, 5, 1), LocalDate.of(2003, 10, 17)); // 이상 and 이하
		
		// 12.
//		list = empRepo.findByDepartmentDepartmentName("IT");
		
		// 13.
//		list = empRepo.findByDepartmentLocationCity("Seattle");
		
		// 14.
//		list = empRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("t", "ol");
		
		// 15.
//		list = empRepo.findByName("ter", "tes");
		
		// 16.
//		list = empRepo.findByName2("ter", "tes");
		
		// 17.
//		list = empRepo.findByName3("te");
		
		// 18.
//		list = empRepo.findByDeptName("IT");
		
		// 19.
//		list = empRepo.findByCity("Toronto");
		
		// 20.
		list = empRepo.findByCountry("Germany");
		
		list.forEach(System.out::println);

	}
	
}
