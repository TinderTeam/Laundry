<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="__PUBLIC__/ThirdParty/EasyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="__PUBLIC__/ThirdParty/EasyUI/themes/icon.css">
<link rel="stylesheet" type="text/css" href="__PUBLIC__/ThirdParty/EasyUI/demo/demo.css">
<script type="text/javascript" src="__PUBLIC__/ThirdParty/EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="__PUBLIC__/ThirdParty/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="__PUBLIC__/Fuego/PublicJS/jsonConvert.js"></script>
<script type="text/javascript" src="__PUBLIC__/Fuego/PublicJS/public.js"></script>
</head>
<body>
	<div style="margin:20px 0;"></div>
	<div class="easyui-layout"data-options="fit:true">
	<table id="userGrid" class="easyui-datagrid" title="Basic DataGrid" style="width:100%;height:95%"
			data-options="pagination:true,pageList:[10,20,30],singleSelect:true,
			url:'__APP__/ProductManage/LoadPage',method:'get',toolbar:'#toolbar'">
		<thead>
			<tr>
				<th data-options="field:'product_id',width:80">产品ID</th>
				<th data-options="field:'product_name',width:80">产品名称</th>
				<th data-options="field:'describe',width:80">密码</th>
			</tr>
		</thead>
	</table>
	</div>
	<div id="toolbar" style="padding:5px;height:auto">
		<a class="easyui-linkbutton" iconCls="icon-add" onclick="onModify('Create')">增加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" onclick="onModify('Modify')">编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" onclick="onDelete()">删除</a>
		<input class="easyui-datebox" style="width:80px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
	</div>
	<div id="userModify"></div>
<script type="text/javascript">
function onModify(type){
	var row = $('#userGrid').datagrid('getSelected');
	if((type == "Modify")&&(!row))
	{
		fuegoAlert("请选择一条记录");
	}
	else
	{	
		//打开用户编辑窗口
		$('#userModify').dialog({
		    title: 'My Dialog',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: '__APP__/UserManage/userInfo.html',
		    modal: true
		});
		if(type == "Modify")
		{
			var json = objToJson(row.user_id);
			$.ajax({
				type:"post",
				url:"__APP__/UserManage/Show",
				data:json,
				dataType:"json",
				success:function(rsp){
					if(isSuccess(rsp)){
						$('#userForm').form('load',rsp);
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
	}	
}
function onDelete(){
	var row = $('#userGrid').datagrid('getSelected');
	if(!row)
	{
		fuegoAlert("请选择一条记录");
	}
	else
	{
		$.messager.confirm('确认提示', '确认删除该条记录？', function (r) {
			if(r){
				var json = objToJson(row.user_id);
				$.ajax({
					type:"post",
					url:"__APP__/UserManage/Delete",
					data:json,
					dataType:"json",
					success:function(rsp){
						if(isSuccess(rsp)){
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
		});
	}	
}
</script>
</body>
</html>