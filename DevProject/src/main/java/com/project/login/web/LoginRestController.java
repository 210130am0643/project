package com.project.login.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.login.model.Login;
import com.project.login.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginRestController {
	
	private final LoginService loginService;
//	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/join")
	public @ResponseBody void join( Login login) {
		
		loginService.insert(login);
//		String oriPassword = login.getPassword();
//		String encPassword = bCryptPasswordEncoder.encode(oriPassword);
//		log.debug("ori password : {}",oriPassword);
//		log.debug("enc password : {}",encPassword);
	}
	
}
