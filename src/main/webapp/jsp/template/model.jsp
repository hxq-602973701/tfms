<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="module_sidebar">
	<h2><b>本站信息</b></h2>
	<div class="wrap">
		<div class="classify_list">
			<ul>
				<li><a href="<%=request.getContextPath()%>/noticeList">本站公告</a></li>
				<li><a href="<%=request.getContextPath()%>/activityList">本站活动</a></li>
			</ul>
		</div>
	</div>
	<h2><b>关于本站</b></h2>
	<div class="wrap">
		<div class="classify_list">
			<ul>
								<li><a  target="_blank" href="<%=request.getContextPath()%>/1/articleShow">关于我们</a></li>
				<li><a  target="_blank" href="<%=request.getContextPath()%>/2/articleShow">联系我们</a></li>
				<li><a  target="_blank" href="<%=request.getContextPath()%>/3/articleShow">关于支付</a></li>
				<li><a  target="_blank" href="<%=request.getContextPath()%>/4/articleShow">帮助中心</a></li>
				<li><a  target="_blank" href="<%=request.getContextPath()%>/5/articleShow">法律申明</a></li>
				<li><a  target="_blank" href="<%=request.getContextPath()%>/6/articleShow">招贤纳士</a></li>
			</ul>
		</div>
	</div>
</div>