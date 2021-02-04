package com.project.login.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.login.model.Login;

@Mapper
@Repository
public interface LoginRepository {
	List<Login> findAll();
}
