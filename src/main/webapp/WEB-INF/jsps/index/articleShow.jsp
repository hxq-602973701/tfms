<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—${article.title.name}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/shop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/article.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
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
		<div class="keyword">
			<h4>当前位置：</h4><a href="/">首页</a><span>&nbsp;</span> ${article.title.name}</div>
			<div class="content1">
			  <div class="left">
				<!-- 模块管理 -->
			  	<jsp:include page="/jsp/template/model.jsp"></jsp:include>
			  </div>
			  <div class="right">
			  	<div class="article_wrap">
				  <div class="article_con">
					<h1>${article.title.name}</h1>
					<div style="width:100%;height:20px;"></div>
					<h2>${acticle.createTime}</h2>
					<div class="default">
						${article.content}
					</div>
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
        
        //下单JS
       	$("#orderAorder").click(function(){
       		var user = $("#userBase").val();
       		if(user!=null && user!=""){
       			window.location.href ='<%=request.getContextPath()%>/order';
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
        	var url = "<%=request.getContextPath()%>/quickLogin";
        	$.post(url,{username:un,password:pwd,kaptcha:ka},function(data){
        		if(data.result==1){//登陆成功
        			//调到下订单页面
        			window.location.href="<%=request.getContextPath()%>/order";
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