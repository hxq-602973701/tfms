$(document).ready(function(){	
	//读取购物车
	$.ajax({
		url:$('#relPath').val()+"/readCar",
		type:"GET",
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			genCart(data);
		}
	});
	
	
	//加入购物车
	$("#addFood").live('click', function(){
		//食物id
		var fid=$(this).attr("value");
		//商店Id
		var sid=$('#shop').val();
		$.ajax({
			url:$('#relPath').val()+"/addFood2Car",
			type:"GET",
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:{foodId:fid},
			success:function(data,st){
				genCart(data);
			}
		});
	});
	
	//加一
	$("#adds").live('click', function(){
		//食物id
		var fid=$("#foodId").val();
		$.ajax({
			url:$('#relPath').val()+"/addCar",
			type:"GET",
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:{foodId:fid},
			success:function(data,st){
				genCart(data);
			}
		});
	});
	//减一
	$("#minus").live('click', function(){
		//alert("减一");
		var num = $('input[name="number"]').val();
		if(num==1){
			return;
		}else{
			//食物id
			var fid=$("#foodId").val();
			$.ajax({
				url:$('#relPath').val()+"/minusCar",
				type:"GET",
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:{foodId:fid},
				success:function(data,st){
					genCart(data);
				}
			});
		}
	});
	//删除
	$("#delete").live('click', function(){
		//食物id
		var fid=$("#foodId").val();
		$.ajax({
			url:$('#relPath').val()+"/deleteCar",
			type:"GET",
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:{foodId:fid},
			success:function(data,st){
				genCart(data);
			}
		});
	});
	
	function genCart(data){
		var res = eval(data);
		var node = "";
		var total = 0;
		for(var i = 0;i < res.length;i++){
			var obj = res[i];
			node = node +
			"<li>"+
				"<div style='margin:10px 0px 0px 4px;'>"+
				   "<img id='delete' src='"+$('#relPath').val()+"/resources/index/images/delete.gif' />"+
				"</div>"+
				"<div>"+
					"<div style='height:5px;width:100%'><input id='foodId' type='hidden' value='"+obj.food.id+"'/></div>"+
					"<span>"+
						"<a id='minus' href='javascript:void(0);' title='减一' style='margin-right:2px;'>"+
							"<img src='"+$('#relPath').val()+"/resources/index/images/bag_close.gif' name='minus' />"+
						"</a>"+
						"<input readonly='readonly' type='text' name='number' maxlength='' value='"+obj.number+"'/>"+
						"<a id='adds' href='javascript:void(0);' title='加一' style='margin-left:2px;'>"+
							"<img src='"+$('#relPath').val()+"/resources/index/images/bag_open.gif' name='add' />"+
						"</a>"+
					"</span>"+
				 "</div>"+
				"<div>￥"+obj.price+"</div>"+obj.name+""+
			"</li>";
			total = total + (obj.price*obj.number);
		}
		$("#cart_food").html(node);
		$("#total").empty();
		$("#total").append("合计：￥"+total+"");
	}
});