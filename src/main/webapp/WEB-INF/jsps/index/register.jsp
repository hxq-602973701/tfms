<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—注册页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/shop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/login.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery.showfoods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/category.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery.tfms.validate.js"></script>
</head>

<body style="z-index:0">

<div id="header">
	<div class="main">
        <div class="logo">
			<a href="#">
				<img src="${pageContext.request.contextPath}/resources/index/images/logo.jpg" />
			</a>
		</div>
    </div>
</div>
<!-- 菜品分类 -->

<div id="bg" class="main">
	<div class="w950">
		<div class="clear"></div>
		<div id="main-wrap">
			<div class="left_pic">
				<img src="${pageContext.request.contextPath}/resources/index/images/member.jpg">
			</div>
			<div class="register_page">
				<div class="page_hd">
				  <h2>注册<span>&nbsp;Register</span></h2>
				</div>
				<div class="register_bd">
				<form id="userReg" action="${pageContext.request.contextPath}/register" method="post">
					<dl>
						<dt></dt>
						<dd> 
							<span class="ipt03 in_b">
							<input type="text" placeholder="用户名" name="username" id="username">
							</span>
							<label for="member_phone" id="member_phone" generated="true" class="error error_reg"  style="display:none;"></label>
						</dd>
					</dl>
					<dl>
						<dt></dt>
						<dd>
							<span class="ipt04 in_b">
							<input type="password" title="" placeholder="密码" name="password" id="password">
							</span>
						</dd>
					</dl>
					<dl>
						<dt></dt>
						<dd>
							<span class="ipt05 in_b">
								<input type="password" title="" placeholder="确认密码" name="password2" id="password2">
							</span>
						</dd>
					</dl>
					<dl>
						<dt></dt>
						<dd> 
							<span class="ipt06 in_b">
								<input type="text" title="" placeholder="邮箱" name="email" id="email">
							</span>
						</dd>
					</dl>
					<dl>
						<dt></dt>
						<dd> 
							<span class="ipt07 in_b" style="padding-left:10px;">
								<input type="text" size="10" maxlength="6" placeholder="请输入验证码" style="width:170px;" name="kaptcha" id="kaptcha">
								<img style="margin-left:12px;" src="jsp/kaptcha.jsp" width="140px" height="45px" border="0" onClick="this.src='jsp/kaptcha.jsp?tmp='+new Date().getTime()" id="vdimgck" align="absmiddle" style="cursor:pointer"/>
							</span>
							
						</dd>
					</dl>
					<dl>
						<dt></dt>
						<dd class="ment"></dd>
						<dd>
							<input type="submit" title="立即注册" class="btn-regist" value="立即注册" name="Submit">
						</dd>
					</dl>
				</form>
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