<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
<div >
    <form id="customerForm" name="customerForm" method="post">
    	<table cellpadding="5">
    		<tr>
    			<td>会员账号:</td>
    			<td>
    				<input id="user_name" class="easyui-textbox" type="text" name="user_name" data-options="required:true,validType:{length:[4,20]}"/>
   				</td>
   				<td style="display:none">
   					<input id="user_id" class="easyui-textbox" type="text" name="user_id"/>
   				</td>
   				<td>昵称:</td>
    			<td><input class="easyui-textbox" type="text" name="nickname" data-options="validType:{length:[0,20]}"/></td>
    		</tr>
    		<tr>
    			<td>真实姓名:</td>
    			<td><input class="easyui-textbox" type="text" name="customer_name" data-options="validType:{length:[0,10]}"/></td>
    			<td>性别:</td>
    			<td>
    				<input type="radio" name="customer_sex" checked="checked" value="男"><span>男</span>
    				<input type="radio" name="customer_sex" value="女"><span>女</span>
    			</td>
    		</tr>
    		<tr>
    			<td>出生日期:</td>
    			<td><input id="birthday" class="easyui-datebox" value="1990-01-01" type="text" name="birthday" data-options="editable:false"/></td>
    			<td>邮箱:</td>
    			<td><input class="easyui-textbox" type="text" name="customer_email" data-options="validType:['email','length[0,48]']"/></td>
    		</tr>
    		<tr>
    			<td>会员类型:</td>
    			<td>
    				<input class="easyui-combobox" name="role_id" style="width:100%"
						   data-options="url: '__ROOT__/index.php/CommonData/GetRoleList?RoleGroupID=3',method: 'post',
							valueField:'role_id',textField:'role_name',panelHeight:'auto',required:true,editable:false"/>
							
					<!-- RoleGroupID=3 选择的是CUSTOMER组 -->
				</td>
    			<td>电话:</td>
    			<td><input class="easyui-textbox" type="text" name="phone" data-options="validType:{length:[0,20]}"/></td>
    		</tr>
    		<tr>
    			<td>会员卡号:</td>
    			<td><input class="easyui-textbox" type="text" name="card_number" data-options="validType:{length:[0,20]}"/></td>
    			<td>积分:</td>
    			<td><input class="easyui-textbox" type="text" name="score" data-options="validType:{length:[0,20]}"/></td>
    		</tr>
    		<tr>
    			<td>家庭住址:</td>
    			<td colspan="3"><input class="easyui-textbox" style="width:355px" type="text" name="addr" data-options="validType:{length:[0,20]}"/></td>
    		</tr>
    	</table>
    </form>
    </div>
    <div style="text-align:center;padding:5px;margin-top:10px">
    	<a class="easyui-linkbutton" iconCls="icon-ok" style="width:60px;" onclick="submitForm()">提交</a>
    	<a>&nbsp&nbsp&nbsp&nbsp</a>
    	<a class="easyui-linkbutton" iconCls="icon-cancel" style="width:60px;" onclick="$('#customerModify').dialog('close')">关闭</a>
    </div>
<script type="text/javascript">
function submitForm() {
	if($("#customerForm").form('validate') == false) return;
	var json = formToJson('#customerForm');
	 var submitUrl = "__APP__/CustomerManage/Create";
	 if("" != $("#user_id").val())
	 {
		 submitUrl = "__APP__/CustomerManage/Modify";
	 }
	 $.ajax({
		type:"post",
		url: submitUrl,
		data:json,
		dataType:"json",
		success:function(rsp){
			if(isSuccess(rsp)){
				//关闭当前对话框
				$('#customerModify').dialog('close');
				//重新加载datagrid数据
				$("#customerGrid").datagrid('reload'); 
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