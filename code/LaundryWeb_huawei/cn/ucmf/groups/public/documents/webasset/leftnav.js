/////////////////////////////////////////////////////////////////////////////
// Copyright HKMCI . All rights reserved.
// Create Date : 2011-1-28
// Last Modify Date: 2011-4-27
// Author : Kevin Leng
/////////////////////////////////////////////////////////////////////////////
//alert("kevin");
var preLevel = 2;
var levelDepth=0;

var divs = "";

function leftNav(strStartLevel, strNumLevels)
{
	//alert("kevin");
	this.m_StartLevel = 3;
	this.m_NumLevels  = 20;
	this.m_EndLevel   = 20;
	this.m_NavPath    = g_navNode_Path;
	leftNav.prototype.Display = leftNav_Display;
	leftNav.prototype.DisplayNode = leftNav_DisplayNode;
		
	
	if (strStartLevel != '')
	{
		var value = parseInt(strStartLevel);
		if (value != NaN)
			this.m_StartLevel = value;
	}
	
	if (strNumLevels != '')
	{
		var value = parseInt(strNumLevels);
		if (value != NaN)
			this.m_NumLevels = value;
	}

	this.m_EndLevel = this.m_StartLevel + this.m_NumLevels - 1 ;
}

function leftNav_Display (node)
{
	document.write("<div class='leftNav block' id='1001'><div class='top' id='1002'></div><div class='content'  id='1003'>");
	this.DisplayNode(node,-1);
	document.write("</div><div class='bottom' id='1004'></div></div>");
	var isLastSelect=true;
	var dbName= document.getElementsByName("nidep-"+levelDepth);
	for(var i=0;i<dbName.length;i++) 
	{
		if(dbName[i].className=="active")
		{
			dbName[i].className = "activeParent";
			isLastSelect=false;
		}
	}
	if(isLastSelect==true)
	{
		var dbName= document.getElementsByName("nidep-"+(levelDepth-1));
		for(var i=0;i<dbName.length;i++)
		{
			if(dbName[i].className=="active")
			{
				dbName[i].className = "activeParent";
			}
		}
	}
	if(isLastSelect==true && node.m_subNodes.length>0){
	//alert("test by kevin");
	
		var dbName= document.getElementsByName("nidep-"+(levelDepth-1));
		for(var i=0;i<dbName.length;i++)
		{
			if(dbName[i].className=="activeParent")
			{
				dbName[i].className = "activeSub";
			}
		}
	
	}
	
}

function leftNav_DisplayNode(node,last)
{

	var bSelected = false;
	
	var nodeClass ="";//this.m_ClassName

	var nodeLevel = node.m_level;
	
	var lastCount = last;
	
	var nodeName="";
	//if (nodeLevel > 6)
	//	nodeLevel = 6;
	if(nodeLevel > levelDepth)
	{
		levelDepth = nodeLevel;
	}
	
	if (this.m_NavPath.length > 0 && node.m_level < this.m_NavPath.length)
	{
		if (this.m_NavPath[node.m_level] == node.m_id)
		{
			if (node.m_level > 0 || (node.m_level == 0 && this.m_NavPath.length == 1))
			{
				//alert(node.m_level+"   "+this.m_NavPath.length);
				bSelected = true;
				//nodeColor = this.m_FocusColor;
				//nodeClass += '-focus';
				nodeClass="active";
			}
		}
	}

	if (nodeLevel > 0)
		//nodeClass += '-' + nodeLevel+" "+node.m_level+"   "+this.m_NavPath.length+" "+this.m_NavPath[node.m_level]+" "+node.m_id;
		nodeName += 'nidep-' + nodeLevel;
/*	if(nodeLevel>2 && lastCount == 0){
			nodeClass="last";
		}*/
	if ( (node.m_level == 0 && this.m_ShowHome) || 
     	 (node.m_level >= this.m_StartLevel && node.m_level <= this.m_EndLevel)
	   )
	{
		var ds = new Array();
		var di = 0;
		

		
		if((nodeLevel-preLevel)==1 ){
			document.write("<div class='children' id='1005'>");
		}
/**
		if(nodeLevel>2 && lastCount == 0){
			document.write("<div class='last' id='1006'>");
		}
**/
		//alert(this.m_NavPath[node.m_level]);
		//this.m_NavPath.length
		
		//link to newsletter
/*  
		if(node.m_label=='Assurance'){
			//alert("tbk");
			ds[di++] = '<a href="/en/services/index.htm?tag=tab2"';
			//ds[di++] = '<a href="http://www-uat.huawei.com/newsletter/SubscribeNL_init.html?form.subscribeCategory=aaa&request_locale=en_US"';
		}
		else if(node.m_label=='System Integration'){
			ds[di++] = '<a href="/en/services/index.htm?tag=tab1"';
		}
		else if(node.m_label=='Learning Service'){
			ds[di++] = '<a href="/en/services/index.htm?tag=tab3"';
		}
		else if(node.m_label=='Global Delivery'){
			ds[di++] = '<a href="/en/services/index.htm?tag=tab4"';
		}
		else{
			ds[di++] = '<a href="' + node.m_href + '"';
		}
		*/  
		if(node.cp_externalUrl){
			node.m_href = node.cp_externalUrl;
			
			//if no include http ,it's still a intenal link,just different section. 
			/*
			if(node.m_href.indexOf("http") == -1 && HWFDomainNamePath.indexOf(currDomainNamePath)!=-1 && node.m_href.indexOf("index.htm") ==-1 ){
				//alert(node.m_href);
				if(node.m_href.indexOf("?") != -1){
					node.m_href = node.m_href.substr(0,node.m_href.indexOf("?")).replace("_","-")+".htm";
				
				}else{
				
					node.m_href = node.m_href.replace("_","-")+".htm";
				
				}
				
			}
			*/
			
		}
		//alert(node.cp_isDynamic+" ==");
		if(node.cp_isDynamic && "TRUE"==node.cp_isDynamic){
			var new_href = node.m_href;
			if(ssUrlPrefix.indexOf("./")!= -1)
			{
				new_href = new_href.replace(ssUrlPrefix, "/" + g_ssSourceSiteId + "/");
			}		
		 	node.m_href = "/ilink" + new_href;
		 //	node.m_href = "#";
		}

		ds[di++] = '<h1><a href="' + node.m_href + '"';
		//if(node.m_id==263)
		//{
		//	ds[di++] = '<a href="' + node.m_href + '" target="'+ node.cp_externalUrl.cp_target +'" id="cp_target"';
		//}else{
		//	ds[di++] = '<a href="' + node.m_href + '"';
		//}
		
		
		if(node.m_href.indexOf("http:")>-1){
			ds[di++] = 'target="_blank"';
		}
		ds[di++] = ' class="' + nodeClass + '"';	
		ds[di++] = ' name="' + nodeName + '"';		
		ds[di++] = '>'
		ds[di++] = node.m_label;//+" "+node.m_level+" "+lastCount;
		ds[di++] = '</a></h1>';
		
		
/*		
		if((preLevel-nodeLevel )==1){
			document.write("</div>");
		}
		if((preLevel-nodeLevel )==2){
			document.write("</div></div>");
		}
*/		
/*		j = preLevel-nodeLevel;
		for(i=1;i<j;i++){
			document.write("</div>");
		}
*/		document.write(ds.join(''));
		
/*
		if(nodeLevel>2 && lastCount == 0){
			document.write("</div>");
		}
		//for last  class;
*/		
/*		if(nodeLevel>2 && lastCount == 0){
			if(bSelected && node.m_subNodes.length > 0){
				divs = divs + "</div>";
			}else{
				document.write(divs);
			}
		}
*/		
		
		//The number 44 and 18 is section id  of every end section on the end level,if add sub-section ,we need change this id.
		/**
		if(node.m_id==44||  node.m_id==18){
			document.write("</div>");
		}
		*/
		
			 preLevel = nodeLevel;
		
	
	}
	
	if (bSelected || node.m_level == 0)
	{	
				
		for (var i = 0; i < node.m_subNodes.length; i++)
		{	

			//this.DisplayNode(node.m_subNodes[i]);
			this.DisplayNode(node.m_subNodes[i],node.m_subNodes.length-i-1);
			
			
		}
		
	}
	//alert("Test by kevin: "+nodeLevel);
	 
	if(nodeLevel>2 && lastCount == 0){
		//if(bSelected && node.m_subNodes.length > 0){
			document.write("</div>");
		//}
	}
		
	
}

 
