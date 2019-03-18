<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
	<div class="main">
        <div class="logo">
			<a href="${pageContext.request.contextPath}/index">
				<img src="${pageContext.request.contextPath}/resources/index/images/logo.jpg"/>
			</a>
		</div>
		
		<!--搜索框开始-->
		<div id="search-box" style="width:490px;">
			<form id="search_form" target="_top" method="post" action="<%=request.getContextPath()%>/search">
				<div class="tab"> 
					<span class="tri"></span>
					<ul class="search_tabs">
						<li class="search_tab" op="search_dash">美食</li>
						<li class="search_tab" op="search_store">店铺</li>
					</ul>
				</div>
				<input type="hidden" name="type" value="1"/>
				<input type="text" placeholder="输入关键字查找美食" x-webkit-speech="" value="" class="search-box-from" style="width:310px;" autocomplete="off" name="keyword" tabindex="1">
				<input type="submit" data-mod="sr" value="搜&nbsp;&nbsp;索" hidefocus="true" class="search_button">
			</form>
        </div>
		<!--搜索框结束-->
    </div>
</div>
<!-- 菜品分类 -->