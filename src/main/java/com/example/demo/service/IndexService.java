package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Image;
import com.example.demo.entity.Page;

public interface IndexService {
	public List<Image> loadimage();
	public int count(String type);
	
	public List<Image> toPage(Page page,String type);
	
}
