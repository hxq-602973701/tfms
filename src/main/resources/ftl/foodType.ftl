<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<li class="hover" id="list0">全部</li>
    <#list foodTypeList as footType>
    <li id="list1">${footType.name} </li>
    </#list>
    <div class="clear"></div>
</ul>