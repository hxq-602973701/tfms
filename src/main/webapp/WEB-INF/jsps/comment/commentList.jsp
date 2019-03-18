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
        	<li>
	        	<form name="fom" id="fom" action="list">
	        	<span><img src="${pageContext.request.contextPath}/resources/images/ico06.png" height="30px"/></span><input name="search" style="height:31px;" type="text" value="${search}" class="dfinput" placeholder="请输入店铺名称..."/>
	        	&nbsp;&nbsp;<input id="save" style="height:30px;width:80px;background-color:#F4F7F8;border-radius:4px;" name="" type="submit" value="提交"/>
	        	</form>
	        </li>
        </ul>
	</div>
	<!-- 列表开始 -->
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>商店名称</th>
		        <th>评论内容</th>
		        <th>发布时间</th>
		        <th>发布人</th>
		        <th width="250px">管理操作</th>
	        </tr>
        </thead>
        <tbody>
        	 <c:forEach items="${datas.records}" var="comment">
	        <tr>
		        <td>${comment.shop.name}</td>
		        <td>${comment.content}</td>
		        <td>${comment.replyTime}</td>
		        <td>${comment.user.username}</td>
		        <td>
		        	<a href="javascript:del('${comment.id}')" class="tablelink"> 删除</a>
		        </td>
	        </tr>
	        </c:forEach>
        </tbody>
   </table>
   <!-- 列表结束 -->
   
     <!-- 分页 开始-->
   <div class="pagin">
    	<div class="message">共<i class="blue">${datas.recordCount}</i>条记录，总页数/当前页&nbsp;[<i class="blue">${datas.pageCount}/${datas.currentPage}</i>]</div>
        <ul class="paginList" style="float：left;">
        	<li class="paginItem"><a href="<%=request.getContextPath()%>/comment/list?currentPage=1&search=${search}">首页</a></li>
        	<c:if test="${datas.currentPage==1}">
        		<li class="paginItem"><a href="javascript:void(0);">下一页</a></li>
        	</c:if>
        	<c:if test="${datas.currentPage!=1}">
        		<li class="paginItem"><a href="<%=request.getContextPath()%>/comment/list?currentPage=${datas.currentPage-1}&search=${search}">上一页</a></li>
        	</c:if>
        	
	        <c:if test="${datas.currentPage==datas.pageCount}">
	        	<li class="paginItem"><a href="javascript:void(0);">下一页</a></li>
	        </c:if>
	        <c:if test="${datas.currentPage!=datas.pageCount}">
	        	<li class="paginItem"><a href="<%=request.getContextPath()%>/comment/list?currentPage=${datas.currentPage+1}&search=${search}">下一页</a></li>
	        </c:if>
	        <li class="paginItem"><a href="<%=request.getContextPath()%>/comment/list?currentPage=${datas.pageCount}&search=${search}">尾页</a></li>
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
		window.location.href="${pageContext.request.contextPath}/comment/list?currentPage="+page;
	}
</script>
</body>
