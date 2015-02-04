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
<script type="text/javascript" src="__PUBLIC__/ThirdParty/Others/uploadPreview.min.js"></script>
<script type="text/javascript" src="__PUBLIC__/Fuego/PublicJS/jsonConvert.js"></script>
<script type="text/javascript" src="__PUBLIC__/Fuego/PublicJS/public.js"></script>
<script type="text/javascript" src="__PUBLIC__/ThirdParty/EasyUI/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="__PUBLIC__/Fuego/PublicJS/EasyUIValidate.js"></script>
</head>
<body>
	<div id="toolbar" style="padding:5px;height:auto">
		<table style="width:100%;">
        	<tr>
            	<td style="width:200px;">
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="onModify('Create')">增加</a>
					<a class="easyui-linkbutton" iconCls="icon-edit" onclick="onModify('Modify')">编辑</a>
					<a class="easyui-linkbutton" iconCls="icon-remove" onclick="onDelete()">删除</a>
				</td>
				<td>
					<form id="searchForm" method="post">
					<input name="user_name" class="easyui-textbox" data-options="prompt:'请输入用户名',validType:{length:[0,20]}" style="width:120px">
					<a class="easyui-linkbutton" iconCls="icon-search" onclick="onSearch()">搜索</a>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div class="easyui-layout"data-options="fit:true">
	<table id="userGrid" class="easyui-datagrid" style="width:100%;height:100%"
			data-options="rownumbers:true,pagination:true,pageList:[10,20,30],singleSelect:true,
			url:'__APP__/AdminManage/LoadPage',method:'post',queryParams:'',toolbar:'#toolbar'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'user_id',width:100">员工编号</th>
				<th data-options="field:'user_name',width:120">员工账号</th>
				<th data-options="field:'email',width:160">员工邮箱</th>
				<th data-options="field:'role_name',width:160">员工角色</th>
			</tr>
		</thead>
	</table>
	</div>
	<div id="userModify" style="padding-left:15px;padding-top:10px;"></div>
<script type="text/javascript">
function onSearch(){
	if($("#searchForm").form('validate') == false) return;
	var json = buildRequest('#searchForm');
	var param = {"data" : json};
	$('#userGrid').datagrid('loadData', { total: 0, rows: [] });
	$('#userGrid').datagrid('load', param);
	
}
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
		    title: '员工信息',
		    width: 280,
		    height: 220,
		    closed: false,
		    cache: false,
		    href: '__APP__/AdminManage/adminInfo.html',
		    modal: true
		});

		if(type == "Modify")
		{
			var json = objToJson(row.user_id);
			$.ajax({
				type:"post",
				url:"__APP__/AdminManage/Show",
				data:json,
				dataType:"json",
				success:function(rsp){
					if(isSuccess(rsp)){
						$('#userForm').form('load',rsp.obj);
						$('#user_name').textbox('readonly');
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
					url:"__APP__/AdminManage/Delete",
					data:json,
					dataType:"json",
					success:function(rsp){
						if(isSuccess(rsp)){
							//解决最后一条记录无法删除BUG
							//var rowIndex = $('#userGrid').datagrid('getRowIndex', row);
					        //$('#userGrid').datagrid('deleteRow', rowIndex);
					        //解决最后一条记录无法删除BUG
					        $('#userGrid').datagrid('loadData', { total: 0, rows: [] });
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