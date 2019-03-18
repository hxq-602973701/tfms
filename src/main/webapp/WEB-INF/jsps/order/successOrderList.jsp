<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/datebox/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/datebox/css/icon.css">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/datebox/js/jquery.easyui.min.js"></script>
<title>天福美食用户列表</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">完成列表</a></li>
    </ul>
</div>
    
<div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
        	<li>
	        	<form name="fom" id="fom" action="successOrderList">
	        	<span><img src="${pageContext.request.contextPath}/resources/images/ico06.png" height="30px"/></span><input name="search" style="height:31px;" type="text" value="${search}" class="dfinput" placeholder="请输入店铺名称..."/>
	        	&nbsp;&nbsp;<input id="save" style="height:30px;width:40px;background-color:#F4F7F8;border-radius:4px;" name="" type="submit" value="搜索"/>
	        	</form>
	        </li>
        </ul>&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="shopname" style="height:30px;width:140px;border:1px solid #95B8E7;border-radius:4px;" name="shopname" placeholder="请输入店铺名称..."/>&nbsp;
        <input id="sdate1" style="height:30px;width:120px;" name="sdate" class="easyui-datebox"/>~
	    <input id="edate1" style="height:30px;width:120px;" name="edate" class="easyui-datebox"/>
	    
	   	<button id="jisuan" style="height:30px;width:60px;background-color:#076C8B;border-radius:4px;text-align:center;color:#fff;cursor:pointer;">统计</button>
	</div>
	<!-- 列表开始 -->
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>订单号</th>
		        <th>所属店铺</th>
		        <th>下单人姓名</th>
		        <th>下单人手机</th>
		        <th>状态</th>
		        <th>总价格</th>
		        <th>下单时间</th>
		        <th width="250px">管理操作</th>
	        </tr>
        </thead>
        <tbody>
        	 <c:forEach items="${datas.records}" var="order">
	        <tr>
		        <td>${order.ordernum}</td>
		         <td>${order.shop.name}</td>
		        <td>${order.name}</td>
		        <td>${order.phone}</td>
		        <td>
		   			<span class="label btn-danger">订单完成</span>
		        </td>
		        <td>${order.total}</td>
		        <td>${order.date}</td>
		        <td>
		        	<a href="javascript:preview1('${order.id}');" class="tablelink">查看</a>
		        </td>
	        </tr>
	        </c:forEach>
        </tbody>
   </table>
   <!-- 列表结束 -->
    <!-- 分页 开始-->
   <div class="pagin">
   	<div class="message">总计:<i class="blue">${datas.recordCount}</i>条&nbsp;&nbsp;每页<i class="blue">${datas.pageSize}</i>条&nbsp;&nbsp;当前第<i class="blue">${datas.currentPage}</i>页</div>
       <ul class="paginList" style="float：left;">
       	<li class="paginItem"><a href="<%=request.getContextPath()%>/order/successOrderList?currentPage=1">首页</a></li>
       	<c:if test="${datas.currentPage==1}">
       		<li class="paginItem"><a href="javascript:void(0);">上一页</a></li>
       	</c:if>
       	<c:if test="${datas.currentPage!=1}">
       		<li class="paginItem"><a href="<%=request.getContextPath()%>/order/successOrderList?currentPage=${datas.currentPage-1}">上一页</a></li>
       	</c:if>
       	
        <c:if test="${datas.currentPage==datas.pageCount}">
        	<li class="paginItem"><a href="javascript:void(0);">下一页</a></li>
        </c:if>
        <c:if test="${datas.currentPage!=datas.pageCount}">
        	<li class="paginItem"><a href="<%=request.getContextPath()%>/order/successOrderList?currentPage=${datas.currentPage+1}"">下一页</a></li>
        </c:if>
        <li class="paginItem"><a href="<%=request.getContextPath()%>/order/successOrderList?currentPage=${datas.pageCount}">尾页</a></li>
       </ul>
      	<div class="goPage">
      		<input id="pager" class="goPager" style="padding:6px;" type="text" placeholder="页数..."/>&nbsp;<input class="goPagerBtn" type="button" value="跳转" onclick="javascript:goPage();"/>
      	</div>
   </div>
   <!-- 分页结束 -->
	
<input id="relPath" type="hidden" value="<%=request.getContextPath()%>"/>
</div>
<script type="text/javascript">
	
	$('.tablelist tbody tr:odd').addClass('odd');
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
						"<td colspan='2'>"+
					"<table>"+
			  			"<tr>"+
			  				"<td style='width:120px'>商品名称</td><td style='width:80px'>店铺</td><td style='width:40px'>数量</td><td style='width:40px'>单价</td><td style='width:40px'>总价</td>"+
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
						//$("#order").jqprint();
						layer.close(index);
					}
				});
			}
		});
	}
	
	$("#jisuan").click(function(){
		var shopname = $("#shopname").val();
		var sdate = $("#sdate1").datetimebox('getValue');
		var edate = $("#edate1").datetimebox('getValue');
		if(shopname=="" || sdate=="" || edate==""){
			layer.msg("输入项不能为空",{
				time: 5000,
				tips: 2
			});
		}else if(sdate>edate){
			layer.msg("解释时间一定要大于开始时间",{
				time: 5000,
				tips: 2
			});
		}else{
			window.location.href=$('#relPath').val()+"/order/orderTotal?search="+shopname+"&sdate="+sdate+"&edate="+edate;
		}
	});

	function goPage()
	{
		var page = $("#pager").val();
		//正整数正则表达式不能写成 "^[0-9]*[1-9][0-9]*$",而是在正则表达式前后加上/
		var reg = /^[0-9]*[1-9][0-9]*$/;
        if(!reg.test(page)) 
        { 
        	alert("输入的页数不合法!"); 
        	$("#pager").val('');
        	return false;
        }
		window.location.href="${pageContext.request.contextPath}/order/successOrderList?currentPage="+page;
	}
</script>
</body>
