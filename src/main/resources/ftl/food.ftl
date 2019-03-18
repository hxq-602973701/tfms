<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="nav">
	<div class="main">
        <ul>
        	<li class="home"><a href="/">首页</a></li>
        	<#assign x = 1>        	
			<#list shopLabelList as shopLabel>
			<#if x%5==0>
			<li class="no"><a href="<%=request.getContextPath()%>/${shopLabel.id}/shop">${shopLabel.name}</a></li>
			<#else>
			<li><a href="<%=request.getContextPath()%>/${shopLabel.id}/shop">${shopLabel.name}</a></li>
			</#if>
			<#assign x = x+1>
			</#list>
		</ul>
    </div>
</div>