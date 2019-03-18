<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加公告页面</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/resources/umeditor1.2.2/themes/default/css/umeditor.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/third-party/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/umeditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/umeditor.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">添加公告</a></li>
	   	</ul>
	</div>
	<form:form modelAttribute="notice">
	
	<div class="formbody">
	   <div id="usual1" class="usual"> 
		    <div class="itab">
			  	<ul> 
			   		<li><a class="selected">添加公告</a></li> 
			  	</ul>
		    </div> 
		  	<div id="tab1" class="tabson">
		    	<div class="formtext">您好，欢迎您使用公告添加功能！<b>请勿更改</b></div>
		    	<div class="forminfo">
		    	<table>
		    		<tr>
		    			<td class="tableleft">公告标题</td>
		    			<td>
		    				<form:input path="title" cssClass="dfinput" cssStyle="float:left;"/>
		    				&nbsp;<form:errors path="title" cssStyle="padding-left:10px;color:red;"/>
		    			</td>
		    		</tr>
		    		<tr style="height:10px;">
		    		</tr>
		    		<tr style="margin-top:10px;">
		    			<td class="tableleft">是否发布</td>
		    			<td>
		    				<div style="float:left;">
		    				<form:radiobutton path="state" value="1" cssClass="dfrad"/>发布
				    		<form:radiobutton path="state" value="0" cssClass="dfrad"/>不发布
		    				</div>
		    				<form:errors path="state" cssStyle="padding-left:10px;color:red;"/>
		    			</td>
		    			
		    		</tr>
		    		<tr style="height:10px;">
		    		</tr>
		    		<tr>
		    			<td class="tableleft">公告内容</td>
		    			<td>
		    				<form:textarea path="content" id="myEditor" type="text/plain" cssStyle="width:620px;height:200px;"/>
		    				&nbsp;<form:errors path="content" cssStyle="padding-left:10px;color:red;"/>
		    			</td>
		    		</tr>
		    		<tr style="height:10px;">
		    		</tr>
		    		<tr>
		    			<td class="tableleft"></td>
		    			<td>
		    				<input type="submit" class="btn" value="添加"/>&nbsp;&nbsp;
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
//初始化文本编辑器
 var um = UM.getEditor('myEditor',{
		toolbar:[
            'undo redo | bold italic | forecolor backcolor |',
            'insertorderedlist insertunorderedlist |paragraph | fontfamily fontsize' ,
            '| justifyleft justifycenter justifyright justifyjustify ',
            '| image print preview fullscreen'
        ]
	});

$(function () {       
	$('#backid').click(function(){
		window.location.href="${pageContext.request.contextPath}/notice/list";
	});
});
</script>
</body>
</html>