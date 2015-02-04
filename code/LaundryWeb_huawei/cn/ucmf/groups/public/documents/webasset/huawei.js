var huawei = {

	init : function () {
		huawei.hero();
		huawei.click();
		this.productPageInit();
		this.productLeftNav();
		//		this.productZoom();
		this.productZoomLeftRright();
		this.productFeaturesVideoLeftRright();
		//huawei.needHelp();
		//$('#videos_carousel').carousel('pause');
	},

	click : function () {

		$(".carousel-indicators li span").click(function () {
			$(this).parent().trigger("click");
		});

		//need help
		$("#need_help_desktop").click(function (e) {
			e.stopPropagation();
		});
		$("#need_help_desktop .tab_help_open").click(function (e) {
			e.stopPropagation();
			if ($(window).width() <= 768) {
				$("#need_help_popup").css('top', '132px').show();
			} else {
				if ($(this).hasClass('close')) {
					$("#need_help_desktop").animate({
						'right' : -250
					});
					$("#need_help_desktop #need_help_popup,#need_help_desktop .tab_help_open").animate({
						'height' : 182
					}, function () {
						$('#need_help_desktop .tab_help_open').removeClass('close');
					});
				} else {
					$("#need_help_desktop").animate({
						'right' : 0
					});
					$("#need_help_desktop #need_help_popup,#need_help_desktop .tab_help_open").animate({
						'height' : 300
					}, function () {
						$('#need_help_desktop .tab_help_open').addClass('close');
					});
				}
			}
		});

		$("body").click(function () {
			$("#need_help_desktop").animate({
				'right' : -250
			});
			$("#need_help_desktop #need_help_popup,#need_help_desktop .tab_help_open").animate({
				'height' : 182
			});
			$('#need_help_desktop .tab_help_open').removeClass('close');
		});

		$(document).on("click ", "#nav_mobile", function (e) {
			//e.stopPropagation();
			var $container = $("#container");
			if ($container.css('left') == '0px') {
				huawei.navMenuMobile.open();
			} else {
				huawei.navMenuMobile.close();
			}
		}).on("click", "body", function (e) {
			//huawei.navMenuMobile.close();
		}).on("click", "#tab_main_nav_mobile_container", function (e) {
			//e.stopPropagation();
		})
		//mobile
		.on("click", "#footer_nav_mobile_back_to_top", function (e) {
			window.scrollTo(0, 0);
		});

	},

	resize : function () {
		var $windowWidth = $(window).width();
		if ($windowWidth > 768) {
			huawei.navMenuMobile.close();
		}
		huawei.hero();
	},

	hero : function () {
		if (!$("#breadcrumb_nav").length)
			return;
		if ($(window).width() > 768) {
			var left = $("#breadcrumb_nav").position().left;
		} else {
			var left = 0;
		}

		$(".carousel-caption").css('left', left + 'px');

	},

	/* needHelp: function(){

	$("#need_help_desktop img").mouseover(function(){
	$(this).css('margin-left', '-7px');
	}).mouseout(function(){
	$(this).css('margin-left', '0px');
	});

	},*/

	navMenuMobile : {

		open : function () {
			//$("#container").css('left', '-100%');
			$("#container").addClass("mobile-menu-open");
			$('#tab_main_nav_close_mobile').addClass("show-table-cell").show().siblings('#tab_main_nav_mobile,#tab_main_need_help_mobile').addClass("hidden").hide();
			// $("#tab_main_nav_mobile_container").css({
			// 'right' : 0
			// });
			$("#tab_main_nav_mobile_container").addClass("mobile-menu-open");
		},

		close : function () {
			$("#tab_main_nav_mobile_next_container").removeClass("mobile-menu-open")
			.animate({
				'right' : -100 + '%'
			}, function () {
				$("#tab_main_nav_mobile_container").removeClass("mobile-menu-open").css({
					'right' : -100 + '%'
				});
				$("#container").removeClass("mobile-menu-open").css('left', 0);
				$('#tab_main_nav_close_mobile').removeClass("show-table-cell").hide().siblings().removeClass("hidden").show();
			});

		}

	},

	productPageInit : function () {
		/*$(document).on("click", "#js_read_more_product a", function (e) {
		if($(".js_product-overview-details-more").is(":hidden")){$(this).addClass("on").find("i").addClass("down").end().find("span").text("COLLAPSE");}
		else {$(this).removeClass("on").find("i").removeClass("down").end().find("span").text("EXPAND");}
		$(".js_product-overview-details-more").slideToggle();
		return false;
		});*/
		$(window).on("scroll", function (e) {
			var top = $(document).scrollTop();
			if (top >= 500 && $("#product_left_nav").is(":hidden")) {
				$("#product_left_nav").fadeIn();
			}
			var section_list = [];
			$("#product_left_nav li a").each(function (index, element) {
				section_list.push($(this));
			});

			section_list = section_list.reverse();
			for (var i = 0; i < section_list.length; i++) {
				if (top >= $(section_list[i].attr("data-section-selector")).offset().top - 10) {
					section_list[i].addClass("active").parent().siblings().find("a").removeClass("active");
					return;
				}
			}

		});

		// table
		$(".product_specifications .table tr:odd").addClass("tr-even-bg");
	},

	productZoom : function () {
		$('.jqzoom').jqzoom({
			zoomType : 'standard',
			lens : true,
			preloadImages : false,
			alwaysOn : false,
			zoomWidth : 360,
			zoomHeight : 360,
			xOffset : 30,
			yOffset : 0,
			position : 'left'
		});

	},
	productZoomLeftRright : function () {
		$(".thumblist").width($("#thumblist a:first").parent().outerWidth(true) * 4);
		$(".prev-photo").find("a").addClass("disabled");
		if ($("#thumblist a").length <= 4) {
			$(".product_pic_thumb_list .next-photo, .product_pic_thumb_list .prev-photo").hide().find("a").addClass("disabled");
		}
		var currentMoveIndex = 0;
		$(document).on("vchange", "#thumblist a", function (e) {
			var $this = $(this);
			var i = $this.parent().index();
			var $margin_div = $(this).parents("#thumblist");
			var offset_left = -4 * ((currentMoveIndex) * parseInt($(this).parent().outerWidth(true)));
			$margin_div.stop().animate({
				"margin-left" : offset_left
			}, 500);
			if (currentMoveIndex <= 0) {
				$(".product_pic_thumb_list .prev-photo a").addClass("disabled");
				$(".product_pic_thumb_list .next-photo a").removeClass("disabled");
			} else {
				$(".product_pic_thumb_list .prev-photo a").removeClass("disabled");
			}
			if (currentMoveIndex >= Math.ceil($("#thumblist a").length / 4) - 1) {
				$(".product_pic_thumb_list .next-photo a").addClass("disabled");
				$(".product_pic_thumb_list .prev-photo a").removeClass("disabled");
			} else {
				$(".product_pic_thumb_list .next-photo a").removeClass("disabled");
			}
		})
		.on("click", ".product_pic_thumb_list .next-photo a", function (e) {
			e.preventDefault();
			if (currentMoveIndex >= Math.ceil($("#thumblist a").length / 4) - 1) {
				return false;
			}
			++currentMoveIndex;
			$("#thumblist a").eq(currentMoveIndex).trigger("vchange");
			return false;
		})
		.on("click", ".product_pic_thumb_list .prev-photo a", function (e) {
			e.preventDefault();
			if (currentMoveIndex <= 0) {
				return false;
			}
			--currentMoveIndex;
			$("#thumblist a").eq(currentMoveIndex).trigger("vchange");
			return false;
		});

	},

	productFeaturesVideoLeftRright : function () {
		$(".product-video-other").width($(".product-video-other li:first").outerWidth(true) * 3);
		if ($(".product-video-other a").length <= 3) {
			$("#js-product_features_video_list .next-photo, #js-product_features_video_list .prev-photo").hide();
		}

		var currentMoveIndex = 0;
		$(document).on("vchange", ".product-video-other a", function (e) {
			var $this = $(this);
			var i = $this.parent().parent().index();
			var $margin_div = $(this).parents("ul").first();
			var offset_left = -3 * ((currentMoveIndex) * parseInt($(this).parent().parent().outerWidth(true)));
			$margin_div.stop().animate({
				"margin-left" : offset_left
			}, 500);
			if (currentMoveIndex <= 0) {
				$("#js-product_features_video_list .prev-photo a").addClass("disabled");
				$("#js-product_features_video_list .next-photo a").removeClass("disabled");
			} else {
				$("#js-product_features_video_list .prev-photo a").removeClass("disabled");
			}
			if (currentMoveIndex >= Math.ceil($(".product-video-other a").length / 3) - 1) {
				$("#js-product_features_video_list .next-photo a").addClass("disabled");
				$("#js-product_features_video_list .prev-photo a").removeClass("disabled");
			} else {
				$(".product_pic_thumb_list .next-photo").removeClass("disabled");
			}

		})
		.on("click", "#js-product_features_video_list .next-photo a", function (e) {
			e.preventDefault();
			if (currentMoveIndex >= Math.ceil($(".product-video-other a").length / 3) - 1)
				return false;

			++currentMoveIndex;
			i = currentMoveIndex;
			$(".product-video-other a").eq(i).trigger("vchange");
			return false;
		})
		.on("click", "#js-product_features_video_list .prev-photo a", function (e) {
			e.preventDefault();
			if (currentMoveIndex <= 0)
				return false;
			--currentMoveIndex;
			i = currentMoveIndex;
			$(".product-video-other a").eq(i).trigger("vchange");
			return false;
		});

	},
	productLeftNav : function () {
		var section_list = [];
		$("#product_left_nav li a").each(function (index, element) {
			section_list.push($(this).attr("data-section-selector"));
		});
		section_list = section_list.reverse();
		$("#product_left_nav li a").click(function (e) {
			$('html, body').stop().animate({
				scrollTop : $($(this).attr("data-section-selector")).offset().top
			}, 700);
			$(this).parent().addClass("current").siblings().removeClass("current");
			return false;
		});
	}
};

$(document).ready(function () {
	huawei.init();
});

$(window).resize(function () {

	huawei.resize();

});
//nav demo
// bof
(function ($) {

	$(function ($) {
		$(document).on("click", ".quick-links a", function (e) {
			$('html, body').stop().animate({
				scrollTop : $($(this).attr("data-section-selector")).offset().top
			}, 700);
			return false;
		})
		.on("click", ".js-prev-news", function (e) {
			var i = $(".js-news-box > ul").data("i");
			if (!i)
				i = 0;
			if (i <= 0)
				i = $(".js-news-box > ul li").length;
			i--;

			$(".js-news-box > ul").animate({
				"margin-top" : -i * $(".js-news-box > ul > li").first().height()
			}, 500);
			$(".js-news-box > ul").data("i", i);
			return false;
		})
		.on("click", ".js-next-news", function (e) {
			var i = $(".js-news-box > ul").data("i");
			if (!i)
				i = 0;
			i++;
			if (i >= $(".js-news-box > ul li").length)
				i = 0;
			$(".js-news-box > ul").animate({
				"margin-top" : -i * $(".js-news-box > ul > li").first().height()
			}, 500);
			$(".js-news-box > ul").data("i", i);
			return false;
		})
		.on("click", ".js-index-play-btn", function (e) {
			$("html, body").animate({
				scrollTop : $(".index-video").offset().top - 10
			}, 1000);

			$(".index-video-box .title").addClass("bg-alpha");
			return false;
		});

	});

})(jQuery);
// eof

// player

// bof
(function ($) {
	$(function ($) {
		// bof dom ready


		var playerInstance = null;

		function pauseVideo(e) {
			if (playerInstance && playerInstance.getState() == "PLAYING")
				playerInstance.pause();
		}

		$(document).on("click", ".js_video_player, .js-play-btn", function (e) {
			var playerid = $(this).attr("data-player-id") || 'playerContainer';
			if ($(this).attr("data-play-nopop")) {
				//$(this).next("img").fadeOut();
				playerInstance = initPlayer(playerid, $(this).attr("data-video-path"), $(this).attr("data-img-path"), true);

				setTimeout(function () {
					if (playerInstance && playerInstance.getState() != "PLAYING")
						playerInstance.play();
				}, 1000);
				$(this).hide();
				return false;
			}

			if (!$("#player_wrapper").length)
				$('<div id="player_wrapper"></div>').appendTo("body");
			$("#play_video_fancyboxTag").attr("href", "#player_wrapper").trigger("click");
			$("#player_wrapper").empty();
			$('<div id="' + playerid + '"/>').appendTo("#player_wrapper");
			//$("#player_wrapper").show();
			if (playerInstance)
				playerInstance.destroyPlayer();
			playerInstance = initPlayer(playerid, $(this).attr("data-video-path"), $(this).attr("data-img-path"), true);
			playerInstance.onFullscreen = function (e) {
				console.log(e); //alert(e);
			};

			setTimeout(function () {
				if (playerInstance && playerInstance.getState() != "PLAYING")
					playerInstance.play();
			}, 1000);

			return false;
		});

		window.initPlayer = function (playerid, videoPath, imgSrc, autoplay) {
			//if(jwplayer(playerid))return;
			autoplay = autoplay || false;
			var video_width = '100%';
			var video_height = '100%';
			//var videoPath=$("#"+playerid).attr("data-video-path");
			//var imgSrc=$("#"+playerid).attr("data-img-path");
			var skinSrc = g_HttpRelativeWebRoot + 'groups/public/documents/webasset/carbon.xml';
			var swfPlayer = g_HttpRelativeWebRoot + 'groups/public/documents/webasset/player_new.swf';
			return jwplayer(playerid).setup({
				//stretching : 'exactfit',
				skin : skinSrc,
				aspectratio : "16:9",
				width : video_width,
				height : video_height,
				image : imgSrc,
				file : videoPath,
				flashplayer : swfPlayer,
				autostart : autoplay,
				ga : {}
			});
		};

		// eof dom ready
	});
})(jQuery);
// eof
