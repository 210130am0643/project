package com.project.login.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.login.model.Login;
import com.project.login.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/")
	public String index(){
		List<Login> list = loginService.findAll();
		System.out.println(list);
		return "index";
	}
}
