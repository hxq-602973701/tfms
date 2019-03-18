var $cqh_width = 710; //宽度
var $cqh_height = 210; //高度

var $cqh_speed = 3000;//切换速度

var $cqh_box = $("#cqh-box");
var $cqh_li = $("#cqh-box li");
var $cqh_li_img = $("#cqh-box li img");
var $cqh_num_box = $("#cqh-num");
var $cqh_btn = $("#cqh-num span");
var $cqh_next = $("#cqh-next");
var $cqh = 0;

$cqh_li.hide();
$cqh_li.eq(0).show();

$cqh_box.width($cqh_width);
$cqh_box.height($cqh_height);

$cqh_li.width($cqh_width);
$cqh_li.height($cqh_height);

$cqh_li_img.width($cqh_width);
$cqh_li_img.height($cqh_height);

var $cqh_num = $cqh_li.size();

$cqh_num_box.width($cqh_num*20+3);

var $num_con = '';
for(i=0;i<$cqh_num;i++){
	$num_con+= '<span>'+(i+1)+'</span>';
}

$cqh_num_box.html($num_con);

$("#cqh-num span").eq($cqh).addClass("this");

$("#cqh-num span").mouseover(function(){

	var $rnd_num = parseInt(Math.random()*3+1);

	$("#cqh-num span").removeClass("this");
	$(this).addClass("this");
	$cqh_li.eq($cqh).hide(); 
	var $thisnum = parseInt($(this).text());
	$cqh = $thisnum-1;
	if($rnd_num==1){
	    $cqh_li.eq($cqh).fadeIn(1000);
	}else if($rnd_num==2){
		$cqh_li.eq($cqh).show(500);
	}else if($rnd_num==3){
		$cqh_li.eq($cqh).slideDown(1000); 
	}else{
		$cqh_li.eq($cqh).fadeIn(1000);
	}
});

var $autoFun;

if($cqh_num>1){
autoNext();
clearFun($cqh_box);
}


$cqh_next.click(function(){
	if($cqh==$cqh_num-1){

		var $rnd_num = parseInt(Math.random()*3+1);

		$("#cqh-num span").removeClass("this");
		$("#cqh-num span").eq(0).addClass("this");
		$cqh_li.eq($cqh).hide(); 
		
		if($rnd_num==1){
			$cqh_li.eq(0).fadeIn(1000);
		}else if($rnd_num==2){
			$cqh_li.eq(0).show(500);
		}else if($rnd_num==3){
			$cqh_li.eq(0).slideDown(1000); 
		}else{
			$cqh_li.eq(0).fadeIn(1000);
		}
		$cqh = 0;

	}else{
		var $rnd_num = parseInt(Math.random()*3+1);

		$("#cqh-num span").removeClass("this");
		$("#cqh-num span").eq($cqh+1).addClass("this");
		$cqh_li.eq($cqh).hide(); 
		
		if($rnd_num==1){
			$cqh_li.eq($cqh+1).fadeIn(1000);
		}else if($rnd_num==2){
			$cqh_li.eq($cqh+1).show(500);
		}else if($rnd_num==3){
			$cqh_li.eq($cqh+1).slideDown(1000); 
		}else{
			$cqh_li.eq($cqh+1).fadeIn(1000);
		}
		
		$cqh = $cqh+1;

	}
	
})


function autoNext(){
        $cqh_next.trigger('click');
        $autoFun = setTimeout(autoNext, $cqh_speed);//此处不可使用setInterval,setInterval是重复执行传入函数,这会引起第二次划入时停止失效
}
function clearAuto(){
        clearTimeout($autoFun);
}
function clearFun(elem){
        elem.hover(function(){
            clearAuto();
        }, function(){
            autoNext();
        });
}
