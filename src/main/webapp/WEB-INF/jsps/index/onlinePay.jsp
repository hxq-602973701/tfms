<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—下订单</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/article.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/order.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery.validate1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery.tfms.validate.js"></script>
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
		<div class="life_body">
			<div id="main-wrap">
			    <div class="sitenav">
			    	<h2>当前位置：</h2><a href="javascript:void(0);">首页</a>&nbsp;&gt;&nbsp;下订单
			    </div>
		    	<div class="clearfix mt10">
			      	<div class="group-buy-form">
				        <ol class="buy-process-bar">
				          <li class="step-first">1.提交订单 <span class="highlight"></span> <span class="arrow"></span> </li>
				          <li  class="current"> 2.选择支付方式 <span class="highlight"></span> <span class="arrow"></span> </li>
				          <li class="step-last"> 3.购买成功 </li>
				        </ol>
		      	
		      			<form id="subOrder" action="forder" method='post'><!-- 提交给易宝处理 -->
		      			<input type="hidden" name="token" value="${token}"/>
		      			<input type="hidden" name="shopId" value="${shopId}">
				        <div class="table-section summary-table" >
				        	<table cellspacing="0">
					            <tbody>
					              <tr>
					                <td></td>
					               <td class="extra-fee total-fee" colspan="3"><strong>订单号</strong>： <span class="money"><strong id="payment">${order.ordernum}</strong> </span><input type="hidden" name="ordernum" value="${order.ordernum}" readonly="readonly"/></td>
					              </tr>
					              <tr>
					                <td></td>
					                <td class="extra-fee total-fee" colspan="3"><strong>应付总额</strong>： <span class="money"> ￥<strong id="payment">${order.total}</strong> </span><input type="hidden" name="total" value="${order.total}" readonly="readonly"/></td>
					              </tr>
					            </tbody>
				        	</table>
				        </div>
				        <div class="order_box">
				        	<table>
				        		<tr>
				            		<td><input type="radio" name="pd_FrpId" value="ABC-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/ABC-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="BCCB-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/BCCB-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="BOC-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/BOC-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="BOCO-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/BOCO-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="CCB-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/CCB-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="CIB-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/CIB-NET.gif"></td>
				              	</tr>
				              	
				              	<tr>
				            		<td><input type="radio" name="pd_FrpId" value="CMBCHINA-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/CMBCHINA-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="CMBC-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/CMBC-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="CZCB-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/CZCB-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="ECITIC-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/ECITIC-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="GDB-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/GDB-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="GNXS-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/GNXS-NET.gif"></td>
				              	</tr>
				              	
				              	<tr>
				            		<td><input type="radio" name="pd_FrpId" value="ICBC-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/ICBC-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="POST-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/POST-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="SDB-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/SDB-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="SDE-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/SDE-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="SHRCB-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/SHRCB-NET.gif"></td>
				                  	<td><input type="radio" name="pd_FrpId" value="SPDB-NET"><img src="<%=request.getContextPath()%>/resources/bankLogo/SPDB-NET.gif"></td>
				              	</tr>
				         		
				           	 	<tr>
				               	  	<td colspan="6" class="order_submit_td"><input type="submit" name="button" id="button" value="" class="order_submit" /></td>
				              	</tr>
				          	</table>
				        </div>
						</form>
		      		</div>
		    	</div>
			</div>
		</div>
	</div>
</div>


<!-- 底部文件 -->
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
		}
	};
	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
</script>
</body>
</html>