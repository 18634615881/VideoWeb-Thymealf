package com.example.demo.service;

import java.util.Map;

public interface FileService {
	public void storage(Map<String,Object> params);
	public void delete(Integer id);
	public void selfIncrement(int clicks,int id);
	public void typeAccessStatistics(String nowDate,String type,String city);
	
}
