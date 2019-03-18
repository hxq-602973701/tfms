<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—注册成功页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script type="text/javascript">
	var time = 4;  
	function returnUrlByTime() {  
	    window.setTimeout('returnUrlByTime()', 1000);  
	    time = time - 1;  
	    document.getElementById("layer").innerHTML = time;  
	} 
</script>
</head>

<body onload="returnUrlByTime()">
<script type="text/javascript">
	layer.msg('天福美食提醒您！注册成功<span id="layer">3</span>秒后返回首页',{
		icon: 6,
		time: 3000
	});
</script>
<%  
    //转向语句  
    response.setHeader("Refresh","3;URL='index'");  
%>  

</body>
</html>