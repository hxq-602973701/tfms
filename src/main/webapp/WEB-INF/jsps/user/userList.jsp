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
<title>天福美食用户列表</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">用户列表</a></li>
    </ul>
</div>
    
<div class="rightinfo">
    
	<!-- 列表开始 -->
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>用户名称</th>
		        <th>用户名</th>
		        <th>性别</th>
		        <th>Email</th>
		        <th>联系方法</th>
		        <th>注册时间</th>
		        <th width="250px">管理操作</th>
	        </tr>
        </thead>
        <tbody>
        	 <c:forEach items="${datas.records}" var="user1">
	        <tr>
		        <td>${user1.name}</td>
		        <td>${user1.username}</td>
		        <td>${user1.sex}</td>
		        <td>${user1.email}</td>
		        <td>${user1.phone}</td>
		        <td>${user1.regDate}</td>
		        <td>
		        	<a href="javascript:del('${user1.id}');" class="tablelink"> 删除</a>
		        </td>
	        </tr>
	        </c:forEach>
        </tbody>
   </table>
   <!-- 列表结束 -->
   
   <!-- 分页 开始-->
   <div class="pagin">
    	<div class="message">总计:<i class="blue">${datas.recordCount}</i>条&nbsp;&nbsp;每页<i class="blue">${datas.pageSize}</i>条&nbsp;&nbsp;当前第<i class="blue">${datas.currentPage}</i>页</div>
        <ul class="paginList" style="float：left;">
        	<li class="paginItem"><a href="<%=request.getContextPath()%>/user/list?currentPage=1">首页</a></li>
        	<c:if test="${datas.currentPage==1}">
        		<li class="paginItem"><a href="javascript:void(0);">上一页</a></li>
        	</c:if>
        	<c:if test="${datas.currentPage!=1}">
        		<li class="paginItem"><a href="<%=request.getContextPath()%>/user/list?currentPage=${datas.currentPage-1}">上一页</a></li>
        	</c:if>
        	
	        <c:if test="${datas.currentPage==datas.pageCount}">
	        	<li class="paginItem"><a href="javascript:void(0);">下一页</a></li>
	        </c:if>
	        <c:if test="${datas.currentPage!=datas.pageCount}">
	        	<li class="paginItem"><a href="<%=request.getContextPath()%>/user/list?currentPage=${datas.currentPage+1}"">下一页</a></li>
	        </c:if>
	        <li class="paginItem"><a href="<%=request.getContextPath()%>/user/list?currentPage=${datas.pageCount}">尾页</a></li>
        </ul>
       	<div class="goPage">
       		<input id="pager" class="goPager" style="padding:6px;" type="text" placeholder="页数..."/>&nbsp;<input class="goPagerBtn" type="button" value="跳转" onclick="javascript:goPage();"/>
       	</div>
    </div>
    <!-- 分页结束 -->
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = id+"/delete?currentPage="+${datas.currentPage};
			window.location.href=url;		
		}
	}
	
	function goPage()
	{
		var page = $("#pager").val();
		//正整数正则表达式不能写成 "^[0-9]*[1-9][0-9]*$",而是在正则表达式前后加上/
		var reg = /^[0-9]*[1-9][0-9]*$/;
        if(!reg.test(page)) 
        { 
        	alert("输入的页数不合法!"); 
        	$("#pager").val('');
        	return false;
        }
		window.location.href="${pageContext.request.contextPath}/user/list?currentPage="+page;
	}
</script>
</body>
