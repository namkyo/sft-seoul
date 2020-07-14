
$(function(){
	var win_w=$(window).width()/*브라우져 넓이*/
	var nav_data = "off";
	
	$('input[type=text]').each(function(){
		$(this).attr("autocomplete","off");
	});

	/*snav*/
		$('.nav_wrap > li').mouseenter(function(){			
			$(this).find('.nav_wrap2').stop().slideDown(600 , "easeOutQuad")	
			$('.nav_wrap2').not($(this).find('.nav_wrap2')).hide()
		})
		$('header').mouseleave(function(){
			$('.nav_wrap2').hide()
		})
	/*enav*/

	/*s슬라이드
	var slide_num = 1;
	var slide_w = $('.slide1_wrap').width();
	var slide_sun_w = $('.slide1 > li').css('width',slide_w);
	var slide_count = parseInt($('.slide1 > li').length)
	var slide1_wrap2 = $('.slide1').css('width', slide_w * slide_count)
	var slide_time = 700
	id = setInterval(function(){next()},5000)
	function ani(){
		$('.slide1').stop().animate({'left': slide_w * (-slide_num+1)},slide_time)
		$('.slide1_btn_wrap span').removeClass('on')
		$('.slide1 li').removeClass('on')
		$('.slide1 .slide1_con'+slide_num).addClass('on')	
		$('.slide1_btn_wrap .slide1_btn'+slide_num).addClass('on')	
	}
	function next(){
		slide_num++
		if(slide_num == slide_count+1){
			$('.slide1').css('left',0)
			slide_num=2
			}
		ani()
	}

	
	$('.slide1_btn_wrap span').click(function(){
		clearInterval(id)
		slide_num = parseInt($(this).attr('data-slide1_btn'))
		ani()
		id = setInterval(function(){next()},5000)
	})

	$('.go_stop').click(function(){
		slide_go_stop = $(this).attr('data-go-stop')		
		if(slide_go_stop == "go"){
			clearInterval(id)
			$(this).find('img').removeClass("on")
			$(this).find('img.'+ slide_go_stop).addClass("on")
			$(this).attr('data-go-stop' , "stop")
		}else if(slide_go_stop == "stop"){
			clearInterval(id)
			id = setInterval(function(){next()},5000)
			$(this).find('img').removeClass("on")
			$(this).find('img.'+ slide_go_stop).addClass("on")
			$(this).attr('data-go-stop' , "go")
		}	
	})
	/*e슬라이드*/
	
	


	/*메인 공지사항 & 이벤트*/
	$(".cont2_wrap2_1 .cont2_row1 h2").click(function(){
	
		main_noti = parseInt($(this).attr('data_noti'))
		$('.cont2_wrap2_1 .cont2_row1 h2').removeClass('on')
		$(this).addClass('on')
		$('.cont2_wrap2_1 .cont2_row2').removeClass('on')
		$('.cont2_noti' + main_noti).addClass('on')	
	
	})
	/*메인 공지사항 & 이벤트*/


	/*계열사*/
	var box_w = parseInt($('.relation ul li').length)*180
	$('.motion_box').css('width',box_w)
	x_data=0
	dir="Left"
	function moveLeft(){
		wid=$('.motion_box ul').width()
		x_data-=1
		if(x_data < -wid){
			x_data=0
		}
		$('.motion_box').css('left',x_data)
	}
	function moveRight(){
		x_data+=1
		if(x_data>0){
			x_data=-wid
		}
		$('.motion_box').css('left',x_data)
	}

	relation = setInterval(function(){moveLeft()},50)

	$('.relation_prev').click(function(){
		clearInterval(relation)
		if(x_data>-156){
			x_data=-wid
			$('.motion_box').css('left',x_data)
		}
		$('.motion_box').stop().animate({'left':x_data+156})
		x_data +=156

		dir="Right"
	})

	$('.relation_next').click(function(){
		clearInterval(relation)
		if(x_data < -wid +156 ){
			x_data=0
			$('.motion_box').css('left',x_data)
		}
		$('.motion_box').stop().animate({'left':x_data-156})
		x_data -=156

		dir="Left"
	})

	$('.relation_wrap2').mouseenter(function(){
		clearInterval(relation)
	})

	$('.relation_wrap2').mouseleave(function(){
		clearInterval(relation)
		relation=setInterval(function(){eval('move'+dir+'()')},50)//
	})
	/*계열사*/

	



	/*lnb start*/
	$('.lnb_wrap > ul li').hover(function(){
		$(this).find('div').stop().slideToggle(300)
	})
	$('.selcet_box1 , .selcet_box2').click(function(){
		$(this).find('ul').stop().slideDown(300)
	})
	$('.selcet_box1 , .selcet_box2').mouseleave(function(){
		$(this).find('ul').stop().slideUp(300)
	})
	/*lnb end*/


	/*s자주묻는질문*/
	$('.roll_warp > li ').click(function(){
		var on_off=$(this).attr('data-on')
		var noti=$(this).attr('data-noti')
		if(on_off == "off"){
			$('.roll_con').stop().slideUp(400)
			$(this).find('.roll_con').stop().slideDown(400)
			$('.roll_title .roll_rest3').children('.chevron_up').removeClass('on')
			$('.roll_title .roll_rest3').children('.chevron_down').addClass('on')		
			$(this).find('.roll_rest3').children('.chevron_down').removeClass('on')
			$(this).find('.roll_rest3').children('.chevron_up').addClass('on')
			$('.roll_warp > li ul').removeClass('on')
			$(this).find('.roll_title > ul').addClass('on')
			$('.roll_warp > li').attr('data-on','off')
			on_off=$(this).attr('data-on','on')
		}else if(on_off == "on"){
			$('.roll_con').stop().slideUp(400)				
			$('.roll_title .roll_rest3').children('.chevron_up').removeClass('on')		
			$('.roll_title .roll_rest3').children('.chevron_down').addClass('on')		
			$('.roll_warp > li ul').removeClass('on')
			on_off=$(this).attr('data-on','off')
		}
	})
})



	/*page_wrap 가운데 정렬*/
	window.onload=function(){
		var pagebtn_count = parseInt($(".page_wrap a").length)
		$(".page_wrap").css("width", pagebtn_count * 51)

		
	}
	/*page_wrap 가운데 정렬*/







			
