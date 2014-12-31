<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="__PUBLIC__/ThirdParty/EasyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="__PUBLIC__/ThirdParty/EasyUI/themes/icon.css">
<link rel="stylesheet" type="text/css" href="__PUBLIC__/ThirdParty/EasyUI/demo/demo.css">
<script type="text/javascript" src="__PUBLIC__/ThirdParty/EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="__PUBLIC__/ThirdParty/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="__PUBLIC__/Fuego/PublicJS/jsonConvert.js"></script>
<script type="text/javascript" src="__PUBLIC__/Fuego/PublicJS/public.js"></script>
</head>
<body>
    <form id="userForm" method="post">
    	<table cellpadding="5">
    		<tr>
    			<td>用户名:</td>
    			<td>
    				<input class="easyui-textbox" type="text" name="user_name" data-options="required:true"></input>
   				</td>
   				<td style="display:none">
   					<input id="user_id" class="easyui-textbox" type="text" name="user_id"></input>
   				</td>
    		</tr>
    		<tr>
    			<td>密码:</td>
    			<td><input class="easyui-textbox" type="text" name="password" data-options="required:true"></input></td>
    		</tr>
    	</table>
    </form>
    <div style="text-align:center;padding:5px">
    	<a class="easyui-linkbutton" onclick="submitForm()">Submit</a>
    	<a class="easyui-linkbutton" onclick="$('#userModify').dialog('close')">Close</a>
    </div>
<script type="text/javascript">
function submitForm() {
	var json = formToJson('#userForm');
	 var submitUrl = "__APP__/UserManage/Create";
	 if("" != $("#user_id").val())
	 {
		 submitUrl = "__APP__/UserManage/Modify";
	 }
	$.ajax({
		type:"post",
		url: submitUrl,
		data:json,
		dataType:"json",
		success:function(rsp){
			if(isSuccess(rsp)){
				//关闭当前对话框
				$('#userModify').dialog('close');
				//重新加载datagrid数据
				$("#userGrid").datagrid('reload'); 
			}
			else{
				fuegoAlert(rsp.errorMsg);
			}
		},
    	error:function(){
    		fuegoAlert("当前ajax操作出错！");
		}
	});
}
</script>
</body>
</html>