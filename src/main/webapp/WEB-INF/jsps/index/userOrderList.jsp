<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—用户信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/shop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/center.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/category.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<style>
	table{margin:0px;padding:0px;}
	#order table tr td{font-family:"微软雅黑";font-size:12px;}
	#food{margin-top:10px;}
</style>

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
		<div id="main-wrap">
			<div class="mt20">
			<div class="c-left">
				<div class="c-leftt"></div>
				<div class="c-leftmu">
					<a href="javascript:void(0);" class="fabu">会员首页</a>
				</div>  
				<div class="mu2">
					<ul>
						<li><a href="<%=request.getContextPath()%>/userInfo">资料管理</a></li>
						<li><a href="<%=request.getContextPath()%>/updatePassword" >修改密码</a></li>
					</ul>
				</div>
				<div class="dhmu2">
					<a href="<%=request.getContextPath()%>/orderList">订单</a>
					<a href="<%=request.getContextPath()%>/logout" title="退出登录">退出登录</a>
				</div>
				<div class="left-d"></div>
			</div>  
			<div class="cmsMain">
				<div class="location"><span class="fLeft">我的订单</span></div>
				<form name="form2" class="mL10 mR10 mTB10">
					<table cellspacing="0" class="list">
						<thead>
							<tr>
								<th>订单号</th>
								<th>下单时间</th>
								<th>状态</th>
								<th>总金额</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${datas.records}" var="order">
							<tr>
								<td align="center">${order.ordernum}</td>
								<td align="center">${order.date}</td>
								<td align="center">
				                	<c:if test="${order.state eq 0}">
				                	<span id="deal-buy-price">正在处理</span>
				                	</c:if>
				                	<c:if test="${order.state eq 1}">
				                	<span id="deal-buy-price">正在派送</span>
				                	</c:if>
				                	<c:if test="${order.state eq 3}">
				                	<span id="deal-buy-price">接单成功</span>
				                	</c:if>
				                </td>
								<td align="center">${order.total}</td>
								<td align="center"><a href="javascript:cancelOrder('${order.id}');">取消</a>&nbsp; <a href="javascript:finishOrder('${order.id}');">收货</a>&nbsp; <a href="javascript:preview1('${order.id}');">查看</a></td>
							</tr>
							</c:forEach>
						</tbody>
						<tfoot>
						  <tr>
							<td colspan="5" align="center">
							<div class="pages"><a>总计:${datas.recordCount}条 每页${datas.pageSize}条 当前第${datas.currentPage}页</a>   
								<a href="<%=request.getContextPath()%>/orderList?currentPage=1">首页</a> 
							    <c:if test="${datas.currentPage==1}">
							    <a href="javascript:void(0);">上一页</a> 
							    </c:if>
							    <c:if test="${datas.currentPage!=1}">
							    <a href="<%=request.getContextPath()%>/orderList?currentPage=${datas.currentPage-1}">上一页</a> 
							    </c:if>
							    <c:if test="${datas.currentPage==datas.pageCount}">
							    <a href="javascript:void(0);">下一页</a> 
							    </c:if>
							    <c:if test="${datas.currentPage!=datas.pageCount}">
							    <a href="<%=request.getContextPath()%>/orderList?currentPage=${datas.currentPage+1}">下一页</a> 
							    </c:if>
							    <a href="<%=request.getContextPath()%>/orderList?currentPage=${datas.pageCount}">尾页</a> 
						    </div>
							
							</td>
						  </tr>
						</tfoot>
					</table>
				</form>
			</div>
			<div class="clear"></div>
		  </div>
		</div>
	</div>
</div>
<div class="clear"></div>

<!-- 底部文件 -->
<jsp:include page="/jsp/bottom.jsp"></jsp:include>

<input type="hidden" id="relPath" value="<%=request.getContextPath()%>"/>
<input type="hidden" id="userObj" value="${sessionScope.user}"/>
<script type="text/javascript">
	/*查看订单*/
	function preview1(orderId){
		var obj = $("#userObj").val();
		if(obj==null){
			layer.msg('非法操作',{
				time: 2000,
				tips: 2
			});
		}else{
			$.ajax({
				url:$('#relPath').val()+"/orderInfo",
				data:"orderId="+orderId,
				type:"GET",
				success:function(result){
					var obj = eval("("+result+")");
					var node = "";
					node = node+"<div id='order' style='padding:20px 20px 0 10px;font-size:16px;font-family:'微软雅黑';'>"+
					"<table>"+
						"<tr>"+
							"<td>订单编号："+obj.ordernum+"</td><td></td>"+
						"<tr>"+
						"<tr>"+
							"<td>联系人姓名："+obj.name+"</td><td></td>"+
					   	"</tr>"+
					   	"<tr>"+
					   		"<td>联系人手机："+obj.phone+"</td><td></td>"+
					   	"</tr>"+
					   	"<tr style='border-bottom:1px solid #F0F0F0;'>"+
					   		"<td>下单时间："+obj.bookdate+"</td><td></td>"+
					   	"</tr>"+
						"<tr>"+
							"<td colspan='2'>"+
						"<table id='food'>"+
				  			"<tr id='title'>"+
				  				"<td style='width:150px'>商品名称</td><td style='width:120px'>店铺</td><td style='width:40px'>数量</td><td style='width:80px'>单价</td><td style='width:80px'>总价</td>"+
						   	"</tr>";
						   	for(var i = 0;i < obj.foodItems.length;i++){
						   	var o = obj.foodItems[i];
						   	node = node+
						   	"<tr>"+
						   		"<td>"+o.foodname+"</td><td>"+o.shopname+"</td><td>"+o.foodnum+"</td><td>￥"+o.price+"</td><td>￥"+o.tprice+"</td>"+
						   	"</tr>";
						   	}
						node = node+
					   	"</table>"+
					   	"</td>"+
					   	"<tr>"+
					   	"<tr>"+
					    "<td><b>总价：￥"+obj.total+"</b></td><td></td>"+
					    "<tr>"+
					    "<tr>"+
					    "<td><b>支付方式：";
					    if(obj.payType==0){
					    	node = node+"货到付款";
					    }else if(obj.payType==1){
					    	node = node+"在线支付";
					    }
					    node = node+"</b></td><td></td>"+
					    "</tr>"+
				    "</table></div>";
			   	
					layer.open({
						type: 1, //page层
						area: ['480px', '340px'],
						title: "<span style='font-family:'微软雅黑';'>订单预览</span>",
						shade: 0.1, //遮罩透明度
						shift: 1, //0-6的动画形式，-1不开启
						content:node,
						btn: ['确定','取消'],
						yes:function(index,layero){
							layer.close(index);
						}
					});
				}
			});
		}
	}

	/*取消订单*/
	function cancelOrder(orderId){
		var obj = $("#userObj").val();
		if(obj==null){
			layer.msg('非法操作',{
				time: 2000,
				tips: 2
			});
		}else{
			$.ajax({
				url:$('#relPath').val()+"/cancelOrder",
				data:"orderId="+orderId,
				type:"GET",
				success:function(result){
					var res = result.result;
					if(res==0){
						layer.msg('订单已经被处理，正在配送，请联系客服！',{
		    				time: 2000,
		    				tips: 2
		    			});
					}else if(res==1){
						window.location.reload();
					}else if(res==2){
						layer.msg('系统报错!',{
		    				time: 2000,
		    				tips: 2
		    			});
					}else if(res==3){
						layer.msg('订单已经确认收货了，不能进行取消操作!',{
		    				time: 2000,
		    				tips: 2
		    			});
					}
				}
			});
		}
	}
	
	/*完成订单*/
	function finishOrder(orderId){
		var obj = $("#userObj").val();
		if(obj==null){
			layer.msg('非法操作',{
				time: 2000,
				tips: 2
			});
		}else{
			$.ajax({
				url:$('#relPath').val()+"/finishOrder",
				data:"orderId="+orderId,
				type:"GET",
				success:function(result){
					var res = result.result;
					if(res==0){
						layer.msg('你的操作非法！',{
		    				time: 2000,
		    				tips: 2
		    			});
					}else if(res==1){
						layer.msg('确认收单操作成功!',{
		    				time: 2000,
		    				tips: 2
		    			});
					}else if(res==2){
						layer.msg('系统报错!',{
		    				time: 2000,
		    				tips: 2
		    			});
					}
				}
			});
		}
	}

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