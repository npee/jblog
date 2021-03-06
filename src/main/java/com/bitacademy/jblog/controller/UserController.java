package com.bitacademy.jblog.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.jblog.repository.BlogVo;
import com.bitacademy.jblog.repository.UserVo;
import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm() {
		
		logger.debug("Join");
		return "user/joinform";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinAction(@ModelAttribute UserVo vo) {
		
		boolean isSuccess = false;
		boolean isSuccessJoin = false;
		isSuccess = userService.join(vo);
		vo.setUserName(vo.getUserName() + "의 블로그입니다.");
		isSuccessJoin = userService.joinBlog(vo);
		logger.debug("join: " + vo);
		if (isSuccess && isSuccessJoin) {
			return "redirect:/user/joinsuccess";			
		} else {
			return "redirect:/user/join";
		}
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		
		logger.debug("Login");
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="password", required=false) String password,
			HttpSession session) {
		if (id.length() == 0 || password.length() == 0) {
			System.err.println("로그인 할 수 없음");
			return "redirect:/user/login";
		}
		
		UserVo authUser = userService.getUser(id, password);
		

		if (authUser == null) {
			//	사용자를 찾지 못함
			logger.debug("Login Failed");
			return "redirect:/user/login";
		} else {
			BlogVo authBlog = blogService.getBlogByUserNo(authUser.getUserNo());
			

			//	세션 등록 후 홈페이지로 리다이렉트
			session.setAttribute("authUser", authUser);
			session.setAttribute("authBlog", authBlog);

			logger.debug("Login Success");
			logger.debug("authUser: " + authUser);
			logger.debug("authBlog: " + authBlog);

			return "redirect:/";
		}

		//return "user/loginform";	
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutAction(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}

