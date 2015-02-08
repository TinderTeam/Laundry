

(function(window) {
        var mobileUAKeywords = ["nokia", "sony", "ericsson", "mot", "samsung", "htc", "sgh", "lg", "sharp", "sie-",
                    "philips", "panasonic", "alcatel", "lenovo", "iphone", "ipod", "blackberry", "meizu", 
                    "android", "netfront", "symbian", "ucweb", "windowsce", "palm", "operamini", 
                    "operamobi", "opera mobi", "openwave", "nexusone", "cldc", "midp", "wap", "mobile"
                ];
        var userAgent = navigator.userAgent;
        var regExp = new RegExp(mobileUAKeywords.join("|"), "i");
        var browser = {
            mobile: !!userAgent.match(regExp),
            android: /android/i.test(userAgent) || /linux/i.test(userAgent),
            iPad: /iPad/i.test(userAgent),
            isWechatBrowser: /micromessenger/i.test(userAgent)
        };
        
        window.browser = browser;
        
        if (!!userAgent.match(regExp) && !browser.iPad) {
            var styleLink = document.createElement("link");
            styleLink.setAttribute("rel", "stylesheet");
            styleLink.setAttribute("href", "/css/secret/download-responsive.css");
            document.getElementsByTagName("head")[0].appendChild(styleLink);
        }
    })(window);

$(function(){
		//var browser = window.browser;
		if (browser.isWechatBrowser) {
			$('head').append('<style>.browser-tips{position:fixed;top:0;left:0;width:100%}.browser-tips-content{position:relative;z-index:1;margin:0 10px;padding:5px;background-color:#fff;border-radius:0 0 5px 5px}.browser-tips-content label{position:relative;z-index:1;padding:0 6px;line-height:42px;background-color:#fff;font-size:15px}.browser-tips-content .line-with-bg{display:block;background-color:#d7eaf6;border-radius:2px}.browser-tips-content .tips-arrow{position:absolute;top:0;right:20px;width:30px;height:30px;background:url("images/wechat_tips_arrow_v2.png") no-repeat right top;background-size:30px 30px}.browser-tips-content .font-attention{color:#00aa1f}.browser-tips-mask{position:fixed;top:0;left:0;width:100%;height:100%;background-color:rgba(36,39,42,.8)}</style>');
			var $downloadBtns = $('.btn_download');
            $downloadBtns.click(function(e) {
                e.preventDefault();
                
                var $browserTips = $("<div></div>")
                        .attr("id", "browserTips")
                        .addClass("browser-tips")
                        .html("<div class='browser-tips-content'>"
                                        +   "<label>微信内无法下载，请点击<span class='font-attention'>右上角</span>按钮</label>"
                                        +   "<label class='line-with-bg'>选择<span class='font-attention'>「在浏览器中打开」</span>即可正常下载</label>"
                                        +   "<div class='tips-arrow'></div>"
                                        + "</div>"
                                        + "<div class='browser-tips-mask'></div>");
                
                $("body").append($browserTips);
                
                $browserTips.find(".browser-tips-mask").click(function() {
                    $browserTips.remove();
                });
            });
	    
    	    document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
                    WeixinJSBridge.call('showOptionMenu');
                });
    	}
});