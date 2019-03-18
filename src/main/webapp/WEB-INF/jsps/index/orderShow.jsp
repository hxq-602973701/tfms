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
				          <li class="step-first current">1.提交订单 <span class="highlight"></span> <span class="arrow"></span> </li>
				          <li> 2.选择支付方式 <span class="highlight"></span> <span class="arrow"></span> </li>
				          <li class="step-last"> 3.购买成功 </li>
				        </ol>
		      	
		      			<form id="subOrder" action="forder" method='post'>
		      			<input type="hidden" name="token" value="${token}"/>
		      			<input type="hidden" name="shopId" value="${shopId}">
				        <div class="table-section summary-table" >
				        	<table cellspacing="0">
					          	<thead>
					          		<tr>
						                <th class="desc w250">菜名</th>
						                <th class="w170">数量</th>
						                <th class="w90">价格</th>
						                <th class="total w90">总价</th>
					          		</tr>
					          	</thead>
					            <tbody>
					              <c:forEach items="${sessionScope.orders}" var="foodItem">
					              <tr>
					                <td class="desc" >${foodItem.name}<input name="foodname" type="hidden" value="${foodItem.name}" /></td>
					                <td class="quantity">
					                  <input type="text" value="${foodItem.number}" name="numbers" maxlength="4" class="f-text J-quantity" id="numbers" disabled>
									</td>
					                <td class="money"> ￥<span id="deal-buy-price">${foodItem.price}</span></td>
					                <td class="money total"> ￥<span id="deal-buy-total">${foodItem.price*foodItem.number}</span></td>
					              </tr>
					              </c:forEach>
					              <tr>
					                <td></td>
					                <td class="extra-fee total-fee" colspan="3"><strong>应付总额</strong>： <span class="money"> ￥<strong id="payment">${total}</strong> </span><input type="hidden" name="total" value="${total}" readonly="readonly"/></td>
					              </tr>
					            </tbody>
				        	</table>
				        </div>
				        <div class="order_box">
				        	<table>
				        		<tr>
				            		<th>联 系 人：</th>
				                  	<td><input id="name" type="text" name="name" class="order_text" /></td>
				              	</tr>
				           	  	<tr>
				               	 	<th>手机号码：</th>
				                	<td><input id="phone" type="text" name="phone" class="order_text" /></td>
				              	</tr>
				                <tr>
				               	  	<th>送货地址：</th>
				                  	<td><input id="address" type="text" name="address" class="order_text" value="天府街66号" /></td>
				              	</tr>
				                <tr>
				               	  	<th>支付方式：</th>
				                  	<td>
				                  		<input name="payType" type="radio" class="radio" id="payment" value="0" checked="checked" />餐到付款 
				                  		<input name="payType" type="radio" class="radio" id="payment" value="1"  />在线支付
				                  		<span id="money"></span>
				                  	</td>
				              	</tr>
				           	  	<tr>
				               	  	<th>备注信息：</th>
				                  	<td><textarea name="remark" id="note" cols="50" rows="6" class="textarea"></textarea></td>
				              	</tr>
				           	 	<tr>
				               	  	<td colspan="2" class="order_submit_td"><input type="submit" name="button" id="button" value="" class="order_submit" /></td>
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
	//实现选项卡
	function setTab(name,cursel,n){
		if(cursel==0){
			var menuhome=document.getElementById(name+"0");
			menuhome.className="hover";
			for(i=1;i<=n;i++){
				var menu=document.getElementById(name+i);
				menu.className="";
				var con=document.getElementById("con_"+name+"_"+i);
				con.style.display="block";
			}
		}else{
			for(i=1;i<=n;i++){
				var menuhome=document.getElementById(name+"0");
				menuhome.className="";
				var menu=document.getElementById(name+i);
				var con=document.getElementById("con_"+name+"_"+i);
				menu.className=i==cursel?"hover":"";
				con.style.display=i==cursel?"block":"none";
			}
		}
	}
	
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