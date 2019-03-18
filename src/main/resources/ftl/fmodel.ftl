<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<p>
   <#assign x = 1>
   <#list channelList as channel>
   <#if x==1>
   <a  target="_blank" href="<%=request.getContextPath()%>/${channel.id}/articleShow">${channel.name}</a>
   <#else>
   | <a  target="_blank" href="<%=request.getContextPath()%>/${channel.id}/articleShow">${channel.name}</a>
   </#if>
   <#assign x = x + 1>
   </#list>
</p>