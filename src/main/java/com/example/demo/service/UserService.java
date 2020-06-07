package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

	public User Sel(int id);
	//public User login(int id);
	
	public int login(String username,String password);
}
