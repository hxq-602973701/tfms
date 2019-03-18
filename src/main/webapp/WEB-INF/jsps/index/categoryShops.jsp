<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—商店</title>
<meta name="Keywords" content="外卖,订餐,小吃" />
<meta name="Description" content="" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/search.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/shop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/category.js" charset="utf-8"></script>
</head>
<body style="z-index:0">
<!-- 顶部文件 -->
<jsp:include page="/jsp/top.jsp"></jsp:include>
<!-- 搜索框 -->
<jsp:include page="/jsp/search.jsp"></jsp:include>
<!-- 导航栏 -->
<jsp:include page="/jsp/template/nav.jsp"></jsp:include>

<div id="bg" class="main">
	<div class="w950">
		<div class="clear"></div>
		<div id="main-wrap">
			<div class="sitenav">
			  <h2>当前位置：</h2>
			  <a href="/">首页</a>&nbsp;&gt;&nbsp;<a href='index.php?m=waimai'>商店列表</a>
			</div>
			
			<div class="main-local">
				<div class="list_nav"> 
					<i class="price-tit"></i>
					<span class="tit">商店：</span>
					<ul class="list" id="J_list">
					    <li class="current"><a href='javascript:void(0);'>全部</a></li>
					 </ul>
				</div>
			</div>
			
			<ul class="list_info">
				<c:forEach items="${shopList}" var="shop">
				<li class="company_info ">
					<a href='<%=request.getContextPath()%>/${shop.id}/show' class="company_logo">
						<img src="<%=request.getContextPath()%>/resources/upload/shop/${shop.url}" width="107" height="80" />
					</a>
					<dl>
						<dt><a href='javascript:void(0);'>${shop.name}</a></dt>
						<dd><span>${shop.address}</span></dd>
						<dd></dd>
						<c:if test="${shop.isrest==1}">
						<dd class="open1">
						</dd>
						</c:if>
						<c:if test="${shop.isrest==0}">
						<dd class="close1">
						</dd>
						</c:if>
					</dl>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>

<!-- 引入底部文件 -->
<jsp:include page="/jsp/bottom.jsp"></jsp:include>

<script type="text/javascript">
	$(function () {
        $(".header_menu").on("mouseenter", function () {
            $(this).addClass("hover");
        });
        $(".header_menu").on("mouseleave", function () {
            $(this).removeClass("hover");
        });
    });

	/*回顶部特效实现*/
    var getDiv = document.getElementById('returntop');
    var n = 0;
    function scrollEvent() {
        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        if (scrollTop) {
            getDiv.style.display = "block";
        } else {
            getDiv.style.display = "none";
        }
        n = 1;
    }
    window.onscroll = scrollEvent;
    if (n == 0) {
        document.body.onscroll = scrollEvent;
    }
    function getWinSize() {
        var winHeight = window.innerHeight, winWidth = window.innerWidth;
        if (document.documentElement.clientHeight) {
            winHeight = document.documentElement.clientHeight;
            winWidth = document.documentElement.clientWidth;
        } else {
            winHeight = document.body.clientHeight;
            winWidth = document.body.clientWidth;
        }
        var height = winHeight - 50;
        var width = winWidth - 100;
        getDiv.style.top = height + "px";
        getDiv.style.left = width + "px";
    }
    window.onresize = getWinSize;

    /**
     * 返回顶部
     */
    function Top() {
        $(document).scrollTop(0);
    }

	/*在线客服特效*/
    $("#showOnline").click(function () {
        $("#sideTools1").toggle("slow");
    });
    $("#service_bar_close").click(function () {
        $("#sideTools1").toggle("slow");
    });
	
	window._bd_share_config={
	"common":{
		"bdSnsKey":{},
		"bdText":"",
		"bdMini":"2",
		"bdMiniList":false,
		"bdPic":"",
		"bdStyle":"0",
		"bdSize":"16"},
		"slide":{
			"type":"slide",
			"bdImg":"0",
			"bdPos":"left",
			"bdTop":"150"
		},
		"image":{
			"viewList":["weixin","tsina","tqq","renren","qzone"],
			"viewText":"分享到：","viewSize":"16"},
			"selectShare":{
				"bdContainerClass":null,
				"bdSelectMiniList":["weixin","tsina","tqq","renren","qzone"]
			}
		};
	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
</script>
</body>
</html>