<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加活动页面</title>
	<link type="text/css" href="${pageContext.request.contextPath}/resources/umeditor1.2.2/themes/default/css/umeditor.css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/third-party/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/umeditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/umeditor.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/umeditor1.2.2/lang/zh-cn/zh-cn.js"></script>
	<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/uploadify/uploadify.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-2.0.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/uploadify/jquery.uploadify.min.js"></script>
</head>
<body>
	<input type="hidden" id="sid" value="<%=session.getId()%>">
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">添加活动</a></li>
	   	</ul>
	</div>
	<form:form modelAttribute="activity">
	<form:hidden path="createTime"/>
	<div class="formbody">
	   <div id="usual1" class="usual"> 
		    <div class="itab">
			  	<ul> 
			   		<li><a class="selected">添加活动</a></li> 
			  	</ul>
		    </div> 
		  	<div id="tab1" class="tabson">
		    	<div class="formtext">您好，欢迎您使用活动添加功能！</div>
		    	<div class="forminfo">
		    	<table>
		    		<tr>
		    			<td class="tableleft">活动名称</td>
		    			<td>
		    				<form:input path="title" cssClass="dfinput" cssStyle="float:left;"/>
		    				<form:errors path="title" cssStyle="padding-left:10px;color:red;"/>
		    			</td>
		    		</tr>
		    			<tr style="height:10px;">
		    		</tr>
		    		<tr id="thumbImage">
		    			<td>缩略图</td>
		    			<td>
							<div id='thumbImage'>
								<input type="hidden" name="url" value="${activity.url}"/>
								<img src="${pageContext.request.contextPath}/resources/upload/activity/thumbnail/${activity.url}">;
							</div>
						</td>
		    		</tr>
		    		<tr style="height:10px;">
		    		</tr>
		    		<%--<tr>--%>
		    			<%--<td class="tableleft">首页图片</td>--%>
		    			<%--<td>--%>
		    				<%--<input type="file" name="uploadFile" id="uploadFile" value="选择图片"/>--%>
		    			<%--</td>--%>
		    		<%--</tr>--%>
					<tr>
						<div class="layui-upload">
							活动图片:<button type="button" class="layui-btn" id="test1">上传图片</button>
							<div class="layui-upload-list">
								<img class="layui-upload-img" id="demo1">
								<p id="demoText"></p>
							</div>
						</div>
					</tr>
		    		<tr style="height:10px;">
		    		</tr>
		    		<tr id="thumbImage">
		    			
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
		    			<td class="tableleft">活动备注</td>
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
    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '${pageContext.request.contextPath}/activity/upload' //上传接口
            ,done: function(res){
                var jsonData = res;
                $("#thumbImage").empty();
                var node = "";
                node =node+"<td class='tableleft'>食品缩略图</td>";
                node =node+"<td>";
                node =node+"<div id='thumbImage'>";
                node = node+"<input type='hidden' name='url' value='"+jsonData.obj+"'/>"
                node =node+"<img src='${pageContext.request.contextPath}/resources/upload/activity/thumbnail/"+jsonData.obj+"'>";
                node =node+"</div>";
                node =node+"</td>";
                $("#thumbImage").append(node);
            }
            ,error: function(){
                //请求异常回调
            }
        });
    });
	//初始化文本编辑器
	var um = UM.getEditor('myEditor',{
		toolbar:[
           'undo redo | bold italic | forecolor backcolor |',
           'insertorderedlist insertunorderedlist |paragraph | fontfamily fontsize' ,
           '| justifyleft justifycenter justifyright justifyjustify ',
           '| image print preview fullscreen'
       ]
	});


$(function() {
	$('#backid').click(function(){
		window.location.href="${pageContext.request.contextPath}/activity/list";
	});
	$("#uploadFile").uploadify({
		height:30,
		swf:"${pageContext.request.contextPath}/resources/uploadify/uploadify.swf",
		uploader:"${pageContext.request.contextPath}/activity/upload",
		fileObjName:"activityImage",
		fileTypeExts:'*.gif; *.jpg; *.png',
		formData:{"sid":$("#sid").val()},
		multi:false,
		width:120,
		onUploadSuccess: function(file,data,response) {//file文件对象，response：请求结果(true,false),data：json字符串必须转成json格
			var jsonData = $.parseJSON(data);
			$("#thumbImage").empty(); 
			var node = "";
			node =node+"<td class='tableleft'>活动缩略图</td>";
			node =node+"<td>";
			node =node+"<div id='thumbImage'>";
			node = node+"<input type='hidden' name='url' value='"+jsonData.obj+"'/>"
			node =node+"<img src='${pageContext.request.contextPath}/resources/upload/activity/thumbnail/"+jsonData.obj+"'>";
			node =node+"</div>";
			node =node+"</td>";
        	$("#thumbImage").append(node);
		}
	});
});
</script>
</body>
</html>