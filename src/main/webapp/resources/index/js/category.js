$(function(){
	//首页左侧分类菜单
	/*
	$(".category ul.menu").find("li").each(
		function() {
			$(this).hover(
				function() {
				    var cat_id = $(this).attr("cat_id");
					var menu = $(this).find("div[cat_menu_id='"+cat_id+"']");
					menu.show();
					$(this).addClass("hover");
					if(menu.attr("hover")>0) return;
					menu.masonry({itemSelector: 'dl'});
					var menu_height = menu.height();
					if (menu_height < 60) menu.height(80);
					menu_height = menu.height();
					var li_top = $(this).position().top;
					if ((li_top > 60) && (menu_height >= li_top)) $(menu).css("top",-li_top+50);
					if ((li_top > 150) && (menu_height >= li_top)) $(menu).css("top",-li_top+90);
					if ((li_top > 240) && (li_top > menu_height)) $(menu).css("top",menu_height-li_top+120);
					if (li_top > 300 && (li_top > menu_height)) $(menu).css("top",60-menu_height);
					if ((li_top > 40) && (menu_height <= 120)) $(menu).css("top",-5);
					menu.attr("hover",1);
				},
				function() {
					$(this).removeClass("hover");
				    var cat_id = $(this).attr("cat_id");
					$(this).find("div[cat_menu_id='"+cat_id+"']").hide();
				}
			);
		}
	);*/
	$('.search_tab').live("click",function(){
		if($(this).attr("op") == 'search_dash'){
			//美食
			$("input[name=type]").val('1');
			$("input[name=keyword]").attr('placeholder','输入关键字查找美食');
			$('.search_tabs').html('<li class="search_tab" op="search_dash">美食</li><li class="search_tab" op="search_store">商铺</li>');
			//alert($("input[name=type]").val());
		}else if($(this).attr("op") == 'search_store'){
			//商店
			$("input[name=type]").val('2');
			$("input[name=keyword]").attr('placeholder','输入关键字查找商铺');
			$('.search_tabs').html('<li class="search_tab" op="search_store">商铺</li><li class="search_tab" op="search_dash">美食</li>');
			//alert($("input[name=type]").val());
		}
		$('.tab').removeClass("tab_over");
	});
	$('#SubmitFrom').click(function(){
		alert("aaaaaaaaaa");
		//alert($("input[name=type]").val());
		$('#search_form').submit();
	});
});

$(function() {
		$(".tab").hover(function() {
		$(this).addClass("tab_over");
	},
	function() {
		$(this).removeClass("tab_over");
	});
});
$(function() {
		$(".dropdown").hover(function() {
		$(this).addClass("dropdown-open dropdown-open-app");
	},
	function() {
		$(this).removeClass("dropdown-open dropdown-open-app");
	});
});
$(function() {
		$(".section-main").hover(function() {
		$(this).addClass("nav-drop");
	},
	function() {
		$(this).removeClass("nav-drop");
	});
});