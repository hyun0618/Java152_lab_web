package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드들을 초기화하는 생성자.
@Controller
@RequestMapping("/post") // --> PostController 클래스의 모든 컨트롤러 메서드의 매핑 주소는 "/post"로 시작.
public class PostController {
	
	private final PostService postService; // 생성자에 의한 의존성 주입
	
	
	
	@GetMapping ("/create") // 링크 클릭 --> Get방식
	public void create() {
		log.debug("GET: create()");
	}
	
//	@PostMapping ("/create") 
//	public String create(@RequestParam(name="title") String title, 
//					     @RequestParam(name="content") String content, 
//						 @RequestParam(name="author") String author) {
//		log.debug("POST: create(title={}, content={}, author={})", title, content, author);
//		return "";
//	}
//	각각 선언하지 않기위해 dto 설정. ==> PostCreateDto

// 작성
	@PostMapping ("/create")
	public String create(PostCreateDto dto) {
		log.debug("POST: create(dto={})", dto);
		
		// 서비스 컴포넌트의 메서드를 호출해 데이터베이스에 새 글을 저장.
		postService.create(dto);
		
		// 포스트 목록 페이지로 리다이렉트.
		return "redirect:/post/list"; 
	}

// 상세보기, 수정
	@GetMapping( {"/details", "/modify"} ) // ==> GET 방식의 "/post/details", "/post/modify" 2개의 요청을 처리하는 메서드.
	public void details(@RequestParam(name = "id") int id, Model model) {
		log.debug("details(id={}", id);
		
		// 서비스 컴포넌트의 메서드를 호출해서 해당 id의 포스트를 검색.
		Post post = postService.read(id);
		
		// 뷰에 데이터를 전달하기 위해서 model 객체에 post를 속성으로 추가.
		model.addAttribute("post", post);
		
		// 리턴타입이 void이므로 뷰의 이름은
		// (1) 요청 주소가 /post/details인 경우 --> /WEB-INF/views/post/details.jsp 
		// (2) 요청 주소가 /post/modify인 경우 --> /WEB-INF/views/post/modify.jsp 
	}

// 삭제
	@GetMapping("/delete")
	public String delete(@RequestParam(name="id") int id) {
		log.debug("delete(id={})", id);
		
		// 서비스 컴포넌트의 메서드를 호출해서 데이터베이스의 테이블에서 해당 아이디의 글을 삭제.
		postService.delete(id); // Controller ==> Service
		
		// 포스트 목록 페이지로 리다이렉트.
		return "redirect:/post/list"; 
	}

// 업데이트
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		postService.update(dto);
		
		return "redirect:/post/details?id=" + dto.getId(); 	
	}
	
// 검색
	@GetMapping("/search")
	public void search(PostSearchDto dto) {
		postService.search(dto);
	}
	
// 리스트
	@GetMapping("/list") // --> 뷰의 이름: "/WEB-INF/views/post/list.jsp"
	public void list(Model model) {
		log.debug("list()");
		
		// 서비스 컴포넌트의 메서드를 호출. 포스트 목록을 읽어 와서 뷰에 전달.
		List<PostListDto> list = postService.read();
		model.addAttribute("posts", list);

	}
}
