package com.project.login.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.project.login.model.Login;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //service
public class LoginService {
	
	private final LoginRepository loginRepository;
	
	public void insert(Login login){
		loginRepository.save(login);
	}
}
