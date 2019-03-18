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
<title>食品类型列表</title>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">商铺列表</a></li>
    </ul>
</div>
    
<div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
	        <li class="click"><a href="add"><span><img src="${pageContext.request.contextPath}/resources/images/t01.png" /></span>添加</a></li>
        </ul>
	</div>
	<!-- 列表开始 -->
    <table class="tablelist">
    	<thead>
	    	<tr>
	    		<th>商品图片</th><!-- 35*35 -->
		        <th>商品名称</th>
		        <th>是否推荐</th>
		        <th>商品原价</th>
		        <th>商品现价</th>
		        <th>状态</th><!-- 上架，下架 -->
		        <th>所属类型</th>
		        <th>所属商店</th>
		        <th width="150px">管理操作</th>
	        </tr>
        </thead>
        <tbody>
        	 <c:forEach items="${datas.records}" var="food">
	        <tr>
	        	<td><img src="${pageContext.request.contextPath}/resources/upload/food/thumbnail/${food.url}"/></td>
	        	<td>${food.name}</td>
	        	<td>
	        		<c:if test="${food.isrecommend eq 1}">
		        		<a href="${food.id}/0/recommend"><span class="label label-success">推荐</span></a>
		        	</c:if>
		        	<c:if test="${food.isrecommend eq 0}">
		        		<a href="${food.id}/1/recommend"><span class="label btn-danger">不推荐</span></a>
		        	</c:if>
	        	</td>
		        <td>${food.oldprice}/份</td>
		        <td>${food.newprice}/份</td>
		        <td>
					<c:if test="${food.isdown eq 1}">
		        		<span class="label label-success">上架</span>
		        	</c:if>
		        	<c:if test="${food.isdown eq 0}">
		        		<span class="label btn-danger">下架</span>
		        	</c:if>
				</td>
				<td>${food.foodType.name}</td>
		        <td>${food.shop.name}</td>
		        <td>
		        	<a href="${food.id}/update" class="tablelink">修改</a>&nbsp;     
		        	<a href="javascript:del('${food.id}')" class="tablelink"> 删除</a>
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
        	<li class="paginItem"><a href="<%=request.getContextPath()%>/food/list?currentPage=1">首页</a></li>
        	<c:if test="${datas.currentPage==1}">
        		<li class="paginItem"><a href="javascript:void(0);">上一页</a></li>
        	</c:if>
        	<c:if test="${datas.currentPage!=1}">
        		<li class="paginItem"><a href="<%=request.getContextPath()%>/food/list?currentPage=${datas.currentPage-1}">上一页</a></li>
        	</c:if>
        	
	        <c:if test="${datas.currentPage==datas.pageCount}">
	        	<li class="paginItem"><a href="javascript:void(0);">下一页</a></li>
	        </c:if>
	        <c:if test="${datas.currentPage!=datas.pageCount}">
	        	<li class="paginItem"><a href="<%=request.getContextPath()%>/food/list?currentPage=${datas.currentPage+1}"">下一页</a></li>
	        </c:if>
	        <li class="paginItem"><a href="<%=request.getContextPath()%>/food/list?currentPage=${datas.pageCount}">尾页</a></li>
        </ul>
       	<div class="goPage">
       		<input id="pager" class="goPager" style="padding:6px;" type="text" placeholder="页数..."/>&nbsp;<input class="goPagerBtn" type="button" value="跳转" onclick="javascript:goPage();"/>
       	</div>
    </div>
    <!-- 分页结束 -->
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	function del(id){
		if(confirm("确定要删除吗？")){
			var url = id+"/delete";
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
		window.location.href="${pageContext.request.contextPath}/food/list?currentPage="+page;
	}
</script>
</body>
