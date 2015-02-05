  
var flash_params = {
    wmode: 'opaque',
    menu: false,
    allowFullscreen: "true",
    scale: 'Scale'
};
var flashvars = {};

var lang = window.location.href.match(/\.com\/([a-z]{2})\//);
if (lang && lang.length && lang.length > 1 && lang[1] != '') 
    lang = lang[1];
else 
    lang = 'en';

jQuery.fn.center = function(){
//alert(this.className);
    var top = ($(window).height() - this.height()) / 2 + $(window).scrollTop();
    var left = ($(window).width() - this.width()) / 2 + $(window).scrollLeft();
   // alert("$(window).height()=="+$(window).height()+"/"+"this.height()="+this.height()+"$(window).scrollTop()="+ $(window).scrollTop());

//alert("window.width:"+$(window).width()+"------------this.width:"+this.width()+"--------------window.scrollLeft:"+$(window).scrollLeft());
    this.css("position", "absolute");
    this.css("top", top + "px");
    this.css("left", left + "px");
    return this;
};
var lightbox = {};
lightbox.status = 0;
lightbox.contentId = false;
lightbox.selector = 'a[rel=lightbox]';
lightbox.width = 706;
lightbox.height = 560;
lightbox.ini = function(){
	
	
    $(lightbox.selector).click(function(e){
    
        e.preventDefault();
        
        lightbox.status = 1;
        lightbox.overlay();
        var hrefObj = this.href.substring(this.href.indexOf('#') + 1);
        var srcArray = hrefObj.split('&');
        var srcPath = srcArray[0];
        lightbox.width = srcArray[1];
        lightbox.height = srcArray[2];
        
        if (lightbox.width == null && lightbox.width == null) {
           
            var video_width = 640;
            var video_height = 400;
        }
        else {
            var video_width = lightbox.width;
            var video_height = lightbox.height;
            
        }
        
        // launch flv player
        if (this.href.indexOf('.mp4') > -1 || this.href.indexOf('.flv') > -1) {
            $('<div id="lightbox" />').appendTo('body').css({
                'width': lightbox.width,
                'height': lightbox.height,
                'z-index': 99
            }).append('<div class="videoHead"><div class="Close"></div></div>').center();
            $('#lightbox .Close').click(lightbox.close);
            
            $('#lightbox').append('<div class="video"><div id="lightbox_flash"/></div>');
           
            if (iPx()) {
          
                //alert("<video width='"+video_width+"' height='"+video_height+"' controls='controls'><source type='video/mp4' src='"+srcPath+"'></source></video>");
                
                $('#lightbox_flash').append(["<video width='", video_width, "' height='", video_height, "' controls='controls'><source type='video/mp4' src='", srcPath, "'></source></video>"].join(""));
                
            }
            else {
            ///
                $('#lightbox .heading div').removeClass("title");
                
                $('#lightbox').css({
                    'width': parseInt(video_width) + 10,
                    'height': parseInt(video_height) + 50
                }).find('.heading').css('width', video_width);
                
                /*var flashvars = {
                    srcPath: srcPath,
                    downloadPath: "",
                    imgPath: "",
                    videoW: video_width,
                    videoH: video_height,
                    autoLoop: "false",
                    controlBarAutoHide: "false",
                    autoPlay: "true"
                };
                var flash_params = {
                    menu: "false",
                    scale: "Scale",
                    allowFullscreen: "true",
                    allowScriptAccess: "always",
                    bgcolor: "#FFFFFF"
                };
                */
                //alert(video_width); 
                var skinSrc=g_HttpRelativeWebRoot+'groups/public/documents/webasset/carbon.xml'
				var swfSrc=g_HttpRelativeWebRoot+'groups/public/documents/webasset/player.swf'
				var currentURL=window.location.href;				
				jwplayer("lightbox_flash").setup({
						'stretching': 'exactfit',
						'skin':skinSrc,
						'width': video_width,
   						'height': video_height,
   						'file': srcPath,
   						'flashplayer': swfSrc,
						ga: {}
 				});
            };
            
            $(window).resize(lightbox.relocate);
            
        }
        //add by kevin 03-22-11(start)
        else 
            if (this.href.indexOf('.swf') > -1) {
                $('<div id="lightbox" />').appendTo('body').css({
                    'width': lightbox.width,
                    'height': lightbox.height,
                    'z-index': 99
                }).append('<div class="videoHead"><div class="Close"></div></div>').center();
                
                $('#lightbox .Close').click(lightbox.close);
                
                $('#lightbox').append('<div class="video"><div id="lightbox_flash"/></div>');
                
                if (iPx()) {
                
                    window.location = srcPath;
                    lightbox.close();
                    
                }
                else {
                    $('#lightbox .heading div').removeClass("title");
                    $('#lightbox').css({
                        'width': parseInt(video_width) + 10,
                        'height': parseInt(video_height) + 50
                    }).find('.heading').css('width', video_width);
                    
                    var flashvars = {};
                    var flash_params = {
                        menu: "false",
                        scale: "Scale",
                        allowFullscreen: "true",
                        allowScriptAccess: "always",
                        bgcolor: "#FFFFFF"
                    };
                     
        			
  		 			 swfobject.embedSWF(srcPath, 'lightbox_flash', video_width, video_height, '9.0.0', "expressInstall.swf", flashvars, flash_params);
                    
                }
                
            }
            //add by kevin 03-22-11(end)
            
            
            else if(this.href.indexOf('#subscribe-confirm') > -1) {
            
                var id = this.href.substring(this.href.indexOf('#') + 1);
                lightbox.contentId = id;
                var $content = $('#' + id);
                
                $('<div id="lightbox" />').appendTo('body').css({
                    'width': '683px',
                    'height': 'auto',
                    'z-index': 99
                });
                
                $content.appendTo('#lightbox').show();
                $('#lightbox').center();
                $(window).unbind('scroll').resize(lightbox.relocate);
            }
            else if(this.href.indexOf('#SubscribeMag') > -1) {
            
                var id = this.href.substring(this.href.indexOf('#') + 1);
                lightbox.contentId = id;
                var $content = $('#' + id);
                
                $('<div id="lightbox" />').appendTo('body').css({
                    'width': '635px',
                    'height': 'auto',
                    'z-index': 99
                });
                
                $content.appendTo('#lightbox').show();
                $('#lightbox').center();
                $(window).unbind('scroll').resize(lightbox.relocate);
            }
            else if(this.href.indexOf('#Subscribe') > -1) {
            
                var id = this.href.substring(this.href.indexOf('#') + 1);
                lightbox.contentId = id;
                var $content = $('#' + id);
                
                $('<div id="lightbox" />').appendTo('body').css({
                    'width': '811px',
                    'height': 'auto',
                    'z-index': 99
                });
                
                $content.appendTo('#lightbox').show();
                $('#lightbox').center();
                $(window).unbind('scroll').resize(lightbox.relocate);
            }
            else if(this.href.indexOf('#feedback-confirm') > -1) {
            
                var id = this.href.substring(this.href.indexOf('#') + 1);
                lightbox.contentId = id;
                var $content = $('#' + id);
                
                $('<div id="lightbox" />').appendTo('body').css({
                    'width': '635px',
                    'height': 'auto',
                    'z-index': 99
                });
                
                $content.appendTo('#lightbox').show();
                $('#lightbox').center();
                $(window).unbind('scroll').resize(lightbox.relocate);
            }
            else if(this.href.indexOf('#study-area-popup') > -1) {
            
                var id = this.href.substring(this.href.indexOf('#') + 1);
                lightbox.contentId = id;
                var $content = $('#' + id);
                
                $('<div id="lightbox" />').appendTo('body').css({
                    'width': $content.width(),
                    'height': 'auto',
                    'z-index': 99
                });
                
                $content.appendTo('#lightbox').show();
                $('#lightbox').center();
                $(window).unbind('scroll').resize(lightbox.relocate);
            }
            //add by kevin 03-22-11(end)
            else {
                $('<div id="lightbox" />').appendTo('body').css({
                    'width': lightbox.width,
                    'height': lightbox.height,
                    'z-index': 99
                }).append('<div class="heading"><div class="rightBg"><div class="title"></div><div class="close"><div class="CloseIcon"></div></div></div></div>').center();
                
                $('#lightbox .close').click(lightbox.close);
                
                
                if (this.title) 
                    $('#lightbox .title').html(this.title);
                
                $('#lightbox').append('<div class="container" id="lightbox_content" /><div class="bottom"/>');
                
                if (this.href.indexOf('#') > -1) {
                
                    var id = this.href.substring(this.href.indexOf('#') + 1);
                    lightbox.contentId = id;
                    
                    var $content = $('#' + id);
                    if ($content.height() > 410) {
                    
                       $('#lightbox').width($content.width() + 4).height(460).center();
                       $content.children(".content").height(388);
                        
                    }
                    else {
   
                        $('#lightbox').width($content.width() + 4).height($content.height() + 50).center();
                        
                    }
                    /*Added by Abdallah 5.12.2011*/
                       if(id == "Subscribe")
                       		$content.children(".content").height(432);
                    $content.appendTo('#lightbox_content').show();
                    $(window).unbind('scroll').resize(lightbox.relocate);
                    
                };
                
                            }
        $(this).blur(); 
    });
    
    $(lightbox.worldwide).click(function(e){
        e.preventDefault();
        
        lightbox.status = 1;
        lightbox.overlay();
        var hrefObj = this.href.substring(this.href.indexOf('#') + 1);
        var srcArray = hrefObj.split('&');
        var srcPath = srcArray[0];
         lightbox.width = srcArray[1];
         lightbox.height = srcArray[2];

        $('<div id="lightbox" />').appendTo('body').css({
            'width': lightbox.width,
            'height': lightbox.height,
            'z-index': 99
        }).append('<div class="videoHead"><div class="Close"></div></div>').center();
        
        $('#lightbox .Close').click(lightbox.close);
        $('#lightbox').append('<div class="video"><div id="lightbox_flash"><div style="text-align:center;"><img src=g_HttpRelativeWebRoot + "groups/public/documents/webasset/hw_076709.gif" /></div></div></div>');
        $('#lightbox .heading div').removeClass("title");
        $('#lightbox').css({
            'width': parseInt(lightbox.width) + 10,
            'height': parseInt(lightbox.height) + 50
        }).find('.heading').css('width', lightbox.width);
        var flashvars = {};
        var flash_params = {
            menu: "false",
            scale: "noScale",
            allowFullscreen: "true",
            allowScriptAccess: "always",
            bgcolor: "#FFFFFF"
        };

  		 swfobject.embedSWF(srcPath, 'lightbox_flash', lightbox.width, lightbox.height, '9.0.0', "expressInstall.swf", flashvars, flash_params);

        $(window).unbind('scroll').resize(lightbox.relocate);
    });
    
};

lightbox.open = function(id, title){

    lightbox.status = 1;
    lightbox.overlay();
    
    $('<div id="lightbox" />').appendTo('body').css({
        'width': lightbox.width,
        'height': lightbox.height,
        'z-index': 99
    }).append('<div class="heading"><div class="title"></div><div class="close">Close X</div></div>').center();
    
    $('#lightbox .close').click(lightbox.close);
    
    $('#lightbox .title').html(title);
    
    $lightbox = $('#lightbox').append('<div id="lightbox_content" />');
    
    lightbox.contentId = id;
    
    var $content = $('#' + id);
    
    $lightbox.width($content.width()).height($content.height() + 50).center();
    
    if (parseInt($lightbox.css('top')) < 0) 
        $lightbox.css('top', '0px');
    
    $content.appendTo('#lightbox_content').show();
    
    if (id == 'inquiry_popup') {
        $(window).scroll(lightbox.relocateShift).resize(lightbox.relocateShift);
        lightbox.relocateShift();
    }
    else {
        $(window).unbind('scroll').resize(lightbox.relocate);
    };
    
    }


lightbox.relocate = function(){
    if (lightbox.status == 1) {
        lightbox.overlay();
        var $lightbox = $('#lightbox').center();
        if (parseInt($lightbox.css('top')) < 0) 
            $lightbox.css('top', '0px');
    };
    }

lightbox.relocateShift = function(){
    if (lightbox.status == 1) {
        lightbox.overlay();
        if (iPx()) 
            var top = $(window).scrollTop() + 30;
        else 
            var top = $(window).scrollTop() + 100;
        var $lightbox = $('#lightbox').center().css('top', top);
        if (parseInt($lightbox.css('top')) < 0) 
            $lightbox.css('top', '0px');
    };
    }

lightbox.overlay = function(){

    var w_width = $(document).width();
    var w_height = $(document).height();
    
    if ($.browser.msie && parseInt($.browser.version) == 6) 
        w_width = w_width - scrollbarWidth() - 8;
    
    $('#overlay').remove();
    $('<div id="overlay" />').appendTo('body').css({
        'width': w_width,
        'height': w_height
    }).click(lightbox.close);
    
    // IE6 select box fix
    if ($.browser.msie && parseInt($.browser.version) <= 6) {
        $('select').css('visibility', 'hidden');
        $('.popup select').css('visibility', 'visible');
    };
    
    }

lightbox.close = function(){

    if (lightbox.contentId != false) {
        $('#' + lightbox.contentId).hide().appendTo('body');
        lightbox.contentId = false;
    };
    
    
    if(jwplayer("lightbox_flash").id != null){ 
  		jwplayer("lightbox_flash").stop();
  		$('#overlay').remove();
   		$('#lightbox').hide();
   		setTimeout(function(){
  		$('#lightbox').remove();
    },500);
   	}else{
    	$('#overlay, #lightbox').remove();
    }
       
    // IE6 select box fix
    if ($.browser.msie && parseInt($.browser.version) <= 6) 
        $('select').css('visibility', 'visible');
    
    lightbox.status = 0;
    
}

function replaceSwfWithEmptyDiv(targetID){
    var el = document.getElementById(targetID);
    if (el) {
        var div = document.createElement("div");
        el.parentNode.insertBefore(div, el);
        swfobject.removeSWF(targetID);
        div.setAttribute("id", targetID);
    };
    }

function scrollbarWidth(){
    var div = $('<div style="width:50px;height:50px;overflow:hidden;position:absolute;top:-200px;left:-200px;"><div style="height:100px;"/></div>');
    // Append our div, do our calculation and then remove it
    $('body').append(div);
    var w1 = $('div', div).innerWidth();
    div.css('overflow-y', 'scroll');
    var w2 = $('div', div).innerWidth();
    $(div).remove();
    return (w1 - w2);
}

function getParameterByName(name){
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regexS = "[\\?&]" + name + "=([^&#]*)";
    var regex = new RegExp(regexS);
    var results = regex.exec(l);
    if (results == null) 
        return "";
    else 
        return decodeURIComponent(results[1].replace(/\+/g, " "));
}

function iPx(){
    if ((navigator.userAgent.match(/iPhone/i)) || (navigator.userAgent.match(/iPod/i)) || (navigator.userAgent.match(/iPad/i))) 
        return true;
    return false;
}

 
$(document).ready(function(){
 	$("img").removeAttr("alt"); 
}); 

var flag = false;
function lightbox_worldwide(obj){
flag = true;

        lightbox.status = 1;
        lightbox.overlay();
        
        var hrefObj = g_HttpRelativeWebRoot+'groups/public/documents/webasset/hw_047292.swf&590&310';
        var srcArray = hrefObj.split('&');
        var srcPath = srcArray[0];
         lightbox.width = srcArray[1];
         lightbox.height = srcArray[2];
 
        $('<div id="lightbox" />').appendTo('body').css({
            'width': lightbox.width,
            'height': lightbox.height,
            'z-index': 99
        }).append('<div class="videoHead"><div class="Close"></div></div>').center();
        
        $('#lightbox .Close').click(lightbox.close);
        $('#lightbox').append('<div class="video"><div id="lightbox_flash"><div style="text-align:center;"><img src=g_HttpRelativeWebRoot + "groups/public/documents/webasset/hw_076709.gif" /></div></div></div>');
        $('#lightbox .heading div').removeClass("title");
        $('#lightbox').css({
            'width': parseInt(lightbox.width) + 10,
            'height': parseInt(lightbox.height) + 50
        }).find('.heading').css('width', lightbox.width);
        var flashvars = {};
        var flash_params = {
            menu: "false",
            scale: "noScale",
            allowFullscreen: "true",
            allowScriptAccess: "always",
            bgcolor: "#FFFFFF"
        };

  		 swfobject.embedSWF(srcPath, 'lightbox_flash', lightbox.width, lightbox.height, '9.0.0', "expressInstall.swf", flashvars, flash_params);

        $(window).unbind('scroll').resize(lightbox.relocate);
}
