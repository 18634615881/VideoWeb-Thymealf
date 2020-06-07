
//日本服务器
//var MainAddress="http://45.63.127.89:8080/";

//本地Ubuntu服务器
//var MainAddress="http://192.168.1.16:8080/";

//本地测试
var MainAddress="http://localhost:8080/";


//阿里云服务器
//var MainAddress="http://47.92.160.64:8080/";

//document.write("<link rel=\"stylesheet\" href=\"https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css\">");  
//document.write("<script src=\"https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js\"></script>");
//document.write("<script src=\"https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js\"></script>");

document.write("<link rel=\"stylesheet\" href=\"/css/bootstrap.min.css\">");  
document.write("<script src=\"/js/jquery.min.js\"></script>");
document.write("<script src=\"/js/bootstrap.min.js\"></script>");

function toPage(obj,type)//封装分页方法
{
	var url = MainAddress+"index/toPage";
	var start=0;
	$.ajax({
        type: "GET",
        dataType: "text",
        url: url,
        data: {start:obj,type:type},
        async: false,
        success: function (result) {
        	
			var map = JSON.parse(result);
			var page=map.page;
			var obj=map.result;	
			var title=map.title;
			//console.log(map);
        	var element='';
        	
			 var current=page.start;//起始索引
			 var PageCount=page.totalPage;//总页数
			 var hasPreviouse=page.hasPreviouse;//是否有上一页
			 var hasNext=page.hasNext;//是否有下一页
			 var count=page.count;//每页显示数量
			 var last=page.last;//尾页跳转索引
        	
        	
        	//obj = JSON.parse(result);
        	$("#ul1").children().remove();
        	
        	/*获取当前访问时间，精确到年月日*/
        	var date = new Date();
        	var year = date.getFullYear();
        	var month = date.getMonth() + 1;
        	var day = date.getDate();
        	if (month < 10) {
        	    month = "0" + month;
        	}
        	if (day < 10) {
        	    day = "0" + day;
        	}
        	var nowDate = year + "-" + month + "-" + day;
        	//console.log(nowDate);
   			//console.log(returnCitySN["cip"]+','+returnCitySN["cname"]);
        	//var ip=returnCitySN["cip"];//获取当前访问者的IP
			var city=returnCitySN["cname"];//获取当前访问者所在城市
        	
        	
        	for(var i=0;i<obj.length;i++)
				{
				element+="<li><a target=\"_blank\" href=\""+MainAddress+"file/play?videoMessage="+obj[i].videolocation+"&clicks="+obj[i].clicks+"&id="+obj[i].id+"&nowDate="+nowDate+"&city="+city+"&type="+obj[i].type+"\"><image class=\"li1\" src=\""+obj[i].location+"\"><br>"+obj[i].title+"</a><span style=\"margin-left:80px;\">☆"+obj[i].clicks+"</span></li>";
				}
			 //console.log(MainAddress);
			 $("#ul1").append(element);
			 
			 //为加载的视频列表设置样式
			 $("#ul1 li").css({float:"left",margin:"30px 30px 30px 30px"});
			 $(".li1").css({width:"220px",height:"250px"});
			 
			 //console.log(page);
			 $("#home").off("click").on("click",(function(){
			 	  toPage(0,type);
			 	  //alert("首页跳转");	
			 	}));

			 //下一页跳转
			 $("#next").off("click").on("click",(function(){
			 	if(hasNext==true)
			 	  {toPage(current+count,type);}
			 		//alert("下一页跳转");	
			 }));

			 //上一页跳转
			 $("#previous").off("click").on("click",(function(){
			 	if(hasPreviouse==true)
			 		{toPage(current-count,type);}
			 	}));

			 //尾页跳转
			 $("#last").off("click").on("click",(function(){
			 		toPage(last,type);

			 	}));
			 
        },
        error:function (result) {
            //alert('首页跳转失败！');
            console.log("首页跳转失败");
        }
    });
	
}









