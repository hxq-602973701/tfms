<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jqprint/jquery.jqprint-0.3.js"></script>
<title>天福美食用户列表</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">新单列表</a></li>
    </ul>
</div>
    
<div class="rightinfo">
    
	<!-- 列表开始 -->
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>订单号</th>
		        <th>商铺名称</th>
		        <th>下单人姓名</th>
		        <th>下单人手机</th>
		        <th>总价格</th>
		        <th>状态</th>
		        <th>下单时间</th>
		        <th width="150px">管理操作</th>
	        </tr>
        </thead>
        <tbody id="orderList">
        	
        </tbody>
   </table>
   <!-- 列表结束 -->
	
<input id="relPath" type="hidden" value="<%=request.getContextPath()%>"/>
</div>
<script type="text/javascript">
	
	$('.tablelist tbody tr:odd').addClass('odd');
	
	$(document).ready(function(){
		genOrderList();
	});  
	
	var int=self.setInterval(function(){
		genOrderList();
	},300000);
	
	function genOrderList(){
		$.ajax({
			url:$("#relPath").val()+"/order/newAddOrderList",
			type:"GET",
			success:function(result){
				var node = "";
				var obj = eval("("+result+")");
				//alert(obj.length);
				for(var i = 0;i<obj.length;i++){
					var order = obj[i];
					node = node+
					"<tr>"+
					"<td>"+order.ordernum+"</td>"+
					"<td>"+order.shopname+"</td>"+
			        "<td>"+order.name+"</td>"+
			        "<td>"+order.phone+"</td>"+
			        "<td>"+order.total+"</td>"+
			        "<td><span class='label btn-danger'>急需处理</span></td>"+
			        "<td>"+order.date+"</td>"+
			        "<td>"+
			        	"<a href='"+order.id+"/acceptOrder' class='tablelink'> 接受</a>"+
			        	"<a href='javascript:preview1("+order.id+");' class='tablelink'> 查看</a>"+
			        "</td>"+
		        	"</tr>";
				}
				$("#orderList").html(node);	
			}
		});
	}
	
	function preview1(orderId){
		$.ajax({
			url:$('#relPath').val()+"/order/orderDetail",
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
				   	"<tr>"+
				   		"<td>下单时间："+obj.bookdate+"</td><td></td>"+
				   	"</tr>"+
					"<tr>"+
			   		"<td>店铺名称："+obj.sname+"</td><td></td>"+
			   		"</tr>"+
					"<tr>"+
						"<td colspan='2'>"+
					"<table>"+
			  			"<tr>"+
			  				"<td style='width:120px'>商品名称</td><td style='width:40px'>数量</td><td style='width:40px'>单价</td><td style='width:40px'>总价</td>"+
					   	"</tr>";
					   	for(var i = 0;i < obj.foodItems.length;i++){
					   	var o = obj.foodItems[i];
					   	node = node+
					   	"<tr>"+
					   		"<td>"+o.foodname+"</td><td>"+o.foodnum+"</td><td>￥"+o.price+"</td><td>￥"+o.tprice+"</td>"+
					   	"</tr>";
					   	}
					node = node+
				   	"</table>"+
				   	"</td>"+
				   	"</tr>"+
				   	"<tr>"+
				    "<td><b>备注："+obj.remark+"</b></td><td></td>"+
				    "</tr>"+
				   	"<tr>"+
				    "<td><b>地址："+obj.address+"</b></td><td></td>"+
				    "</tr>"+
				   	"<tr>"+
				    "<td><b>总价：￥"+obj.total+"</b></td><td></td>"+
				    "</tr>"+
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
					area: ['380px', '350px'],
					title: "<span style='font-family:'微软雅黑';'>订单预览</span>",
					shade: false, //遮罩透明度
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
	
</script>
</body>
