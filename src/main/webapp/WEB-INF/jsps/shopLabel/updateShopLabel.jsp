<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加食品种类页面</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
</head>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">添加商铺标签</a></li>
	   	</ul>
	</div>
	<form:form modelAttribute="shopLabel">
	
	<div class="formbody">
	   <div id="usual1" class="usual"> 
		    <div class="itab">
			  	<ul> 
			   		<li><a class="selected">添加商铺标签</a></li> 
			  	</ul>
		    </div> 
		  	<div id="tab1" class="tabson">
		    	<div class="formtext">您好，欢迎您使用食品类型添加功能！<b>请勿更改</b></div>
		    	<div class="forminfo">
		    	<table>
		    		<tr>
		    			<td class="tableleft">商铺标签名称</td>
		    			<td>
		    				<form:input path="name" cssClass="dfinput" cssStyle="float:left;"/>
		    				&nbsp;<form:errors path="name" cssStyle="padding-left:10px;color:red;"/>
		    			</td>
		    		</tr>
		    		<tr style="height:10px;">
		    		</tr>
		    		<tr style="margin-top:10px;">
		    			<td class="tableleft">是否启用</td>
		    			<td>
		    				<div style="float:left;">
		    				<form:radiobutton path="state" value="1" cssClass="dfrad"/>启用
				    		<form:radiobutton path="state" value="0" cssClass="dfrad"/>禁用
		    				</div>
		    				<form:errors path="state" cssStyle="padding-left:10px;color:red;"/>
		    			</td>
		    			
		    		</tr>
		    		<tr style="height:10px;">
		    		</tr>
		    		<tr>
		    			<td class="tableleft">商铺标签备注</td>
		    			<td><form:textarea path="description" cssClass="textinput"/></td>
		    		</tr>
		    		<tr style="height:10px;">
		    		</tr>
		    		<tr>
		    			<td class="tableleft"></td>
		    			<td>
		    				<input name="" type="submit" class="btn" value="修改"/>&nbsp;&nbsp;
				   			<input id="backid" type="button" class="btn" value="返回列表"/>
				   		</td>
		    		</tr>
		    		
		    	</table>
		    	</div>
	    	</div> 
		</div>
	</div>
	</form:form>
<script>
$(function () {       
	$('#backid').click(function(){
		window.location.href="${pageContext.request.contextPath}/shopLabel/list";
	});
});
</script>
</body>
</html>