package com.example.demo.controllerTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoTest {
	
    /**跳转到测试页面*/
    @RequestMapping("/index")
    public String test(){ 
    	return "index";
    }

}
