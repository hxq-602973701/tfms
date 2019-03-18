<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统错误</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script language="javascript">
	$(function(){
    	$('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
   	 	$('.error').css({'position':'absolute','left':($(window).width()-490)/2});
    })  
});  
</script> 

</head>
<body style="background:#edf6fa;">

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">错误提示</a></li>
		</ul>
    </div>
    
    <div class="error">
	    <h2>${exception.message}</h2>
	    <p>看到这个提示，就自认倒霉吧!</p>
	    <div class="reindex">
	    	<a href="javascript:history.go(-1);" target="_parent">返回上一页</a>
	    </div>
    </div>
</body>
</html>
