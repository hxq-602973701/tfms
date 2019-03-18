<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—搜索结果</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/shop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/category.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
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
			
			<div class="show_product_list" id="con_content_1">
				<ul>
		        	<li class="hover" id="list0">食品</li>
		            <li>全部</li>
		            <div class="clear"></div>
	       		</ul>
		        <div class="clear"></div>
		        <!-- 商品显示 -->
		        <dl id="con_list_1" class="product_list">
  					<c:forEach items="${shopFoods}" var="shopFood">
		        	<dt><c:out value="${shopFood.key}"/></dt>
		        	<c:forEach items="${shopFood.value}" var="food">
            		<dd>
            			<c:if test="${food.shop.isrest eq 0}">
						<a href="javascript:;" class="add" id="17">
							<!-- <img src="${pageContext.request.contextPath}/resources/index/images/add.png" width="20" />-->
							<input type="button" style="width:60px;height:25px;background:#8E8989;color:#fff;border:0;border-radius:4px;cursor:pointer;" value="购买" readonly="readonly"/>
						</a>
						</c:if>
						<c:if test="${food.shop.isrest eq 1}">
						<a href="javascript:;" class="add" id="addFood1" shopId="${food.shop.id}" foodId="${food.id}">
							<!-- <img src="${pageContext.request.contextPath}/resources/index/images/gm.png" width="60" /> -->
							<input type="button" style="width:60px;height:25px;background:#FE4D3D;color:#fff;border:0;border-radius:4px;cursor:pointer;" value="购买" readonly="readonly"/>
						</a>
						</c:if>
						<span>￥${food.newprice}</span>
						<a href="javascript:;" class="photo">
							<img src="${pageContext.request.contextPath}/resources/upload/food/${food.url}" width="25" height="25" />
						</a>
						<div>${food.name}<i>[${food.desription}]</i>
						</div>
					</dd>
					</c:forEach>
					</c:forEach>
		        </dl>		        
		    </div>
		    
		    <div class="show_product_list_right">
		    	<!-- 本站公告 -->
		        <jsp:include page="/jsp/template/notice.jsp"></jsp:include>
		        
		    </div>
		</div>
	</div>
</div>

<!-- 底部文件 -->
<jsp:include page="/jsp/bottom.jsp"></jsp:include>
<script type="text/javascript">
	
	$(function () {
		//加入购物车
		$("#addFood1").live('click', function(){
			//食物id
			var fid=$(this).attr("foodId");
			//商店Id
			var sid=$(this).attr("shopId");
			$.ajax({
				url:$('#relPath').val()+"/addFood2Car",
				type:"GET",
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:{foodId:fid,shopId:sid},
				cache:false,
				success:function(data){
					window.location.href=$('#relPath').val()+"/"+sid+"/show";
				}
			});
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