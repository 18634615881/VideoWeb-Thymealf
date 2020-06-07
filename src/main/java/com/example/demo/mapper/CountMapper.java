package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Access_statistics;

@Repository
public interface CountMapper {

	public List<Access_statistics> piechart();
	public List<Access_statistics> histogramQuery();
	public List<Access_statistics> clickArea();


}
