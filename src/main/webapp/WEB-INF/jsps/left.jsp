<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食左侧页面</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
});
</script>
</head>
<body style="background:#f0f9fd;">
	<div class="lefttop">
		<span></span>通讯录
	</div>
    <dl class="leftmenu">
	    <dd>
	    	<div class="title"><span><img src="${pageContext.request.contextPath}/resources/images/leftico03.png" /></span>订单处理</div>
	    	<ul class="menuson">
	    		<c:if test="${sessionScope.admin.state eq 0}">
		        <li><cite></cite><a href="order/newOrderList" target="rightFrame">新增订单列表</a><i></i></li>
		        <li><cite></cite><a href="order/acceptOrderList" target="rightFrame">已接收订单列表</a><i></i></li>
		        </c:if>
		        <c:if test="${sessionScope.admin.state eq 1}">
		        <li><cite></cite><a href="order/failOrderList" target="rightFrame">取消订单列表</a><i></i></li>
		        <li><cite></cite><a href="order/successOrderList" target="rightFrame">完成订单列表</a><i></i></li>
	    		</c:if>
	    	</ul>    
	    </dd>
	    <dd>
	    	<div class="title"><span><img src="${pageContext.request.contextPath}/resources/images/leftico03.png" /></span>用户管理</div>
	    	<ul class="menuson">
		       	<li><cite></cite><a href="user/list" target="rightFrame">用户管理</a><i></i></li>
		        <c:if test="${sessionScope.admin.state eq 1}">
		        <li><cite></cite><a href="admin/list" target="rightFrame">管理员管理</a><i></i></li>
	    		</c:if>
	    	</ul>    
	    </dd>
	    <dd>
	    	<div class="title">
	    		<span>
	    			<img src="${pageContext.request.contextPath}/resources/images/leftico04.png" />
	    		</span>信息设置
	    	</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="activity/list" target="rightFrame">活动管理</a><i></i></li>
		        <li><cite></cite><a href="notice/list" target="rightFrame">公告管理</a><i></i></li>
		    </ul>
	    </dd>
	    <dd>
	    	<div class="title"><span><img src="${pageContext.request.contextPath}/resources/images/leftico03.png" /></span>评论设置</div>
	    	<ul class="menuson">
		        <li><cite></cite><a href="comment/list" target="rightFrame">评论列表</a><i></i></li>
	    	</ul>
	    </dd>
	    <dd>
	    	<div class="title">
	    		<span>
	    			<img src="${pageContext.request.contextPath}/resources/images/leftico04.png" />
	    		</span>基本设置
	    	</div>
		    <ul class="menuson">
		        <li><cite></cite><a href="shopLabel/list" target="rightFrame">商铺标签管理</a><i></i></li>
		    	<li><cite></cite><a href="shop/list" target="rightFrame">商铺管理</a><i></i></li>
		    	<li><cite></cite><a href="foodType/list" target="rightFrame">美食类型管理</a><i></i></li>
		        <li><cite></cite><a href="food/list" target="rightFrame">美食管理</a><i></i></li>
		    </ul>
	    </dd>
	    
	     <dd>
	    	<div class="title">
	    		<span><img src="${pageContext.request.contextPath}/resources/images/leftico03.png" />
	    		</span>系统设置
	    	</div>
	    	<ul class="menuson">
		        <li><cite></cite><a href="channel/list" target="rightFrame">模块管理</a><i></i></li>
		        <li><cite></cite><a href="article/list" target="rightFrame">模块内容管理</a><i></i></li>
	    	</ul>    
	    </dd>
    </dl>
</body>
</html>