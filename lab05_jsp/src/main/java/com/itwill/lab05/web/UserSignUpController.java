package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "userSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
	
	private final UserService userService = UserService.INSTANCE;
	
	// 회원 가입에 필요한 요청 처리 메서드. 	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) // 회원가입 할게요~
			throws ServletException, IOException {
		log.debug("doGet()");
		
		req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) // 양식 작성했어요~ㄴ
			throws ServletException, IOException {
		log.debug("doPost()");
		
		// 회원가입 폼에 제출된 userid, password, email 요청 파라미터 값을 읽음.
		String userid = req.getParameter("userid"); // 'input'에서의 'name'속성
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		User user = User.builder()
				.userid(userid).password(password).email(email)
				.build();
		log.debug("post={}", user);
		
		userService.signUp(user);
		
		String url = req.getContextPath() + "/";
		resp.sendRedirect(url);
	}
	
}
