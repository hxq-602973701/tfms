<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="module_sidebar">
	<h2><b>本站信息</b></h2>
	<div class="wrap">
		<div class="classify_list">
			<ul>
				<li><a href="<%=request.getContextPath()%>/noticeList">本站公告</a></li>
				<li><a href="<%=request.getContextPath()%>/activityList">本站活动</a></li>
			</ul>
		</div>
	</div>
	<h2><b>关于本站</b></h2>
	<div class="wrap">
		<div class="classify_list">
			<ul>
				<#list channelList as channel>
				<li><a  target="_blank" href="<%=request.getContextPath()%>/${channel.id}/articleShow">${channel.name}</a></li>
				</#list>
			</ul>
		</div>
	</div>
</div>