<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食头页面</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>
<body style="background:url(${pageContext.request.contextPath}/resources/images/topbg.gif) repeat-x;">
    <div class="topleft">
    	<a href="main.html" target="_parent">
    		<img src="${pageContext.request.contextPath}/resources/images/logo.png" title="系统首页" />
    	</a>
    </div>
    <div class="topright">    
	    <ul>
		    <li><span><img src="${pageContext.request.contextPath}/resources/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
		    <li><a href="#">关于</a></li>
		    <li><a href="<%=request.getContextPath()%>/admin/alogout" target="_parent">退出</a></li>
	    </ul>
    	<div class="user">
	   	 	<span>${sessionScope.admin.name}</span>
	    	<i>&nbsp;&nbsp;</i>
   	 	</div>    
    </div>
</body>
</html>
