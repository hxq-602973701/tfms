<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—商店详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/shop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/order.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
</head>

<body style="z-index:0">

<!-- 顶部文件 -->
<jsp:include page="/jsp/top.jsp"></jsp:include>
<!-- 搜索框 -->
<jsp:include page="/jsp/search.jsp"></jsp:include>
<!-- 导航栏 -->
<jsp:include page="/jsp/template/nav.jsp"></jsp:include>

<!-- 引入js文件 -->
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/star.js"></script>

<input type="hidden" id="relPath" value="<%=request.getContextPath()%>">
<input type="hidden" id="shop" value="${shopDto.id}">
<div id="bg" class="main">
	<div class="w950">
		<div class="clear"></div>
		<div id="main-wrap">
		    <div class="sitenav">
		      <h2>当前位置：</h2>
		      <a href="/">首页</a>&nbsp;&gt;&nbsp;订单成功
			</div>
			<ol class="buy-process-bar">
		          <li class="step-first">1.提交订单 <span class="highlight"></span> <span class="arrow"></span> </li>
		          <li> 2.选择支付方式 <span class="highlight"></span> <span class="arrow"></span> </li>
		          <li class="step-last current"> 3.下单成功 </li>
		        </ol>
			<div class="show_product_list" id="con_content_1">
				<div style="border-top:1px solid #E0E0E0;"></div>
		        <div class="clear"></div>
		        <div class="order" >
					<div class="orderSuccess"><b>下单成功！</b></div>
					<div class="con">
						<p>请保持您的手机${fn:substring(order.phone,0,6)}...畅通，方便送餐人员联系到您</p>
						<p class="time">预计接单时间<b class="orange">1</b>分钟</p>
						<p class="findorder"><a href="<%=request.getContextPath()%>/orderList">查看订单</a></p>
					</div>
		        </div>
		    </div>
			
			<!--购物车-->
		    <div class="show_product_list_right">
		        <!-- 公告 -->
		        <jsp:include page="/jsp/template/notice.jsp"></jsp:include>
		    </div>
		</div>
	</div>
</div>


<!-- 底部文件 -->
<jsp:include page="/jsp/bottom.jsp"></jsp:include>
<script type="text/javascript">

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
		}
	};
	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
</script>
</body>
</html>