/////////////////////////////////////////////////////////////////////////////
// Copyright HKMCI . All rights reserved.
// Date : 2011-2-9
// Author : Kevin Leng
/////////////////////////////////////////////////////////////////////////////

var html="";
var node1;
function getNode(node,nodeId){
		for (var count=0 ; count < node.m_subNodes.length; count++){
			
			id=node.m_subNodes[count].m_id;
			if(id==nodeId){
			node1 =node.m_subNodes[count];
			}
			else{
			getNode(node.m_subNodes[count],nodeId);
			}
		}
}

function proNav(node,nodeId){
		
		getNode(node,nodeId);
		for (var count=0 ; count < node1.m_subNodes.length; count++){
		
				var node2 = node1.m_subNodes[count];
				label2 	 = node2.m_label;
				href2 	 = node2.m_href;
				html+="<a href='"+href2+"'><div class='arrowR'></div>"+label2+"</a>";
				
		}
		
		return html;
		
}
