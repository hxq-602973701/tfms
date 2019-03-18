//点评星星
$(function(){
	var stars = [];
	$(".rating-wrap a").mouseover(function(){
		$(this).parent().siblings().find("a").removeClass("active-star");   
		$(this).addClass("active-star");   
		$("."+$(this).parent().parent().attr("id")).html($(this).attr("data-hint"))  
	}).mouseleave(function(){
		var selectID=$(this).parent().parent().attr("id")+"select";   
		$(this).removeClass("active-star");   
		if($("#"+selectID).length==0)   
		{
			$("."+$(this).parent().parent().attr("id")).removeClass("active-hint").html("");   
		}   
		else   
		{
			$("."+$(this).parent().parent().attr("id")).html($("#"+selectID).attr("data-hint"));   
			$("#"+selectID).addClass("active-star");   
		}   
	}).click(function(){
		$(this).addClass("active-star").attr('id',$(this).parent().parent().attr("id")+"select");   
		$(this).parent().siblings().find("a").attr("id","");   
		$("."+$(this).parent().parent().attr("id")).html($(this).attr("data-hint")).addClass("active-hint");
		$("input[name="+$(this).parent().parent().attr("id")+"]").val($(this).attr("data-rate-value"));
		var obj = $("input[name="+$(this).parent().parent().attr("id")+"]").val($(this).attr("data-rate-value")).val();
		stars.push(obj);
		alert(stars);
	});
	
});

//判断是否登录
/*
$(document).ready(function(){
	$("#dp_submit").click(function(){
		var userid=$("#userid").attr("value");
		if(userid==0){
			alert("请先登录后再点评!");
			return false;
		}
	});					   
});*/