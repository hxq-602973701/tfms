<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="index_right index_news box">
	<h3><a href='javascript:void(0);'>本站公告</a><span class="more"><a href="<%=request.getContextPath()%>/noticeList">更多>></a></span></h3>
	<ul>
		<#assign x = 1>
    	<#list noticeList as notice>
		<li><div class="shu${x}"></div> <a href="<%=request.getContextPath()%>/${notice.id}/noticeShow" target="_blank">${notice.title}</a></li>
		<#assign x = x + 1>
        </#list>
	</ul>
    <h5>&nbsp;</h5>
</div>