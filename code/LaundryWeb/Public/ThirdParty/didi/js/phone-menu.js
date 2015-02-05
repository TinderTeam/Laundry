(function(){
	$(document).ready(function(){
		
		var eachMenuWidth = 100,
			menuLength = $('.menu_ani_outer a').length;
		var menuWidth = eachMenuWidth * menuLength > $(window).width() ? (eachMenuWidth * menuLength) + 40 : $(window).width();
		$('.menu_ani_outer').css({'width': menuWidth, 'left': ('-' + menuWidth + 'px')});
		
		var touch = function(ele, callback, bubble) {
			if (!bubble) {
				bubble = false;
			}
			if (ele && callback) {
				ele.addEventListener('touchstart', function(ev) {
					ev.target.focus();
					ev.stopPropagation(); //阻止冒泡
				}, bubble);
		
				ele.addEventListener('touchmove', function(ev) {
					ev.target.setAttribute("moved", "true");
				}, bubble);
		
				ele.addEventListener('touchend', function(ev) {
					ev.target.blur();
					if (ev.target.getAttribute("moved") !== "true") {
						callback(ev);
					} else {
						ev.target.setAttribute("moved", "false");
					}
				}, bubble);
			}
		};
		
		
		$('.didi_phone_menu').bind('touchend',function(ev){
			ev.stopPropagation();
		});
		
		$('.btn_show_menu').bind('touchend',function(ev){
			$('#phone_menu').show();
			$('.phone_sec_menu').hide().css({'left': '-140px'});
			$('.menu_ani_outer').show().stop().animate({'left': '0'},300);	
		});
		
		$('.menu_ani_outer').bind('touchend',function(ev){
			ev.stopPropagation();	
		});
		
		$(document).bind('touchend',function(ev){
			$('.phone_sec_menu').stop().animate({'left': '-140px'},300,function(){
				$('#phone_menu').hide();	
			});
			$('.menu_ani_outer').stop().animate({'left': ('-' + menuWidth + 'px')},300,function(){
				$('#phone_menu').hide();
			});	
		});
		
		$('.phone_spe_menu').eq(0).bind('click',function(){
			$('.menu_ani_outer').hide().css({'left': ('-' + menuWidth + 'px')});
			$('.phone_sec_menu').hide();
			$('.phone_sec_menu').eq(0).show().stop().animate({'left': 0 },300);	
		});
		
		$('.phone_spe_menu').eq(1).bind('click',function(){
			$('.menu_ani_outer').hide().css({'left': ('-' + menuWidth + 'px')});
			$('.phone_sec_menu').hide();
			$('.phone_sec_menu').eq(1).show().stop().animate({'left': 0 },300);	
		});
		
	});
})()















