package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Access_statistics;

public interface CountService {

	public List<Access_statistics> piechart();
	public List<Access_statistics> histogramQuery();
	public List<Access_statistics> clickArea();

}
