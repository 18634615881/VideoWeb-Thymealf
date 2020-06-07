package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Image;
import com.example.demo.entity.Page;

@Repository
public interface IndexMapper {
	public List<Image> loadimage();
	public List<Image> toPage(@Param("page")Page page,@Param("type")String type);
	public int count(@Param("type")String type);
}
