package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Access_statistics;
import com.example.demo.service.CountService;
import com.example.demo.service.UserService;



@Controller
@RequestMapping("/count")
public class CountController {

	@Autowired
    private CountService countService;
	
	@Autowired
	private UserService userService;
	

    public boolean loginInformationJudgment(HttpServletRequest request)//根据sessions中是否有用户信息来而判定登录状态
    {
    	HttpSession session = request.getSession();
    	String sessionUsername = (String) session.getAttribute("username");//取出sessions中存放的用户名密码信息
    	String sessionPassword=(String) session.getAttribute("password");
    	int sessionSelectResult=userService.login(sessionUsername,sessionPassword);
    	if(sessionSelectResult==1)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
	
	
    /*跳转到数据统计界面*/
    
    @CrossOrigin(origins = "*")
    @RequestMapping("/toCount")
    public String toNumberCount(HttpServletRequest request){
    	
    	boolean judgeMeng=loginInformationJudgment(request);
    	if(judgeMeng==true)
    	{
    	return "count";
    	}
    	else
    	{
    		return "login";
    	}
    }
    
    /*为统计界面的饼图查询统计的数据*/
    @CrossOrigin(origins = "*")
    @RequestMapping("/piechart")
    @ResponseBody
    public List<Access_statistics> piechart(){ 
    	List<Access_statistics> list;
    	list=countService.piechart();
    	return list;
    }
    
    
    /*为统计界面的柱状图查询统计的数据*/
    @CrossOrigin(origins = "*")
    @RequestMapping("/histogramQuery")
    @ResponseBody
    public List<Access_statistics> histogramQuery(){ 
    	List<Access_statistics> list;
    	list=countService.histogramQuery();
    	return list;
    } 
    
    /*为点击量数据分布图查询数据*/
    @CrossOrigin(origins = "*")
    @RequestMapping("/clickArea")
    @ResponseBody
    public List<Access_statistics> clickArea(){ 
    	List<Access_statistics> list;
    	list=countService.clickArea();
    	return list;
    } 
    

	
}
