<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="t10 index_company">
	<h3><a href="javascript:void(0);">更多商店 >></a>今日推荐</h3>
	<ul>
		<#list shopList as shop>
		<li>
			<a href="<%=request.getContextPath()%>/${shop.id}/show" target="_blank">
				<img src="<%=request.getContextPath()%>/resources/upload/shop/${shop.url}" />
			</a>
			<#--open：营业，close：休息-->
			<#if shop.isrest=="1">
			<h4 class="open"></h4>
			<#elseif shop.isrest=="0">
			<h4 class="close"></h4>	
			</#if>
		</li>
		</#list>
		
		<div class="cl"></div>
	</ul>
</div>