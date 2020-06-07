package com.example.demo.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper {
	
	public void storage(Map<String,Object> params);
	public void delete(Integer id);
	public void selfIncrement(int clicks,int id);
	public void typeAccessStatistics(String nowDate,String type,String city);
	
}
