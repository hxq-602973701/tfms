<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="banner" id="cqh-box">
	<ul>
		<#list activityList as activity>
		<li><a href="<%=request.getContextPath()%>/${activity.id}/activityShow" target="_blank"><img src="<%=request.getContextPath()%>/resources/upload/activity/${activity.url}" alt="${activity.title}"/></a></li>
		</#list>
	</ul>
	<div id="cqh-num"></div>
	<div id="cqh-next"></div>
</div>