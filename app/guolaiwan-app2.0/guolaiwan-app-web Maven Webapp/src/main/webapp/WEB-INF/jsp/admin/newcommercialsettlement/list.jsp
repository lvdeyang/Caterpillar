<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>商家结算</title>
<meta name="rende rer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/x-admin.css"
	media="all">
</head>

<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>商家结算</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>

	<div class="x-body">

		<xblock>
		<button type="button" class="layui-btn" id="deriveallorder"
			onclick="deriveallorder()">导出总表</button>
		<div class="layui-inline">
			<div class="layui-inline">
				<input class="layui-input" name="sName" id="sName"
					autocomplete="off">
			</div>
			<button class="layui-btn" data-type="reload" onclick="select(this)">搜索商家</button>
		</div>
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="sTime" placeholder=" - "
					style="width:300px">
			</div>
			<button class="layui-btn" data-type="reload" onclick="search(this)">确定</button>
		</div>
		<div class="layui-inline">
			&nbsp;&nbsp;&nbsp;&nbsp;总流水：${cashAmount}元&nbsp;&nbsp;&nbsp;&nbsp;
			总提成：${getAmount}元
		</div>
		</xblock>
		<table class="layui-table" id="List" lay-filter="List"></table>
	</div>
	<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		layui.use([ 'element', 'laypage', 'layer', 'laytpl', 'table', 'form', 'laydate' ], function() {
			$ = layui.jquery; //jquery
			lement = layui.element; //面包导航
			layer = layui.layer; //弹出层
			laypage = layui.laypage; //分页
			laytpl = layui.laytpl; //模板引擎
			table = layui.table;
			form = layui.form;
			laydate = layui.laydate;
	
			laydate.render({
				elem : '#sTime',
				type : 'datetime',
				range : true
			});
	
			getCommercialList();
			active = {
				reload : function() {
					var index = layer.load(1);
					var sName = $('#sName');
					var sTime = $('#sTime');
					table.reload('mList', {
						where : {
							sName : sName.val(),
							sTime : sTime.val(),
						},
						done : function(res) { //返回数据执行回调函数
							layer.close(index); //返回数据关闭loading
	
						}
					});
				}
			};
			return false;
	
		});
	
	
		function getCommercialList() { //加载
			var index = layer.load(1);
			table.render({
				elem : "#List",
				method : "post",
				url : "list.do",
				page : true,
				loading : true,
				limits : [ 10, 30, 50, 100 ],
				limit : 10,
				id : 'mList',
				cols : [ [ {
					field : 'id',
					title : 'ID',
					align : 'center',
					sort : true,
					width : 60
				}
					, {
						field : 'merchantName',
						align : 'center',
						title : '名称',
						sort : true
					}
					, {
						field : 'bankNo',
						align : 'center',
						title : '银行卡号',
						sort : true,
						width : 280
					}
					, {
						field : 'amount',
						align : 'center',
						title : '结算金额',
						sort : true,
						width : 150
					}
					, {
						field : 'accrued',
						align : 'center',
						title : '提成金额',
						sort : true,
						width : 150
					}
					, {
						field : 'settleDate',
						align : 'center',
						title : '结算日期',
						sort : true,
						width : 200
					}
					, {
						fixed : 'right',
						width : 200,
						title : '导出',
						unresize : true,
						align : 'center',
						templet : "#derive"
					}
				] ],
				done : function(res) { //返回数据执行回调函数
					layer.close(index); //返回数据关闭loading
	
				}
			})
		}
	
		function select(obj) {
			var type = $(obj).data('type');
			active[type] ? active[type].call(this) : '';
		}
	
		function search(obj) {
			var type = $(obj).data('type');
			active[type] ? active[type].call(this) : '';
		}
	
		function deriveallorder() {
			/* $.get('http://localhost:8080/guolaiwan-app-web/admin/newcommercialsettlement/deriveallorder.do', null, function() {});  */
			var url = "<%=path%>/admin/newcommercialsettlement/deriveallorder.do";
			window.open(url);
			layer.close(index);
		}
	
		function deriveall(id) {
			var url = "<%=path%>/admin/newcommercialsettlement/deriveall.do/" + id;
			window.open(url);
			layer.close(index);
		}
	
		function derivedetails(merid, id) {
			var url = "<%=path%>/admin/newcommercialsettlement/derivedetails.do/" + merid + "/" + id;
			window.open(url);
			layer.close(index);
		}
	</script>
	<script type="text/html" id="derive">
	<a title="导出总表" href="javascript:;" onclick="deriveall('{{d.id}}')" style="text-decoration:none">	
		<i class="layui-icon">导出总表</i>
	</a>
	 <a title="导出详情表" href="javascript:;" onclick="derivedetails('{{d.merchantId}}','{{d.id}}')" style="text-decoration:none">
         <i class="layui-icon">导出详情表</i>
     </a>	
</script>
</body>
</html>
