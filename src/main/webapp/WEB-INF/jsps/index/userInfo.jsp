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
				<div class="titleTabBar bgGreen lineTB">
				  <ul>
					<li class="select"><a href="<%=request.getContextPath()%>/userInfo">基本资料</a></li>
					<li><a href="<%=request.getContextPath()%>/updatePassword">修改密码</a></li>
				  </ul>
				</div>
				<form id="userComplete" class="mTB10 mL10 mR10" action="<%=request.getContextPath()%>/userInfo" method="post">
				  <input type="hidden" name="id" value="${userInfo.id}" readonly="readonly"/>
				  <div class="titleBar bgGrass lineB">
					<div class="fLeftNoForm"><strong>基本资料</strong></div>
					<div class="fRight titSecondary white" style="margin-top:5px;"></div>
				  </div>
				  <table cellspacing="1" class="submit">
					<tbody>
						<tr>
							<td width="15%" align="right" valign="top">用户名：</td>
							<td> ${userInfo.username}</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								<span class="tdl">姓名：</span>
							</td>
							<td>
								<input name="name" id="truename" size="12" value="${userInfo.name}" class="text_value"/> 
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								<span class="tdl">性别：</span>
							</td>
							<td>
								<c:if test="${userInfo.sex eq '男'}">
								<input type="radio" value="男" name="sex" checked="checked"/> 男 
								<input type="radio" value="女" name="sex" /> 女 
								</c:if>
								<c:if test="${userInfo.sex eq '女'}">
								<input type="radio" value="男" name="sex"/> 男 
								<input type="radio" value="女" name="sex" checked="checked"/> 女 
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								<span class="tdl">手机号：</span>
							</td>
							<td>
								<input name="phone" id="phone" size="30" value="${userInfo.phone}" class="text_value"/> 
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								<span class="tdl">安全邮箱：</span>
							</td>
							<td>
								<input name="email" id="email" size="30" value="${userInfo.email}" class="text_value" readonly="readonly"/> 
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								<span class="tdl">联系地址：</span>
							</td>
							<td>
								<input name="address" id="address" size="40" value="${userInfo.address}" class="text_value" /> 
							</td>
						</tr>
					</tbody>
					<tfoot>
					  <tr>
						<td height="45">&nbsp;</td>
						<td height="45">&nbsp;<input name="gourl" type="hidden" value="" /><button class="button2" type="submit">更新</button></td>
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
<script type="text/javascript">
	

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