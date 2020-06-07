package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;


	@Service
	public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    public User Sel(int id){
        return userMapper.Sel(id);
    	}
	
    
	public int login(String username,String password) {
		// TODO Auto-generated method stub
		int result=userMapper.login(username,password);
		return result;
	}


	
	
//    public User login(){
//        return userMapper.Sel(id);
//    }
//	
}

