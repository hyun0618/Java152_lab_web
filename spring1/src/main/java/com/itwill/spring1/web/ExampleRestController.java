package com.itwill.spring1.web;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itwill.spring1.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

/*
 * REST 서비스를 하는 컨트롤러 메서드를 작성하는 방법:
 * (1) @Controller 클래스에서 @ResponseBody 애너테이션을 선언한 메서드를 작성한다. 
 * (2) @RestController 클래스의 모든 컨트롤러 메서드들은 REST 서비스로 구현된다. 
 * ==> 컨트롤러가 리턴하는 값은 (뷰의 이름이 아니라) 응답으로 전송되는 데이터이다. 
 */

@Slf4j
@RestController // 해당 클래스 안의 모든 메서드는 rest 뷰의 이름을 리턴할 수 없다. 응답만 리턴할 수 있음.  
public class ExampleRestController {
	
	@GetMapping("/rest3")
	public String rest3() {
		log.debug("rest3()");
		
		return "안녕하세요, rest!";
	}
	
	@GetMapping("/rest4")
	public ArrayList<UserDto> rest4() {
		log.debug("rest4()");
		
		ArrayList<UserDto> list = new ArrayList<>(); 
		list.add(UserDto.builder().username("홍길동").age(16).build());
		list.add(UserDto.builder().username("오쌤").age(20).build());
		
		return list; // 2개의 객체를 가진 배열. 각각은 'username'과 'age'의 프로퍼티를 가짐. 
		
	}

}
