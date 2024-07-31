package com.itwill.springboot5.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
import com.itwill.springboot5.service.PostService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller @RequestMapping("/post")
public class PostController {
	
	private final PostService postSvc;
	
	@GetMapping("/list")
    public void list(@RequestParam(name = "p", defaultValue = "0") int pageNo, Model model) {
        log.info("list(pageNo={})", pageNo);       
        // 서비스 계층의 메서드를 호출 -> 뷰에 포스트 목록 전달
        Page<PostListItemDto> list = postSvc.read(pageNo, Sort.by("modifiedTime").descending());
        model.addAttribute("page", list);
    }
	
//	@GetMapping("/list")
//	public void list(Model model) {		
//		log.info("list()");
//		// 서비스 계층의 메서드를 호출 --> 뷰에 포스트 목록 전달 
//		List<PostListItemDto> list = postSvc.read();
//		model.addAttribute("posts", list);
//	}
	
	@GetMapping("/create")
	public void create() {
		
	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.info("POST create(dto={})", dto);
		
		// 서비스 계층의 메서드를 호출해서 작성한 포스트를 DB에 저장.
		postSvc.create(dto);
	        
		return "redirect:/post/list";
	}
	 
//	@PostMapping("/create")
//	public String save(@RequestParam String title, @RequestParam String content, @RequestParam String author) {
//		PostCreateDto dto = new PostCreateDto();
//		dto.setTitle(title);
//		dto.setContent(content);
//		dto.setAuthor(author);
//		postSvc.create(dto);
//		return "redirect:/post/list";
//	}
	
	@GetMapping({ "/details", "/modify" })	
	public void details(@RequestParam Long id, Model model) {
		Post entity = postSvc.read(id);
		model.addAttribute("post", entity);
		 
		// --> view의 이름은 요청 주소가 "details"인 경우에는 details.html
		// 요청주소가 "modify"인 경우에는 modify.html
	 }
	  
//	@GetMapping("/details/{id}")	
//	public String list(@PathVariable Long id, Model model) {
//		Post post = postSvc.read(id);
//		model.addAttribute("post", post);
//		return "post/details";
//	 }
	 
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		postSvc.delete(id);
		return "redirect:/post/list"; 
	}
	 
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		postSvc.update(dto);
		return "redirect:/post/details?id=" + dto.getId(); 
	}
	
	// 클라이언트가 Dispatcher Servlet에 요청.(req.getParameter)
	// Dispatcher Servlet이 컨트롤러의 메서드를 호출.(아규먼트 전달)
//	@GetMapping("/search")  
//	public String search(PostSearchRequestDto dto, Model model) {
//		log.info("search(dto={})", dto);
//		
//		// 페이지 요청 생성
////		Sort sort = Sort.by(Sort.Direction.DESC, "modifiedTime");
////	    Page<PostListItemDto> result = postSvc.search(dto, sort);
//
//		Page<PostListItemDto> result = postSvc.search(dto, Sort.by("modifiedTime").descending());
//	    model.addAttribute("posts", result.getContent());
//	    model.addAttribute("page", result);
//
//	    return "post/list";
//	    
//	}
	
	@GetMapping("/search")
    public String search(PostSearchRequestDto dto, Model model) {
        log.info("search(dto={})", dto);

        try {
            if (dto.getCategory() != null) {
                dto.setCategory(URLDecoder.decode(dto.getCategory(), "UTF-8"));
            }
            if (dto.getKeyword() != null) {
                dto.setKeyword(URLDecoder.decode(dto.getKeyword(), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Error decoding search parameters", e);
        }

        Page<PostListItemDto> result = postSvc.search(dto, Sort.by("modifiedTime").descending());
        model.addAttribute("posts", result.getContent());
        model.addAttribute("page", result);

        // 검색 파라미터를 모델에 추가하여 페이지네이션에서 유지되도록 함
        model.addAttribute("category", dto.getCategory());
        model.addAttribute("keyword", dto.getKeyword());

        return "post/list";
    }

}
