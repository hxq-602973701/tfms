<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cloud.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script language="javascript">
	$(function(){
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    }); 
});  
</script> 
</head>
<body style="background-color:#1c77ac; background-image:url(../resources/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  
	<div class="logintop">    
    	<span>欢迎登录后台管理界面平台</span>    
	    <ul>
	    <li><a href="#">回首页</a></li>
	    <li><a href="#">帮助</a></li>
	    <li><a href="#">关于</a></li>
	    </ul>    
    </div>
    <div class="loginbody">
	    <span class="systemlogo"></span> 
	    <div class="loginbox">
		    <ul>
		    <li><input id="username" name="username" type="text" class="loginuser" placeholder="请输入用户名"/></li>
		    <li><input id="password" name="password" type="password" class="loginpwd" placeholder="请输入密码"/></li>
		    <li>
		    	<input id="kaptcha" name="kaptcha" type="text" class="captha" placeholder="验证码"/>
		    	<img style="margin-left:10px;" src="<%=request.getContextPath()%>/jsp/kaptcha.jsp" width="80px" height="35px" border="0" onClick="this.src='<%=request.getContextPath()%>/jsp/kaptcha.jsp?tmp='+new Date().getTime()" id="vdimgck" align="absmiddle" style="cursor:pointer"/>
		    	<input id="subBtn" type="button" class="loginbtn" value="登录"/></li>
		    </ul>
	    </div>
    </div>
    <div class="loginbm">版权所有：天福美食城  技术支持：刘颖</div>
    <input id="relPath" type="hidden" value="<%=request.getContextPath()%>"/>
</body>
<script type="text/javascript">
	$("#subBtn").live('click', function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var kaptcha = $("#kaptcha").val();
		if(username==null || ""==username){
			layer.msg("用户名不能为空！",{
				time: 2000,
				tips: 2
			});
		}else if(password==null || ""==password){
			layer.msg("密码不能为空！",{
				time: 2000,
				tips: 2
			});
		}else if(kaptcha==null || ""==kaptcha){
			layer.msg("验证码不能为空！",{
				time: 2000,
				tips: 2
			});
		}else{
			$.ajax({
				url:$('#relPath').val()+"/admin/alogin",
				data:{username:username,password:password,kaptcha:kaptcha},
				type:"POST",
				success:function(result){
					var obj = eval("("+result+")")
					var flag = obj.result;
					if(flag==0){
						layer.msg("输入验证码错误！",{
							time: 2000,
							tips: 2
						});
					}else if(flag==1){
						layer.msg("输入用户名错误！",{
							time: 2000,
							tips: 2
						});
					}else if(flag==2){
						layer.msg("输入密码错误！",{
							time: 2000,
							tips: 2
						});
					}else if(flag==3){
						window.location.href ='<%=request.getContextPath()%>/main';
					}
				}
			});
		}
	});
</script>
</html>
