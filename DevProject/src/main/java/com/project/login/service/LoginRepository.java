package com.project.login.service;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.login.model.Login;

public interface LoginRepository  extends JpaRepository<Login,String>{ //model, primary key type
	
}
