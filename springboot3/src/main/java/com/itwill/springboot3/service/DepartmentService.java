package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentService {

	private final DepartmentRepository deptRepo;
	
	@Transactional(readOnly = true)
	public Page<Department> read(int pageNo, Sort sort) {	
		log.info("read()");
		
		Pageable pageable = PageRequest.of(pageNo, 5, sort);	
		return deptRepo.findAll(pageable);	
	}
	
	@Transactional(readOnly = true)
	public Department read(Integer id) {
		log.info("read(id={})", id);
		
		return deptRepo.findById(id).orElseThrow();		
	}
	
	
//	
//	public Department read(Integer id) {
//		return deptRepo.findById(id).orElseThrow();	
//	}
	
}
