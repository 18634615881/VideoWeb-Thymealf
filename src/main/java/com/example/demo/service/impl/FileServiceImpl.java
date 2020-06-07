package com.example.demo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.FileMapper;
import com.example.demo.service.FileService;
@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileMapper fileMapper;
	
	@Override
	public void storage(Map<String,Object> params) {
		
		fileMapper.storage(params);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		fileMapper.delete(id);
		
	}

	@Override
	public void selfIncrement(int clicks,int id) {
		// TODO Auto-generated method stub
		fileMapper.selfIncrement(clicks,id);
		
	}
	
	@Override
	public void typeAccessStatistics(String nowDate,String type,String city) {
		// TODO Auto-generated method stub
		fileMapper.typeAccessStatistics(nowDate,type,city);
		
	}
	
	
}
