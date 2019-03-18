<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="nav">
	<div class="main">
        <ul>
        	<li class="home"><a href="<%=request.getContextPath()%>/index">首页</a></li>
			<#list shopLabelList as shopLabel>
			<li><a href="<%=request.getContextPath()%>/${shopLabel.id}/shop">${shopLabel.name}</a></li>
			</#list>
		</ul>
    </div>
</div>