package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.example.demo.service.FileService;
//import com.example.demo.util.RedisUtil;

@Controller
@RequestMapping("/file")
public class FileController{
	
	@Autowired
    private FileService fileService;
//	@Resource
//	RedisUtil redisUtil;
	
	
	
	
	
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String uploadVideoFiles(
	        @RequestParam(value = "file") MultipartFile[] files,  //这样接收文件
	        @RequestParam("title") String content,
	        @RequestParam("type") String type,
	        //String classId,
	        HttpServletRequest request
	) {
	    try {
	    	

	    	 
	    	 
	    	String title=content;
	        Map<String,Object> params=new HashMap<String, Object>();
	        
	        //测试环境视频路径
	        String path="D:\\new\\video\\";
	        //测试环境缩略图路径
	        String framefile="D:\\new\\image\\";
	        
	        
	        
	        //生产环境视频路径
	        //String path="/soft/jar/static/video/";
	        //生产环境缩略图路径
	        //String framefile="/soft/jar/static/image/";
	        
//	        //测试异地保存
//	        String path="192.168.1.16/soft/test/static/video/";
//	        //生产环境缩略图路径
//	        String framefile="192.168.1.16/soft/test/static/image/";
	        
	        
	        
	        //int userId=((TSystemUser)request.getSession().getAttribute("USER")).getUserId();
	        //params.put("classId",classId);
	        params.put("attachmentType","VIDEO");
	        //params.put("userId",userId);
	        for (MultipartFile file : files) {    //循环保存文件
	            if(file.getSize()==0)//如果上传了空的文件，直接返回上传页面,防止进入404报错界面
	            {
	            	return "videoUpload";
	            }
	        	
	        	Map<String,String> name=uploadFile(path,file,request);//将视频重命名然后保存
	            
	            params.put("attachmentUrl","/video/"+name.get("saveName"));//将要存入数据库的视频地址
	            params.put("attachmentName",name.get("fileName"));
	            String thumbpath=name.get("saveName").substring(0,name.get("saveName").length()-4)+".jpg";//在修改过的视频名称的基础上改动得到缩略图名称

	            params.put("attachmentThumbnail","/image/"+thumbpath);//将要存入数据库的缩略图地址
	            params.put("title",title);
	            params.put("type",type);
	            
//	            System.out.println("D:/new/video/"+name.get("saveName"));
//	            System.out.println("D:/new/image/"+thumbpath);
	            //调用保存缩略图方法
	            this.fetchFrame(path+name.get("saveName"),framefile+thumbpath);//从传入的视频里获取缩略图并保存
	            //this.fetchFrame(path+name.get("saveName"),basePath+thumbpath);//从传入的视频里获取缩略图并保存

	            //保存入库
	            fileService.storage(params);
	        }
	        // 返回后台上传界面准备继续上传
	        return "videoUpload";
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return JSON.toJSONString("fail");
	    }
	 
	}
	
	
	
	
public Map<String,String> uploadFile(String path,MultipartFile file, HttpServletRequest request) throws IOException {
    Map<String,String> result=new HashMap<String,String>();
    
    String fileName = file.getOriginalFilename();//文件原名
    //String basePath=request.getSession().getServletContext().getRealPath("/");
    //String basePath=System.getProperty("user.dir")+"/src/main/webapp";
    //String basePath=System.getProperty("user.dir");
    //String basePath=System.getProperty("user.dir");
    //path="D:\\new\\video"; //设置视频保存路径
    //path="/soft/jar/static/video";//设置视频保存路径
    
    //System.out.println("uploadFile方法里basePath="+basePath);
   // System.out.println("uploadFile方法里path="+path);
    

    //File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));
    //文件类型
    String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();//获取视频后缀
    //保存的文件名
    String saveName=String.valueOf((new Date()).getTime()).substring(8)+(int)((Math.random()*999+1))+'.'+fileType;//重新命名后的文件(防重)
   
    
    File tempFile = new File(path, String.valueOf(saveName));
    if (!tempFile.getParentFile().exists()) {    //创建文件夹
        tempFile.getParentFile().mkdir();
    }
    if (!tempFile.exists()) {
        tempFile.createNewFile();
    }
    file.transferTo(tempFile);
    result.put("fileName",fileName);
    result.put("saveName",saveName);
    return result;
}
	


//参数:视频路径和缩略图保存路径
public static void fetchFrame(String videofile, String framefile)//(视频路径+名称，缩略图路径+名称,)
      throws Exception {
  long start = System.currentTimeMillis();
  File targetFile = new File(framefile);
  FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
  ff.start();
  int length = ff.getLengthInFrames();
  int i = 0;
  Frame f = null;
  while (i < length) {
      // 去掉前5帧，避免出现全黑的图片，依自己情况而定
      f = ff.grabImage();
      if ((i > 5) && (f.image != null)) {
          break;
      }
      i++;
  }
  ImageIO.write(FrameToBufferedImage(f), "jpg", targetFile);//创建缩略图名称
  //ff.flush();
  ff.stop();
  //System.out.println(System.currentTimeMillis() - start);
}

public static BufferedImage FrameToBufferedImage(Frame frame) {
  //创建BufferedImage对象
  Java2DFrameConverter converter = new Java2DFrameConverter();
  BufferedImage bufferedImage = converter.getBufferedImage(frame);
  return bufferedImage;
		}


/**跳转到播放页面*/
@CrossOrigin(origins = "*")
@RequestMapping("/play")
public String play(Model model,
				@RequestParam(name = "videoMessage") String videoMessage,
				@RequestParam(name = "clicks") int clicks,
				@RequestParam(name = "id") int id,
				@RequestParam(name = "nowDate") String nowDate,
				@RequestParam(name = "type") String type,
				@RequestParam(name = "city") String city
		){ 
	
	
//	 String a="山西省晋城市";
//	 int result1 = a.indexOf("省");
//	 String fin=a.substring(0,result1);
//	 System.out.println(result1);
//	 System.out.println(fin);
	
		
		int result1 = city.indexOf("省");
		if(result1!=-1)
		{
			city=city.substring(0,result1);
		}
		int result2 = city.indexOf("市");
		if (result2!=-1) {	
			city= city.substring(0,city.length() - 1);
	    }
		clicks++;

	
		

		/*
		 *redis接口
		 **注意:在配置的时候,不能忘了上面的
		 *@Resource
		 *RedisUtil redisUtil;
		 */

		//redis测试
//		String key = "user";
//		String value = "tom";
//		redisUtil.set(key, value);
//		String result = (String) redisUtil.get(key);
//		System.out.println(result);

	fileService.selfIncrement(clicks,id);//执行访问量加1
	fileService.typeAccessStatistics(nowDate,type,city);//将视频类型和访问时间,访问者地区信息插入数据库
	
	model.addAttribute("videoMessage",videoMessage);
	return "play";
	
}


/**后台删除操作*/
@CrossOrigin(origins = "*")
@RequestMapping("/delete")
public String delete(@RequestParam(name = "id") String id){ 
	int Id=Integer.valueOf(id);
	fileService.delete(Id);
	return "delete";
}


	



}