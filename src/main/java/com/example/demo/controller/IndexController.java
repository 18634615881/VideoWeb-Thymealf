package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Image;
import com.example.demo.entity.Page;
import com.example.demo.service.IndexService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	IndexService indexService;

    @Autowired
    private UserService userService;

	
	
    /**主页自动加载图片*/
	@CrossOrigin(origins = "*")
    @RequestMapping("/loadimage")
    @ResponseBody
    public List<Image> show(){ 
    	List<Image> result;
    	result=indexService.loadimage();
    	//JSON json=(JSON) JSON.toJSON(result);
    	return result;
    }
	
    /**跳转到主页*/
	
    @RequestMapping("/")
    public String index(){ 
    	return "index";
    }
    
    
    /**进行分页查询*/
    @CrossOrigin(origins = "*")
    @RequestMapping("/toPage")
    @ResponseBody
    public Map<String, Object> toPage(@RequestParam("start") String start,@RequestParam("type") String type){	
    	
    	
    	Page page=new Page();
    	page.setCount(10);//设置前台首页,后台删除界面每页的视频展示数量
    	List<Image> result;
    	Map<String,Object> results=new HashMap<String, Object>();
    	
    	page.setStart(Integer.parseInt(start));//起始页码
    	page.setTotal(indexService.count(type));//得到总条数
    	page.getTotalPage();//在page对象中计算总页数
    	page.isHasPreviouse();//计算是否有上一页
    	page.isHasNext();//计算是否有下一页
    	page.getLast();//计算得到尾页
    	
    	result=indexService.toPage(page,type);//搜索相关视频数据

    	results.put("result",result);
    	results.put("page", page);

    	return results;
    }
    

    /**跳转到测试页面*/
    @RequestMapping("/test")
    public String test(){ 
    	return "/commoon/testt";
    }







}
