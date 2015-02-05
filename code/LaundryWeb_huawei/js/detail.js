document.write(' 	<script src="../js/main.js"><\/script>	');

var detail;
detail = {
    init: function () {
        detail.resize.init();
        $(".slider_button").click(function () {
			var os=$("#header").css("position")!="fixed"?0:$("#header").height();
            $("html,body").animate({
                scrollTop:$("#container_1").offset().top-os
            },700);
        });
        $("#red_x").hide();
        $("#black_button").click(function(){
            $("#red_x").show();
            $("#black_button").hide();
            $("#main_body").animate({left:"-85%"});
            $("#mobile_slide_nav_box").animate({right:"0%"});
        });
        $("#red_x").click(function(){
            $("#red_x").hide();
            $("#black_button").show();
            $("#main_body").animate({left:"0%"});
            $("#mobile_slide_nav_box").animate({right:"-85%"});
        });
        window.onload = function() {
            var w = $(window).width();
            //$('div.pics').height('600px');
            if(w>767){
                var h = $('img.pic-1.pic').height();
                $('div.pics').height(h);
            }else{
                $('div.pics').height('100%');
            }
        };
        

    },
    resize: {
        init: function () {
            detail.resize.resize();
            $(window).resize(detail.resize.resize);
        },
        resize: function () {
            var w = $(window).width();
            if(w>767){
                var h = $('img.pic-1.pic').height();
                $('div.pics').height(h);
                //$('img.main').attr("src","image/hero_BG.jpg");
                $("#red_x").hide();
                $("#black_button").show();
                $("#main_body").animate({left:"0%"});
                $("#mobile_slide_nav_box").animate({right:"-85%"});
            }else{
                $('div.pics').height('100%');
                //$('img.main').attr("src","image/case_hero_BG_mobile.jpg");
            }

        }
    }
};
$(document).ready(function(){
    detail.init();
});