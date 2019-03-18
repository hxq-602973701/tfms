<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—商店详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/shop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<link type="text/css" href="${pageContext.request.contextPath}/resources/umeditor1.2.2/themes/default/css/umeditor.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/category.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/umeditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/umeditor.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery.showfoods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery.cart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
	window.onload=function(){
		window.location.hash="#"+${foodId};
	}
</script>
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
		<div id="main-wrap" class="show_top">
			<div class="show_top_info">
		    	<div class="show_top_info_logo"><img src="${pageContext.request.contextPath}/resources/upload/shop/${shopDto.url}" width="93" height="70" /></div>
		        <div class="show_top_info_title">
		        	<h1>${shopDto.name}</h1>
		            <ul>
		            	<li><div>店铺地址：</div><span>${shopDto.address}</span></li>
		            	<li><div>老板联系方式：</div><span>${shopDto.bosstel}</span></li>
		            </ul>
		        </div>
		    </div>
			
		    <div class="show_top_category">
		        <a id="content0" onClick="setTab('content',0,4)" style="display:none;"></a>
		        <a class="hover" href="javascript:;" id="content1" onClick="setTab('content',1,4)">菜单</a>
		        <a href="javascript:;" id="content2" onClick="setTab('content',2,4)">餐厅介绍</a>
		        <a href="javascript:;" id="content3" onClick="setTab('content',3,4)">评价</a>
		    </div>
		    <div class="clear"></div>
		</div>
		
		<div id="main-wrap">
			<div class="show_product_list" id="con_content_1">
				<!-- 商品种类 -->
				<jsp:include page="/jsp/template/foodType.jsp"></jsp:include>
		        <div class="clear"></div>
		        <!-- 商品显示 -->
		        <dl id="con_list_1" class="product_list">
		        </dl>		        
		    </div>
		    
		    <!-- 商铺介绍 -->
		    <div class="show_product_about" id="con_content_2">
		    ${shopDto.description}
		    </div>
		    <div class="show_product_comment" id="con_content_3">
				<div id="com" class="comments_c">
					<!-- 品论循环开始 -->
					<!-- 品论循环结束 -->
				</div>
				
				<!-- 评论输入框 -->
				<div style="width:100%;height:400px;">
					<div style="margin:0 auto;width:500px;height:250px;margin-top:40px;position:relative;">
						<textarea id="myEditor" style="width:500px;height:150px;"></textarea>
						<span style="position:absolute;bottom:20px;left:85px;">
							<input id="subcomment" type="submit" value="发表评论" style="width:150px;height:30px;font-size:14px;background:#F76118;border:1px solid #FE7C00;color:#fff;font-weight:bold;cursor:pointer;"/>&nbsp;&nbsp;
							<input type="reset" value="取消发表" style="width:150px;height:30px;font-size:14px;background:#F76118;border:1px solid #FE7C00;color:#fff;font-weight:bold;cursor:pointer;"/>
						</span>
					</div>
				</div>
		    </div>
		    
		    <div class="show_product_list_right">
		    
		    	<!-- 公告 -->
		        <jsp:include page="/jsp/template/notice.jsp"></jsp:include>
		        
		        <!-- 购物车开始 -->
		        <div class="cart">
		            <div class="cart_t">天福美食<br /><span>购物车</span></div>    
		            <div id="cart">
		            	<ul id="cart_food">
		            		<!-- 购物车循环开始 -->
		            		
		            	</ul>
		            	<div class="cart_b"></div>
			            	<div class="cart_submit"><div>
			            	<span id="total"></span>配送费：￥0.00</div>
			            	<div class="button">
			            		<a id="orderAorder" href="javascript:void(0);">立即下单</a>
			            		<input id="userBase" type="hidden" value="${sessionScope.user.id}" readonly="readonly"/>
			            	</div>
		            	</div>
		            	<div class="cart_footer"></div>
		            </div>
		        </div>
		      	<!-- 购物车结束 -->
		    </div>
		</div>
	</div>
</div>

<!-- 快速登录 -->
<div style='padding:25px 45px 45px 45px;display:none;' id="quickLogin">
	<b><font size='2'>登录名称</font></b>：<br/>
	<input id="username" type='text' name='username' placeholder='输入用户名' style='width:250px;height:20px;'/>
	<br/><br/>
	<b><font size='2'>登录密码</font></b>：<br/>
	<input id="password" type='password' name='username' placeholder='输入用户名' style='width:250px;height:20px;'/>
	<br/><br/>
	<b><font size='2'>验证码</font></b>：<br/>
	<input id="kaptcha" type='text' name='kaptcha' placeholder='输入验证码' style='width:100px;height:20px;'/>
	<img style="margin-left:4px;cursor:pointer" src="<%=request.getContextPath() %>/jsp/kaptcha.jsp" width="80px" height="20px" border="0" onClick="this.src='<%=request.getContextPath() %>/jsp/kaptcha.jsp?tmp='+new Date().getTime()" id="vdimgck"/>
	<br/><br/>
	<input id="subbtn" type='submit' value='快 速 登 录' style='width:251px;height:30px;background:#FE7C00;border:1px solid #FE7C00;color:#fff;font-weight:bold;'/><br/>
</div>

<!-- 底部文件 -->
<jsp:include page="/jsp/bottom.jsp"></jsp:include>
<script type="text/javascript">
/*	var um = UM.getEditor('myEditor',{
		toolbar:[
	          'undo redo | bold ',
	          '| fontfamily fontsize | emotion | insertorderedlist insertunorderedlist |paragraph' ,
	          '| justifyleft justifycenter justifyright justifyjustify ',
	          '| fullscreen'
	      ]
	});*/
	
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
			if(cursel==3){
				genComments(1);
			}
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
	
	function genComments(currentPage){
		//列表
		var relPath = $("#relPath").val();
		var shopId = $("#shop").val();
		$.ajax({
			url:relPath+"/showComments",
			data:{shopId:shopId,currentPage:currentPage},//"shopId="+shopId,
			type:"GET",
			success:function(datas){
				var result = eval("("+datas+")");
				var node = "";
				for(var i = 0;i < result.records.length;i++){
					var o = result.records[i];
					node = node+
					"<div class='comments_c_t'>"+
					"<h4><img id='photo' src='/tfms/resources/index/images/photo.jpg'/><br>"+o.user.phone+"</h4>"+
					"<div class='comments_c_r'>"+
						"<ul>"+
							"<div class='clear'></div>"+
						"</ul>"+
						"<div class='clear'></div>"+
						"<div class='comments_c_c'>"+
							"<p>"+o.content+"</p>"+
							"<br><strong>"+o.replyTime+"</strong>"+
						"</div>"+
					"</div>"+
					"<div class='clear'></div>"+
					"</div>";
				}
				node = node + "<div class='pages'><div class='pages'><a>总计:"+result.recordCount+"条 每页"+result.pageSize+"条 当前第:"+result.currentPage+"页</a>"+
				"&nbsp;&nbsp;<a href='javascript:genComments(1);'>首页</a>";
				if(result.currentPage==1){
					node = node + "&nbsp;&nbsp;<a href='javascript:void(0)'>上一页</a>";
				}else{
					node = node + "&nbsp;&nbsp;<a href='javascript:genComments("+(result.currentPage-1)+");'>上一页</a>";
				}
				if(result.currentPage==result.pageCount){
					node = node +	"&nbsp;&nbsp;<a href='javascript:void(0);'>下一页</a>";
				}else{
					node = node + "&nbsp;&nbsp;<a href='javascript:genComments("+(result.currentPage+1)+");'>下一页</a>";
				}
				node = node + "&nbsp;&nbsp;<a href='javascript:genComments("+(result.pageCount)+");'>尾页</a>";
				$("#com").html(node);
			}
		});
	}
	
	$(function () {
        $(".header_menu").on("mouseenter", function () {
            $(this).addClass("hover");
        });
        $(".header_menu").on("mouseleave", function () {
            $(this).removeClass("hover");
        });
        
        //评论
       $("#subcomment").click(function(){
        	var url = "<%=request.getContextPath()%>/addComments";
        	var userId = $("#userBase").val();
        	var shopId = $("#shop").val();
        	var content = $("#myEditor").val();
 //alert(content);
        	if(userId!=null && userId!=""){
        		if(content==null || content==""){
            		layer.msg("内容不能为空！",{
        				time: 2000,
        				tips: 2
        			});
            	}else{
	        		//对评论内容进行提交
	        		$.post(url,{shopId:shopId,content:content},function(data){
	        			var msg = eval("("+data+")");
//alert(msg.result);
	            		if(msg.result==1){
	            			alert("haohao");
	            			genComments(1);
	            			$("#myEditor").val('');
	            		}
	            	});
            	}
        	}else{
        		window.location.href ='<%=request.getContextPath()%>/login';
        	}
        });
        
        //下单JS
       	$("#orderAorder").click(function(){
       		var user = $("#userBase").val();
       		var shopId = $("#shop").val();
       		var relPath = $("#relPath").val();
//alert(shopId);
       		if(user!=null && user!=""){
       			window.location.href =relPath+"/forder?shopId="+shopId;
       		}else{
       			//在这里面输入任何合法的js语句
       			layer.open({
       				type: 1, //page层
       				area: ['337px', '300px'],
       				title: "<span style='font-family:'微软雅黑';'><b>快速登录</b></span>",
       				shade: 0.1, //遮罩透明度
       				shift: 1, //0-6的动画形式，-1不开启
       				content:$('#quickLogin')
       			});
       		}
       	});
        
        //快速登录
        $("#subbtn").click(function(){
        	var un = $("#username").val();
        	var pwd = $("#password").val();
        	var ka = $("#kaptcha").val();
        	var shopId = $("#shop").val();
        	var relPath = $("#relPath").val();
        	var url = relPath+"/quickLogin";
        	$.post(url,{username:un,password:pwd,kaptcha:ka},function(data){
        		if(data.result==1){//登陆成功
        			//调到下订单页面 
        			window.location.href= relPath+"/forder?shopId="+shopId;
        		}else{
        			var message = data.msg;
        			layer.msg(message,{
        				time: 500,
        				tips: 2
        			});
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