<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<title>天福美食公告列表</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">公告列表</a></li>
    </ul>
</div>
    
<div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
	        <li class="click"><a href="add"><span><img src="${pageContext.request.contextPath}/resources/images/t01.png" /></span>添加公告信息</a></li>
        </ul>
	</div>
	<!-- 列表开始 -->
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>名称</th>
		        <th>发布时间</th>
		        <th width="250px">管理操作</th>
	        </tr>
        </thead>
        <tbody>
        	 <c:forEach items="${articleList}" var="article">
	        <tr>
		        <td>${article.title.name}</td>
		        <td>${article.createTime}</td>
		        <td>
		        	<a href="${article.id}/update" class="tablelink">修改</a>&nbsp;   
		        	<a href="javascript:del('${article.id}')" class="tablelink"> 删除</a>
		        </td>
	        </tr>
	        </c:forEach>
        </tbody>
   </table>
   <!-- 列表结束 -->
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = id+"/delete";
			window.location.href=url;		
		}
	}
</script>
</body>
