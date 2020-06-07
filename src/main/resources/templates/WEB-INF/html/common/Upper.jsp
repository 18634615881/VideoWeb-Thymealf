<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script src="/static/js/static.js"></script>

<style>

/*
生产环境下为0px,测试环境下设置为1
div{border:0px solid red;}
*/
div{border:1px solid red;}
.topimg{width:100%;height:50px;};
</style>

</head>
<body>
<div class="container-fluid">

		
		<div class="row">
		<div class="col-md-2" align="center">&nbsp;</div>
		<div class="col-md-8" align="center">&nbsp;</div>
		<div class="col-md-2" align="center">&nbsp;</div>		
		</div>

		<div class="row">
		<div class="col-md-2" align="center">&nbsp;</div>
		<div class="col-md-8" align="center">&nbsp;</div>
		<div class="col-md-2" align="center">&nbsp;</div>		
		</div>

		<div class="row">
		<div class="col-md-2" align="center">&nbsp;</div>
		<div class="col-md-8" align="center">&nbsp;</div>
		<div class="col-md-2" align="center">&nbsp;</div>		
		</div>
		
		<div class="row">
		<div class="col-md-2" align="center">&nbsp;</div>
		<div class="col-md-8" align="center">&nbsp;</div>
		<div class="col-md-2" align="center">&nbsp;</div>		
		</div>
		
		
		
		<div class="row">
		<div class="col-md-2" align="center">&nbsp;</div>
		<div class="col-md-8" align="center">&nbsp;</div>
		<div class="col-md-2" align="center">&nbsp;</div>		
		</div>

		<div class="row">
		<div class="col-md-2" align="center">&nbsp;</div>
		<div class="col-md-8" align="center">&nbsp;</div>
		<div class="col-md-2" align="center">&nbsp;</div>
		</div>

		<div class="row">
		<div class="col-md-2" align="center">&nbsp;</div>
		<div class="col-md-8" align="center">
		<table class="table table-bordered">
	<caption>视频类别</caption>
<!-- 
	<thead>
	
		<tr>
			<th>名称</th>
			<th>城市</th>
			<th>邮编</th>
			<th>名称</th>
			<th>城市</th>
			<th>邮编</th>
		</tr>
	</thead>
	 -->
	<tbody align="center">
	
		<!-- <tr>
			<td>Sachin</td>
			<td>Mumbai</td>
			<td>400003</td>
			<td>Tanmay</td>
			<td>Bangalore</td>
			<td>560001</td>
		</tr>
		-->
		<tr>
		<!-- 
			<td><button type="button" class="btn btn-default" id="gequmv">歌曲mv</button></td>
			<td><button type="button" class="btn btn-default" id="gaoxiaoshipin">搞笑视频</button></td>
			 -->
			<td colspan="3"><a href="#" id="gequmv" >歌曲mv</a></td>
			<td colspan="3"><a href="#" id="gaoxiaoshipin" >搞笑视频</a></td>

		</tr>
	</tbody>
</table>
		</div>
		<div class="col-md-2" align="center">&nbsp;</div>		
		</div>



</div>

</body>
<script>

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







var determine=0;

$("#gequmv").click(function(){
	 if(determine==0)
	 {
	 //加载分页
	 	var paging="<div class=\"row\" id=\"row\"><div class=\"col-md-2\" align=\"center\">分页</div><div class=\"col-md-8\" align=\"center\"><ul class=\"pagination\"><li><a href=\"#\" id=\"home\">首页</a></li><li><a href=\"#\" id=\"previous\">上一页</a></li><li><a href=\"#\" id=\"next\">下一页</a></li><li><a href=\"#\" id=\"last\">尾页</a></li></ul></div><div class=\"col-md-2\" align=\"center\">分页</div></div>";
		 $("#div1").append(paging);
		 determine=1;
	 }
	  	 toPage(0,"歌曲mv");
		 $("#ul1 li").css({float:"left",margin:"30px 30px 30px 30px"});
		 $(".li1").css({width:"220px",height:"250px"});

	});
	
$("#gaoxiaoshipin").click(function(){
	 if(determine==0)
	 {
		//加载分页
	 	//alert(determine);
	 	var paging="<div class=\"row\" id=\"row\"><div class=\"col-md-2\" align=\"center\">分页</div><div class=\"col-md-8\" align=\"center\"><ul class=\"pagination\"><li><a href=\"#\" id=\"home\">首页</a></li><li><a href=\"#\" id=\"previous\">上一页</a></li><li><a href=\"#\" id=\"next\">下一页</a></li><li><a href=\"#\" id=\"last\">尾页</a></li></ul></div><div class=\"col-md-2\" align=\"center\">分页</div></div>";
		 $("#div1").append(paging);
		 determine=1;
	 }
    toPage(0,"搞笑视频");
	 $("#ul1 li").css({float:"left",margin:"30px 30px 30px 30px"});
	 $(".li1").css({width:"220px",height:"250px"});

	 
	 /*要添加标题模板的话，直接以上面的2个标题为例，复制粘贴之后，设置jquery获取标签即可*/
	 //<img src="/static/image/top1.gif" class="topimg">
	 //图片标号:112234
});
</script>




</html>