/*用户登录验证的js*/
$().ready(function(){
	
	jQuery.validator.addMethod("mobile", function (value, element) {
	    var mobile = /^1[3|4|5|7|8]\d{9}$/;
		return this.optional(element) || (mobile.test(value));
	}, "手机格式不对");
	
	jQuery.validator.addMethod("same", function(value, element) {  
		return this.optional(element) || same(value);  
	}, "新密码不能与老密码重复"); 
	
	function same(pwd) {  
        var oldPwd = $("#oldpassword").val();  
        if (oldPwd == pwd)  
            return false;  
        else  
            return true;  
    } 
	
	$("#userValidate").validate({
		rules:{
			username:{
				required:true
			},
			password:{
				required:true
			},
			kaptcha:{
				required:true
			}
		},
		messages:{
			username:{
				required: "<label class='error error_reg'>请输入用户名称！</label>"
			},
			password:{
				required: "<label class='error error_reg'>请输入用户密码！</label>"
			},
			kaptcha:{
				required: "<label class='error error_reg'>请输入验证码！</label>"
			}
		}
	});
	
	//注册验证
	$("#userReg").validate({
		rules:{
			username:{
				required:true,
				rangelength: [6,15],
			},
			password:{
				required:true,
				rangelength: [8,15],
			},
			password2:{
				required:true,
				equalTo: "#password"
			},
			email:{
				required: true,
				email:true
			},
			kaptcha:{
				required:true
			}
		},
		messages:{
			username:{
				required: "<label class='error error_reg'>请输入用户名称！</label>",
				rangelength:"<label class='error error_reg'>长度至少6位,不能超过16位！</label>"
			},
			password:{
				required: "<label class='error error_reg'>请输入用户密码！</label>",
				rangelength:"<label class='error error_reg'>长度至少8位,不能超过16位！</label>"
			},
			password2:{
				required: "<label class='error error_reg'>确认密码不能为空！</label>",
				equalTo:  "<label class='error error_reg'>确认密码和密码相同！</label>"
			},
			email:{
				required: "<label class='error error_reg'>邮箱不能为空！</label>",
				email: "<label class='error error_reg'>邮箱格式不正确！</label>"
			},
			kaptcha:{
				required: "<label class='error error_reg'>请输入验证码！</label>"
			}
		}
	});
	
	/*用户信息修改验证*/
	$("#userComplete").validate({
		rules:{
			name:{
				required:true
			},
			phone:{
				required: true,
				mobile:true
			},
			address:{
				required:true
			}
		},
		messages:{
			name:{
				required: "<label style='color:red;'>&nbsp;用户名称不能为空！</label>"
			},
			phone:{
				required: "<label style='color:red;'>&nbsp;手机不能为空！</label>",
				mobile:"<label style='color:orange;'>&nbsp;手机格式不正确！</label>"
			},
			address:{
				required:"<label style='color:red;'>&nbsp;地址不能为空！</label>"
			}
		}
	});
	
	/*用户密码修改验证*/
	$("#editPassword").validate({
		rules:{
			oldpassword:{
				required:true,
				remote:{
					type:"POST",
		            url:"checkPassword", //servlet
		            data:{
		                 pwd:function(){
		                	 return $("#oldpassword").val();
		                 },
		                 userId:function(){
		                	 return $("#uid").val();
		                 }
		            } 
				}
			},
			password:{
				required:true,
				rangelength: [8,15],
				same:true
			},
			password2:{
				required:true,
				equalTo: "#password"
			}
		},
		messages:{
			oldpassword:{
				required: "<label style='color:red;'>&nbsp;请输入用户原来密码！</label>",
				remote: "<label style='color:orange;'>&nbsp;输入密码错误！</label>"
			},
			password:{
				required: "<label style='color:red;'>&nbsp;请输入新密码密码！</label>",
				rangelength:"<label style='color:orange;'>&nbsp;长度至少8位,不能超过16位！</label>",
				same:"<label style='color:gold;'>&nbsp;新密码不能与老密码一样！</label>"
			},
			password2:{
				required: "<label style='color:red;'>&nbsp;确认密码不能为空！</label>",
				equalTo:  "<label style='color:orange;'>&nbsp;确认密码和密码相同！</label>"
			}
		}
	});

	
	/*下订单页面*/
	$("#subOrder").validate({
		rules:{
			name:{
				required:true
			},
			phone:{
				required: true,
				mobile:true
			},
			address:{
				required:true
			}
		},
		messages:{
			name:{
				required: "<label style='color:red;'>&nbsp;联系人姓名不能为空！</label>"
			},
			phone:{
				required: "<label style='color:red;'>&nbsp;联系人手机不能为空！</label>",
				mobile:"<label style='color:orange;'>&nbsp;手机格式不正确！</label>"
			},
			address:{
				required:"<label style='color:red;'>&nbsp;联系人地址不能为空！</label>"
			}
		}
	});
});