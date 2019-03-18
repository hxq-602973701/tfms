<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="main t15">
<div class="index_shop">
    <h3><a href="javascript:void(0);">分享美味 >></a>推荐店铺</h3>
    <ul class="main t10 brand">
	<#assign x = 1>
	<#list shopList as shop>
	<#if x==1>
	<li class="no">
		<a href="<%=request.getContextPath()%>/${shop.id}/show" target="_blank">
			<img src="<%=request.getContextPath()%>/resources/upload/shop/${shop.url}" />
		</a>
	</li>
	<#else>
	<li >
		<a href="<%=request.getContextPath()%>/${shop.id}/show" target="_blank">
			<img src="<%=request.getContextPath()%>/resources/upload/shop/${shop.url}" />
		</a>
	</li>
	</#if>
	<#assign x = x+1>
	</#list>
	<div class="cl"></div>
</ul>
</div>
</div>