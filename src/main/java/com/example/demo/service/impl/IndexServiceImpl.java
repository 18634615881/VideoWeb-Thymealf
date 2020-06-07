package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Image;
import com.example.demo.entity.Page;
import com.example.demo.mapper.IndexMapper;
import com.example.demo.service.IndexService;
@Service
public class IndexServiceImpl implements IndexService{
	
	@Autowired
	IndexMapper indexMapper; 
	
	@Override
	public List<Image> loadimage(){
		List<Image> result=indexMapper.loadimage();
		
		return result;
	}

	@Override
	public List<Image> toPage(Page page,String type) {
		// TODO Auto-generated method stub
		List<Image> result=indexMapper.toPage(page,type);
		return result;
	}

	@Override
	public int count(String type) {
		int count =indexMapper.count(type);
		return count;
	}



	
}
