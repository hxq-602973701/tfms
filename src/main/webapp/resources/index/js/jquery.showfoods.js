$(document).ready(function(){	
	$.ajax({
		url:$('#relPath').val()+"/showShopFoods",
		type:"GET",
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:"shopId="+$('#shop').val(),
		success:function(result){
			var node = "";
			var data = eval(result);
			for(var i=0;i<data.length;i++){
				var type = data[i];
				var foodTypename = type.name;
				node = node+"<dt>"+foodTypename+"</dt>";
				var foods = type.foods;
				if(foods.length==0){
					node = node+
					"<dd>"+
					"<div><i>商家这类商品没有上架...</i>"+
					"</div>"+
					"</dd>";
				}else{
					for(var j = 0;j<foods.length;j++){
						var food = foods[j];
						var foodname = food.name;
						node = node+
						"<dd id='"+food.id+"'>";
						if(food.shop.isrest==1){
						node = node+
						"<a href='javascript:;' class='add' id='addFood' value='"+food.id+"'>"+
							"<img src='"+$('#relPath').val()+"/resources/index/images/add1.png' width='20'/>"+
						"</a>";
						}else{
						node = node+
						"<a href='javascript:void(0);'class='add'>"+
							"<img src='"+$('#relPath').val()+"/resources/index/images/add.png' width='20'/>"+
						"</a>";
						}
						node = node+
						"<span>￥"+food.newprice+"</span>"+
						"<a href='javascript:;' class='photo'>"+
							"<img src='"+$('#relPath').val()+"/resources/upload/food/"+food.url+"' width='25' height='25' />"+
						"</a>"+
						"<div>"+food.name+"<i>["+food.desription+"]</i>"+
						"</div>"+
						"</dd>"+
						"<div class='clear'></div>";
					}
				}
			}
			$("#con_list_1").append(node);
		}
	});
});