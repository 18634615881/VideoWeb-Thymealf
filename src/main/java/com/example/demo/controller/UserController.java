package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.verificationcode.RandomValidateCodeUtil;

@Controller
@RequestMapping("/user")
public class UserController {
 
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	
    @Autowired
    private UserService userService;
 
    @CrossOrigin(origins = "*")
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
    
    
      
    /**登录跳转 */
    @CrossOrigin(origins = "*")
    @RequestMapping("/login")
    public String tologin(){ 
    	return "login";
    } 
    
    
    
    
    /**登录校验 (用户名,密码,验证码)*/
    @CrossOrigin(origins = "*")
    @RequestMapping("/tologin")
    @ResponseBody
    public String login(@RequestParam("userName") String username,@RequestParam("passWord") String password,@RequestParam("verify_input") String verifyInput,HttpServletRequest request){  	
    	HttpSession session = request.getSession();
    	int result=userService.login(username,password);
    	String inputStr=verifyInput;
    	String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");//取出sessions中RandomValidateCodeUtil类生产的验证码
    	int determine;//验证码的判定
    	if (random == null) {
    		determine=0;
        }
        if (random.equals(inputStr)) {
        	determine=1;
        } else {
        	
            determine=0;;
        }
    	
    	if(result==1 && determine==1)
    	{
    		session.setAttribute("username",username);//如果用户名密码正确，就把用户名密码放入sessions中
    		session.setAttribute("password",password);
    		
    		return "1";//用户名密码和验证码都正确
    	}
    	else
    	{
    		return "0";//用户名密码,验证码都错误
    	}
    
    } 
    
    /**跳转到后台管理员界面 */
    @RequestMapping("/toVideoUpload")
    public String tomanage(HttpServletRequest request){ 
    	boolean judgeMeng=loginInformationJudgment(request);
    	if(judgeMeng==true)
    	{
    	return "videoupload";
    	}
    	else
    	{
    		return "login";
    	}
    } 
    
    
    
    /**生成验证码*/

	@CrossOrigin(origins = "*")
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }
    
    /**校验验证码*/
	@CrossOrigin(origins = "*")
    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST,headers = "Accept=application/json")
    public String checkVerify(@RequestParam String verifyInput, HttpSession session) {
        try{
            //从session中获取随机数
            String inputStr = verifyInput;
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return "login";
            }
            if (random.equals(inputStr)) {
                return "videoUpload";
            } else {
                return "login";
            }
        }catch (Exception e){
            logger.error("验证码校验失败", e);
            return "login";
        }
    }


	 /**跳转到后台视频内容删除界面*/
    @RequestMapping("/toDelete")
    public String Manageskipping(HttpServletRequest request){
    	
    	boolean judgeMeng=loginInformationJudgment(request);
    	if(judgeMeng==true)
    	{
    	return "delete";
    	}
    	else
    	{
    		return "login";
    	}
    	
    }
    


}


