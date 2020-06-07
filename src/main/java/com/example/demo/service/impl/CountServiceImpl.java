package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Access_statistics;
import com.example.demo.mapper.CountMapper;
import com.example.demo.service.CountService;

@Service
public class CountServiceImpl implements CountService{
	@Autowired
	CountMapper countMapper;

	@Override
	public List<Access_statistics> piechart() {
		// TODO Auto-generated method stub
		List<Access_statistics> list;
		list=countMapper.piechart();
		return list;
	}
	
	public List<Access_statistics> histogramQuery() {
		// TODO Auto-generated method stub
		List<Access_statistics> list;
		list=countMapper.histogramQuery();
		return list;
	}
	
	public List<Access_statistics> clickArea() {
		// TODO Auto-generated method stub
		List<Access_statistics> list;
		list=countMapper.clickArea();
		return list;
	}
	
}
