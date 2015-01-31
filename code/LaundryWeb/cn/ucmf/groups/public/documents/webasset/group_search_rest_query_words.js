
var highlightindex = -1 ;
var timeoutID;
var autoNode;
var ul ;
var searchbox;
var currentbox;
$(function(){
	
	addHtmlAfter("#search_1");
	addHtmlBefore("#search_result_box");
	
	try{
		$.ucm.cgiPath = "/ilink/" + g_ssSourceSiteId + "/<!--$HttpCgiPath-->";
	}catch(e){}
	//search input
	var w_input = $("#search-box-h");
	searchbox=$("#search-box-h");
	var r_textarea =  $("#search_result_box_textarea");
	autoNode = $(".auto");
	ul = $(".auto .List_Box .List_Box_Ul");
	
	var w_flag = false;
	
	w_input.focus(function(){
			r_textarea.val("");
		highlightindex = -1;
		var input = $(this);
		autoNode.hide();
		autoNode = $(".auto");
		ul = $(".auto .List_Box .List_Box_Ul");
	
		if(!w_flag){
			searchwords(input);
			w_flag = true;
		}
	});
	var r_flag = false;
	r_textarea.focus(function(){
		w_input.val("");
		highlightindex = -1;
		autoNode.hide();
		var input = $(this);
		autoNode = $(".auto_bottom");
		ul = $(".auto_bottom .List_Box_bottom .List_Box_Ul_bottom");
		if(!r_flag){
		 searchwords(input);
	        r_flag = true;		
		}
	});	
});
var beforeTime;
function searchAjax(wordText,appNum){
					var time = new Date().getTime()-beforeTime;
					if(time<500){
						return;
					}
		 			var params={"keywords":wordText,"appNum":appNum};
						$.ajax({
  		                type: 'GET',
  		                async:false,
  		                url: "http://www.huawei.com/iwcmmarketing/querywords_getSuggestWords.action",
  		                data:params,
  		                dataType: 'jsonp', 
  		                jsonp: 'scallback', 
  	                    success: function(data) {}
	                 });
}
function searchwords(w_input)
{
   currentbox=w_input;
	w_input.keyup(function(event){
		var myEvent = event || window.event;
		var keyCode = myEvent.keyCode;
		if(keyCode>=65 && keyCode<=90 || keyCode==8 || keyCode==46 ||
		 (keyCode>=48 && keyCode<=57) || (keyCode>=96 && keyCode<=105) || keyCode==32){
		 	beforeTime = new Date().getTime();
		 	var wordText = $(this).val();
		 	
		 	if(wordText != ""){
		 			
					var appNum=$('#APP_NUM').val(); 
						setTimeout("searchAjax(\""+wordText+"\",'"+appNum+"')",500);
		 				//setTimeout("searchAjax('"+wordText+"','"+appNum+"')",500);
		 				/*var params={"keywords":wordText,"appNum":appNum};
						$.ajax({
  		                type: 'GET',
  		                async:false,
  		                url: "http://localhost.huawei.com:9090/iwcmmarketing/querywords_getSuggestWords.action",
  		                //url: "http://dggap007-ts.huawei.com/iwcmmarketing/querywords_getSuggestWords.action",
  		                data:params,
  		                dataType: 'jsonp', 
  		                jsonp: 'scallback', 
  	                    success: function(data) {}
	                 });*/
		 			 
		 	}else{
		 		autoNode.hide();
		 		highlightindex = -1;
		 	}
		 }
	});	  
	
	w_input.keydown(function(event){
		var myEvent = event || window.event;
		var keyCode = myEvent.keyCode;
		 if(keyCode == 38 || keyCode == 40){
		 	if(keyCode == 38){
		 		var lis = ul.children("li");
		 		if(highlightindex != -1){
		 			addStyle(lis.eq(highlightindex),2);
		 			//lis.eq(highlightindex).addStyle(2);
		 			highlightindex--;
		 		}else{
		 			highlightindex = lis.length -1;
		 		}
		 		if(highlightindex == -1){
		 			highlightindex = lis.length -1;
		 		}
		 		addStyle(lis.eq(highlightindex),1);
		 		//lis.eq(highlightindex).addStyle(1);
		 		var currText = ul.children("li").eq(highlightindex).text();
		 		w_input.val(currText);
		 		
		 	}
		 	
		 	if(keyCode == 40){
		     var lis = ul.children("li");
		  		if(highlightindex != -1){
		  			addStyle(lis.eq(highlightindex),2);
		 			//lis.eq(highlightindex).addStyle(2);
		 		}
		 		
		 		highlightindex++;
		 		if(highlightindex == lis.length){
		 			highlightindex  = 0 ;
		 		}
		 		addStyle(lis.eq(highlightindex),1);
		 		//lis.eq(highlightindex).addStyle(1);
		 		var currText = ul.children("li").eq(highlightindex).text();
		 		w_input.val(currText);
		 	}
		 }
	});	
	w_input.blur(function(){
		setTimeout(function(){
			autoNode.hide();
		},250);	
	});		
}

//Prompt Box
function addHtmlAfter(obj){
	
	$(" <div style='display:none' class='auto'><div class='ico'></div><div class='List_Box'><ul class='List_Box_Ul'></ul></div><div class='clear'></div></div> ").insertAfter(obj);	
	//$(obj).parent().parent().append(" <div class='auto'><div class='ico'></div><div class='List_Box'><ul class='List_Box_Ul'></ul></div><div class='clear'></div></div> ");	
}

function addHtmlBefore(obj){
	$(" <div class='auto_bottom'><div class='List_Box_bottom'><ul class='List_Box_Ul_bottom'></ul></div></div>").insertBefore(obj);
}



function removeHtml(obj){
	$(obj).remove();
}
function scallback(dataJson)
{	
		//debugger;
   ul.empty();
    var querywords='';
    $(dataJson).each(function(i,item){
	        querywords=item['querywords'];
	     });
	      if(querywords!=null&&querywords!=""&&querywords!="undefined"){
		    var wordsarray=querywords.toString().split(',');
	          for(var rowIndex = 0 ;rowIndex<wordsarray.length;rowIndex++){
		 					var row = wordsarray[rowIndex];
		 					var li = $("<li>").attr("lid",rowIndex);
		 					li.hover(
		 						function(){
		 							
		 							if(highlightindex != -1){
		 								
		 								addStyle(ul.children("li").eq(highlightindex),2);
		 								//ul.children("li").eq(highlightindex).addStyle(2);
		 							}
		 							highlightindex = $(this).attr("lid");
		 							
		 							//$(this).addStyle(1);
		 							addStyle($(this),1);
		 						},
		 						function(){
		 							
		 							//$(this).addStyle(2);
		 							addStyle($(this),2);
		 						}
		 					);
		 					li.click(function(){
		 					
		 					currentbox.val($(this).text());		 					
		 					//autoNode.hide();
		 					 highlightindex = -1;
		 						});
		 					li.html(row).appendTo(ul);
		 				
		 				}
		              if(wordsarray.length>0){
		              		//alert("show");
		 					autoNode.show();
		 				}else{
		 					//alert("hide1");
		 					autoNode.hide();
		 					highlightindex = -1;
		 				}	
		 	}else{
		 		//alert("hide2");
		 		autoNode.hide();
		 	}			 
}



//change Style
function addStyle(a,n){
	a.parent().children("li").removeClass("liHover");
	switch(n){
		case 1:
			a.removeClass("liOut").addClass("liHover")
			break
		case 2:
			a.removeClass("liHover").addClass("liOut")
			break
		default:
			a.removeClass("liHover").addClass("liOut")		
	}
}
