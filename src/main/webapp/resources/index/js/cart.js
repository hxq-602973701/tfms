$(document).ready(function(){
	//加入购物车
	$("input[name='btnBuy']").live('click', function(){
		var id=$(this).attr("value");
		var cid=$("#companyid").attr("value");
/*		var address=getcookie('address');
		if(address==""){
			alert("请先选择您所在位置！");
			popup();
			return ;
		}*/
		$.ajax({
			type:"GET",
			url:"index.php",
			data:{m:"company",c:"cart",pid:id,cid:cid,random:Math.random()},
			success:function(data,st){
				$("#cart").html(data);
			}
		});
	})
	
	//加一
	$("img[name='add']").live('click', function(){
		var elem=$(this).parent().parent().parent().parent();
		var id=$(elem).attr("id");
		$.ajax({
			type:"GET",
			url:"index.php",
			data:{m:"company",c:"cart",f:"add",cid:id,random:Math.random()},
			success:function(data,st){
				$("#cart").html(data);
			}
		});
	})
	
	//减一
	$("img[name='minus']").live('click', function(){
		var elem=$(this).parent().parent().parent().parent();
		var id=$(elem).attr("id");
		$.ajax({
			type:"GET",
			url:"index.php",
			data:{m:"company",c:"cart",f:"minus",cid:id,random:Math.random()},
			success:function(data,st){
				$("#cart").html(data);
			}
		});
	})
	
	//改变份数
	$("input[name='quantity']").live('blur', function(){
		var elem=$(this).parent().parent().parent();
		var id=$(elem).attr("id");
		var value=$(this).attr("value");
		$.ajax({
			type:"GET",
			url:"index.php",
			data:{m:"company",c:"cart",f:"change_quantity",cid:id,values:value,random:Math.random()},
			success:function(data,st){
				$("#cart").html(data);
			}
		});
	})
	
	//删除
	$("img[name='delete']").live('click', function(){
		var elem=$(this).parent().parent();
		var id=$(elem).attr("id");
		$.ajax({
			type:"GET",
			url:"index.php",
			data:{m:"company",c:"cart",f:"delete",cid:id,random:Math.random()},
			success:function(data,st){
				$("#cart").html(data);
			}
		});
	})
	
	//提交
	$(".cart_button").live('click', function(){
		location.href("/index.php?m=company&f=cart");							 
	})
	
	//修改地址
	$("#address").click(function(){ 
		var title=$(this).attr("title");
		var elem=$(this).parent();
		$(elem).html("<input type=\"text\" name=\"address\" value=\""+title+"\" class=\"order_text\"> <input type=\"checkbox\" name=\"issave\" id=\"issave\" value=\"1\" />设为默认地址");
	});
	
	//备注
	$("input[id^='buttonValidate']").click(function(){ 
		var btnVal=$.trim($(this).val());
		var str = $('#note').val() + btnVal;
		$('#note').val(str);
	});
	
	//发送短信
	$("input[name='sendcode']").live('click', function(){
		var phone=$("input[name='phone']").attr("value");
		
		//判断手机号码格式
		if(phone.length < 11){
			alert("手机号码位数不正确");
			return false;
		}
		var patrn=/^((\(\d{2,3}\))|(\d{3}\-))?13|14|15|18\d{9}$/; 
		if (!patrn.exec(phone)){
			alert("手机号码格式不正确");
			return false;
		}
		
		$.ajax({
			type:"GET",
			url:"index.php",
			data:{m:"sms",tel:phone,type:"cart",random:Math.random()},
			success:function(data,st){
				$("#phonetext").html(data);
			}
		});					 
	});
	
	//返回余额
	$("#payment").live('change',function(){
		var value=$(this).attr("value");
		if(value==4){
			$("#money").show();
			$.ajax({
				type:"GET",
				url:"index.php",
				data:{m:"company",c:"cart",f:"getmoney",random:Math.random()},
				success:function(data,st){
					$("#money").html(data);
				}
			});
		}else{
			$("#money").hide();
		}							
	});
	
	$("#sendtime").live('change',function(){
		var value=$(this).attr("value");
		if(value==2){
			$("#showtime").show();
		}else{
			$("#showtime").hide();
		}
	});
	
	function getcookie(name) {
		var cookie_start = document.cookie.indexOf(name);
		var cookie_end = document.cookie.indexOf(";", cookie_start);
		return cookie_start == -1 ? '' : unescape(document.cookie.substring(cookie_start + name.length + 1, (cookie_end > cookie_start ? cookie_end : document.cookie.length)));
	} 
	
});