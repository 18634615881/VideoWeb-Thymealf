<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.row{text-align:center;}
</style>

<script src="/static/js/static.js"></script>

<body>


	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-md-4"><a id="a1">视频上传</a></div>
			<div class="col-md-4"></div>
		</div>
		
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-md-4"><a id="a2">视频删除</a></div>
			<div class="col-md-4"></div>
		</div>
		
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-md-4"><a id="a3">数量统计</a></div>
			<div class="col-md-4"></div>
		</div>
</div>
<script>
$('#a1').attr('href',MainAddress+"user/toVideoUpload");
$('#a2').attr('href',MainAddress+"user/contentManageSkipping");
$('#a3').attr('href',MainAddress+"count/toNumberCount");
</script>



</body>
</html>