package com.bitacademy.jblog.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.jblog.repository.UserVo;
import com.bitacademy.jblog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm() {
		
		logger.debug("Join");
		return "user/joinform";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinAction() {
		return "user/joinform";
		
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		
		logger.debug("Join");
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
		
		UserVo authMember = userService.getUser(id, password);
		if (authMember == null) {
			//	사용자를 찾지 못함
			return "redirect:/user/login";
		} else {
			//	세션 등록 후 홈페이지로 리다이렉트
			session.setAttribute("authMember", authMember);
			return "redirect:/";
		}
		
		//return "user/loginform";
		
	}
}

