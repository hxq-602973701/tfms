<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天福美食—登陆页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/shop.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/login.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/index/css/sidetools.tfms.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/category.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/layer.min.js"></script>
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
		<div id="main-wrap">
			<div class="left_pic">
				<img src="${pageContext.request.contextPath}/resources/index/images/member.jpg">
			</div>
			<div class="register_page">
				<div class="page_hd">
				  <h2>登录<span>&nbsp;Login</span></h2>
				</div>
				<div class="register_bd">
					<form id="userValidate" action="${pageContext.request.contextPath}/login" method="post">
						<dl>
							<dt></dt>
							<dd> 
								<span class="ipt03 in_b">
									<input type="text" placeholder="输入用户名" name="username" id="username" value="${username}"/>
								</span>
							</dd>
						</dl>
						<dl>
							<dt></dt>
							<dd>
								<span class="ipt04 in_b">
									<input type="password" placeholder="输入密码" name="password" id="password" value="${password}"/>
								</span>
							</dd>
						</dl>
						<dl>
							<dd class="clearfix" style=" margin:10px 0;">
								<span class="ipt07 in_b" style="padding-left:10px;">
									<input type="text" size="10" maxlength="4" placeholder="请输入验证码" style="width:170px;" name="kaptcha" id="verifycode">
									<img style="margin-left:12px;" src="jsp/kaptcha.jsp" width="140px" height="45px" border="0" onClick="this.src='jsp/kaptcha.jsp?tmp='+new Date().getTime()" id="vdimgck" align="absmiddle" style="cursor:pointer"/>
								</span>
								
								<c:if test="${ajaxObj.result eq 0}">
								<label class='error error_reg'>${ajaxObj.msg}</label>
								</c:if>
							</dd>
							
							<dd class="submit clearfix">
								<input type="submit" class="btn-regist" id="loginSubmit" name="Submit" value="登  录">
							</dd>
						</dl>
					</form>
					<div class="btn_r">
						<span>还没有注册账号？</span>
						<a class="btn_com"  href="${pageContext.request.contextPath}/register"></a>
						<a class="fw" style="font-size:12px" target="_blank" href="#">忘记密码？</a>
					</div>
					<div class="xd-login-other">      
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