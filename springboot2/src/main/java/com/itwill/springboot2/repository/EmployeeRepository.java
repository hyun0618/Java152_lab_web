package com.itwill.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Employee;

/*
class A extends B {}

interface D {}
class C implements D {}

interface J {}
interface I extends J {} ==> 인터페이스가 인터페이스를 상속할 수 있음. 
*/

/*
Repository<T, ID>
|__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
	|__ JpaRepository<T, ID>

T: Entity 클래스, ID: Entity 클래스의 @Id 필드 타입 
*/

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
