<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jqprint/jquery.jqprint-0.3.js"></script>
<title>天福美食订单统计列表</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">订单统计</a></li>
    </ul>
</div>
    
<div class="rightinfo">
    
	<!-- 列表开始 -->
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>店铺名称</th>
		        <th>商铺联系人</th>
		        <th>商铺联系方式</th>
		        <th>查询时间段</th>
		        <th>总的订单数</th>
		        <th>总价格</th>
	        </tr>
        </thead>
        <tbody>
	        <tr>
		        <td>${orderTotalDto.name}</td>
		        <td>${orderTotalDto.bossname}</td>
		        <td>${orderTotalDto.bossphone}</td>
		        <td>${sdate}~${edate}</td>
		        <td>${orderTotalDto.num}</td>
		        <td>${orderTotalDto.total}</td>
	        </tr>
	        <tr>
	        	<td colspan="6"><center><span class="label btn-danger" onclick="javascript:history.go(-1);">返回</span></center></td>
	        </tr>
        </tbody>
   </table>
   <!-- 列表结束 -->
   
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
