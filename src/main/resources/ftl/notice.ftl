<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="company_box">
    <h3>本站公告<span class="more"><a href="<%=request.getContextPath()%>/noticeList">更多>></a></span></h3>
    <ul>
    	<#assign x = 1>
    	<#list noticeList as notice>
        <li><i class="shu${x} a4"></i><a href="<%=request.getContextPath()%>/${notice.id}/noticeShow">${notice.title}</a></li>
        <#assign x = x + 1>
        </#list>
    </ul>
    <div class="announcement"></div>
    <h5>&nbsp;</h5>
</div>