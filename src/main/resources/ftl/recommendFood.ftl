<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="index_food">
	<h3><a href="javascript:void(0);">分享美味 >></a>推荐菜品</h3>
	<ul>
		<#assign x = 1>
		<#list foodList as food>
		<#if x==1>
		<li class="no">
			<a href="<%=request.getContextPath()%>/${food.shop.id}/show?foodId=${food.id}" target="_blank">
				<img src="<%=request.getContextPath()%>/resources/upload/food/${food.url}" />
				<br><b>￥${food.newprice}</b><br>${food.name}</a>
			</a>
		</li>
		<#else>
		<li >
            <a href="<%=request.getContextPath()%>/${food.shop.id}/show?foodId=${food.id}" target="_blank">
                <img src="<%=request.getContextPath()%>/resources/upload/food/${food.url}" />
                <br><b>￥${food.newprice}</b><br>${food.name}</a>
            </a>
        </li>
		</#if>
		<#assign x = x+1>
		</#list>
		<div class="cl"></div>
	</ul>
	<div class="cl"></div>
</div>