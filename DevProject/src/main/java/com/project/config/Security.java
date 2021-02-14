package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * Security 파일을 생성 한뒤에는 login 페이지로 이동 안됨.
 *
 */
@Configuration //config 
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{
	
	@Bean // 리턴되는 오브젝트를 IoC에 등록
	public BCryptPasswordEncoder encPassword() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/main/**").authenticated()
		.antMatchers("/system/**").access("hasRole('ROLE_SYSTEM')")
		.and().formLogin().loginPage("/login");
	}
}
