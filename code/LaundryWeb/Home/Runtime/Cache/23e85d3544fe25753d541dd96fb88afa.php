<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
<div >
	<form id="orderForm" name="orderForm" method="post">
	  	<table cellpadding="5">
	  		<tr>
	  			<td>订单编号:</td>
	  			<td><input name="order_code" class="easyui-textbox" type="text" readonly="readonly"></input></td>
				<td>用户账号:</td>
	  			<td><input name="user_name" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  			<td>创建时间:</td>
	  			<td><input name="create_time" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  			<td>支付方式:</td>
	  			<td><input name="pay_option" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  		</tr>
	  		<tr>
	  			<td>订单类型:</td>
	  			<td><input name="order_type" class="easyui-textbox" type="text" readonly="readonly"></input></td>
				<td>订单状态:</td>
	  			<td><input name="order_status" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  			<td>产品数量:</td>
	  			<td><input name="total_count" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  			<td>产品总价:</td>
	  			<td><input name="total_price" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  		</tr>
	  		<tr>
	  			<td>取货时间:</td>
	  			<td><input name="take_time" class="easyui-textbox" type="text" readonly="readonly"></input></td>
				<td>送货时间:</td>
	  			<td><input name="delivery_time" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  			<td>客户名称:</td>
	  			<td><input name="customer_name" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  			<td>客户电话:</td>
	  			<td><input name="phone" class="easyui-textbox" type="text" readonly="readonly"></input></td>
	  		</tr>
	  		<tr>
	   			<td>家庭住址:</td>
	   			<td colspan="3"><input name="delivery_addr" class="easyui-textbox" readonly="readonly" style="width:378px;height:40px" type="text" data-options="multiline:true"></input></td>
	   			<td>订单备注:</td>
	   			<td colspan="3"><input name="order_note" class="easyui-textbox" readonly="readonly" style="width:378px;height:40px" type="text" data-options="multiline:true"></input></td>
	   		</tr>
	  	</table>
	</form>
</div>
<div class="easyui-layout"data-options="fit:true">
	<table id="orderDetailGrid" class="easyui-datagrid" title="产品列表" style="width:100%;height:95%"
			data-options="rownumbers:true,checkOnSelect:true,pagination:true,pageList:[10,20,30],singleSelect:true">
		<thead>
			<tr>
				<th hidden="true" data-options="field:'order_detail_id',width:200">订单详情ID</th>
				<th hidden="true" data-options="field:'price_type',width:80,formatter:formatPrice" >价格类型</th>
				<th data-options="field:'product_id',width:60" >产品编号</th>
				<th data-options="field:'product_name',width:180">产品名称</th>
				<th data-options="field:'current_price',width:80">产品价格</th>
				<th data-options="field:'quantity',width:60">数量</th>
				<th data-options="field:'product_type',width:100">产品类型</th>
				<th data-options="field:'product_img',width:160">产品图片</th> 
				<th data-options="field:'product_describe',width:280">产品描述</th>
			</tr>
		</thead>
	</table>
</div>
<div style="text-align:center;padding:5px">
	<a class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	<a class="easyui-linkbutton" onclick="$('#productModify').dialog('close')">Close</a>
</div>
<script type="text/javascript">
function formatPrice(val,row){
	if (val == "面议"){
		return row.current_price = "面议";
	}
}
</script>
</body>
</html>