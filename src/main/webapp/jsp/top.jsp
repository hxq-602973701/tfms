<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--客服+二维码+返回顶部开始-->
<div id="sideTools">
    <span id="wx_big" class="tny-wximg" style="display: none;"></span>
    <p class="iOnline">
        <a id="showOnline" href="javascript:void(0)"></a>
    </p>
    <p class="weixin2">
        <a id="wx_s" style="height:93px;" href="#"></a>
    </p>
    <p class="iToTop" id="returntop" style="display: none; top: 389px; left: 1249px;">
        <a name="dac_index_ycdhsh06" href="javascript:Top()"></a>
    </p>
</div>
<div id="sideTools1" style="display: none;">
    <div class="service_bar_head">
        <span id="service_bar_close" title="点击关闭">点击关闭</span>
    </div>
    <div class="service_bar_main">
        <ul class="service_menu">
            <li class="hover">
                <dl>
                    <dd style="display: block;">
                        <a href="http://wpa.qq.com/msgrd?v=3&uin=1131473289&site=qq&menu=yes" target="_blank">
                            <img width="74" height="22" border="0" title="天福美食在线客服" src="${pageContext.request.contextPath}/resources/index/images/qq1.gif">
                        </a>
                        <a href="http://wpa.qq.com/msgrd?v=3&uin=1131473289&site=qq&menu=yes" target="_blank">
                            <img width="74" height="22" border="0" title="天福美食在线客服" src="${pageContext.request.contextPath}/resources/index/images/qq1.gif">
                        </a>
                        <a href="http://wpa.qq.com/msgrd?v=3&uin=1131473289&site=qq&menu=yes" target="_blank">
                            <img width="74" height="22" border="0" title="天福美食在线客服" src="${pageContext.request.contextPath}/resources/index/images/qq1.gif">
                        </a>
                    </dd>
                </dl>
            </li>
        </ul>
    </div>
</div>
<!--客服+二维码+返回顶部结束-->

<div id="top">
	<div class="main">
		<!-- 
		<span>
			<a href="#">商家加盟</a> | 
			<a href="#">联系我们</a> |
			<a href="#">加入收藏</a> 
		</span> -->
		
		<c:if test="${!empty sessionScope.user}">
		<a href="javascript:void(0);">欢迎您！${sessionScope.user.username}</a>&nbsp;
		<a href="${pageContext.request.contextPath}/userInfo">会员中心</a>&nbsp;
		<a href="${pageContext.request.contextPath}/logout">退出</a>
		</c:if>
		<c:if test="${empty sessionScope.user}">
		<a href="${pageContext.request.contextPath}/login">登录</a>&nbsp;
		<a href="${pageContext.request.contextPath}/register">免费注册</a>
		</c:if>
	</div>
</div>
