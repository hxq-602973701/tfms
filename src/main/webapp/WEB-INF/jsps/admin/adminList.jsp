<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/zTree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style1.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/index/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<style type="text/css">
	#initShop{display:none;}
</style>
<title>管理员列表</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">管理员列表</a></li>
    </ul>
</div>
    
<div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
	        <li class="click"><a href="add"><span><img src="${pageContext.request.contextPath}/resources/images/t01.png" /></span>添加管理员</a></li>
        </ul>
	</div>
	<!-- 列表开始 -->
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>管理员名称</th>
		        <th>用户名</th>
		        <th>角色</th>
		        <th width="250px">管理操作</th>
	        </tr>
        </thead>
        <tbody>
        	 <c:forEach items="${adminList}" var="admin">
	        <tr>
		        <td>${admin.name}</td>
		        <td>${admin.username}</td>
		        <td>
		        	<c:if test="${admin.state eq 1}">
		        		<span class="label label-success">系统管理员</span>
		        	</c:if>
		        	<c:if test="${admin.state eq 0}">
		        		<span class="label btn-danger">管理员</span>
		        	</c:if>
		        </td>
		        <td>
		        	<a href="${admin.id}/update" class="tablelink">修改</a>&nbsp;   
		        	<a href="javascript:del('${admin.id}');" class="tablelink"> 删除</a>
		        	<a href="javascript:initpwd('${admin.id}');" class="tablelink"> 初始化密码</a>
		        	<a href="javascript:addShop('${admin.id}');" class="tablelink"> 分配商铺</a>
		        </td>
	        </tr>
	        </c:forEach>
        </tbody>
   </table>
   <!-- 列表结束 -->
  <div id="initShop" class="ztree">
	<!--<ul id="treeDemo" class="ztree"></ul>  -->
  </div>
  <input type="hidden" id="relPath" value="<%=request.getContextPath()%>"/>
</div>
<script type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	function addShop(id){
		$.ajax({
			url:$('#relPath').val()+"/admin/initShop",
			type:"GET",
			data:{adminId:id},
			success:function(result){
				var zNodes = eval(result);
				$.fn.zTree.init($("#initShop"), setting, zNodes);
				//在这里面输入任何合法的js语句
				layer.open({
					type: 1, //page层
					area: ['280px', '280px'],
					title: "<span style='font-family:'微软雅黑';'>正在为分配商铺</span>",
					shade: false, //遮罩透明度
					shift: 1, //0-6的动画形式，-1不开启
					content:$('#initShop'),
					btn: ['分配商铺','取消'],
					yes:function(index,layero){
						layer.close(index);
						var treeObj = $.fn.zTree.getZTreeObj("initShop");
						var nodes = treeObj.getCheckedNodes(true);
						var shopArrIds= [];
						for(var i = 0; i < nodes.length; i++){
							shopArrIds.push(nodes[i].id);
						}
						var shopIds=shopArrIds.join(",");
						$.post($('#relPath').val()+"/admin/addShop2Admin",{shopIds:shopIds,adminId:id},function(result){
							if(result.result==1){
								layer.msg('店铺分配成功',{
			        				time: 1000,
			        				tips: 2
			        			});
							}
						},"json");
					}
				});
			}
		});
	}

	
	
	$('.tablelist tbody tr:odd').addClass('odd');
	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = id+"/delete";
			window.location.href=url;		
		}
	}
	
	function initpwd(id)
	{
		if(confirm("确定要初始化密码吗？"))
		{
			var url = id+"/initPassword";
			window.location.href=url;		
		}
	}
</script>
</body>
