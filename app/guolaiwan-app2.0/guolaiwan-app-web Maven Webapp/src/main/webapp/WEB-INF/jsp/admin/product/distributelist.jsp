<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>阳光成单系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>产品管理</cite></a>
			<a><cite>产品列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div id="prosel" class="x-body">
		
	</div>
	<!--  -->
	<div class="x-body">
		<xblock>
		<button class="layui-btn">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		
		<span class="x-right" style="line-height:40px">系统中共有：<span
			id="count">${count}</span>个商品
		</span> </xblock>
		<table id="productList" lay-filter="productList"></table>
	</div>
	<!-- 操作引擎模板 -->
	<script type="text/html" id="procaozuoTpl">
 <a title="详情" href="javascript:;" onclick="product_info('产品详情','info','{{d.uuid}}','','510')" style="text-decoration:none;display:none"><i class="layui-icon">&#xe62d;</i></a>
 <a title="删除" href="javascript:;" onclick="pro_del(this,'{{ d.uuid }}')" style="text-decoration:none"><i class="layui-icon">&#xe640;</i></a>
 <a title="套餐" href="javascript:;" onclick="full_show('产品套餐','package','{{ d.id }}','','510')" style="text-decoration:none;display:none;"><i class="layui-icon">&#xe857;</i></a>
  
<a title="分销政策" href="javascript:;" onclick="location.href='toDisProList.do/{{ d.id }}'" class="layui-btn layui-btn-xs">分销政策</a>

</script>

	<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"
		charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/layui/js/x-layui.js"
		charset="utf-8"></script>
	<script>

		layui.use([ 'element', 'layer', 'laytpl', 'upload', 'laydate', 'laypage', 'form', 'table' ], function() {
			$ = layui.jquery; //jquery
			lement = layui.element; //面包导航
			layer = layui.layer; //弹出层
			laytpl = layui.laytpl; //模板引擎
			laypage = layui.laypage; //分页
			upload = layui.upload;
			laydate = layui.laydate;
			form = layui.form;
			table = layui.table;
	
			//获取产品列表
			getDistributeProductList();
	
			//表单提交
			form.on('submit(getPro)', function(data) {
				layer.load();
				getProductListByf(data.field);
				return false;
			});
		});
	
		function product_add(title, url, w, h) {
			x_admin_show(title, url, w, h);
		}

		//产品详情
		function product_info(title, url, id, w, h) {
			x_admin_show(title, url + "?uuid=" + id, w, h);
		}
	
		function full_show(title, url, id, w, h) {
			var index = layer.open({
				type : 2,
				area : [ w + 'px', h + 'px' ],
				fix : false, //不固定
				maxmin : true,
				shadeClose : true,
				shade : 0.4,
				title : title,
				content : url + "?uuid=" + id
			});
			layer.full(index);
		}
	
		//审核意见
		function advice_show(advice) {
			layer.open({
				type : 1,
				title : false, //不显示标题栏
				closeBtn : false,
				area : '300px;',
				shade : 0.8,
				id : 'LAY_layuipro', //设定一个id，防止重复弹出
				btn : [ '知道啦' ],
				btnAlign : 'c',
				moveType : 1, //拖拽模式，0或者1
				content : '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">审核意见：<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' + advice + '</div>'
			});
		}
	
		function getDistributeProductList() {
			table.render({
				elem : '#productList',
				method : 'post',
				url : 'distributeProductList.do',
				page : true,
				limits : [ 10, 30, 50, 100 ],
				limit : 10,
				skin : 'row',
				even : true,
				cols : [ [
					{
						type : 'checkbox'
					}
					, {
						field : 'productName',
						title : '商品',
						sort : true,
						width : 200
					}
					, {
						field : 'distributorName',
						title : '商户',
						sort : true
					}
					, {
						field : 'distributorType',
						title : '分销类型',
						sort : true
					}
					, {
						field : 'minAmount',
						title : '最小数量',
						width : 100,
						sort : true
					}
					, {
						field : 'online',
						title : '在线',
						width : 100,
						sort : true
					}
					, {
						field : 'price',
						title : '价格',
						sort : true,
						width : 80
					}
					, {
						field : 'proleft',
						title : '剩余数量',
						width : 180,
						sort : true
					}
					, {
						field : 'regionName',
						title : '区域',
						width : 100,
						sort : true
					}
					, {
						field : 'sellPrice',
						title : '售价',
						width : 80,
						sort : true
					}
					, {
						fixed : 'right',
						title : '操作',
						width : 160,
						templet : '#procaozuoTpl',
						unresize : true
					}
				] ]
			});
		}
	
		function getProductListByf(data) {
			table.render({
				elem : '#productList',
				method : 'post',
				url : 'getProductBy.do',
				where : data,
				page : true,
				limits : [ 10, 30, 50, 100 ],
				limit : 10,
				skin : 'row',
				even : true,
				cols : [ [
					{
						type : 'checkbox'
					}
					, {
						field : 'id',
						title : 'ID',
						sort : true,
						width : 60
					}
					, {
						field : 'productName',
						title : '商品名称',
						sort : true,
						templet : '#productNameTpl'
					}
					, {
						field : 'productCityName',
						title : '城市',
						sort : true,
						width : 80
					}
					, {
						field : 'productMerchantName',
						title : '商家',
						sort : true
					}
					, {
						title : '板块',
						width : 160,
						templet : '#bankuaiTpl'
					}
					, {
						field : 'productOldPrice',
						title : '原价',
						width : 100,
						sort : true
					}
					, {
						field : 'productPrice',
						title : '现价',
						width : 100,
						sort : true
					}
					, {
						field : 'updateTime',
						title : '上传时间',
						width : 200,
						sort : true
					}
					, {
						title : '状态',
						width : 100,
						templet : '#shenheTpl'
					}
					, {
						fixed : 'right',
						title : '操作',
						width : 160,
						templet : '#procaozuoTpl',
						unresize : true
					}
				] ],
				done : function(res, curr, count) {
					layer.closeAll("loading");
				}
			});
			
			
		
			
		}
		
		
			function pro_del(obj,id,name){
                layer.confirm('确认要删除吗？',function(index){
                  layer.close(index);
                  layer.load();
                  //发异步删除数据
                  $.ajax({
                    type:"post",
                    url:"disprodel.do",
                    data:{"uuid":id},
                    success:function(msg){
                  	 if(msg=="success"){
                        layer.closeAll("loading");
                  		  $(obj).parents("tr").remove();
                  		  layer.msg('已删除!',{icon:1,time:1000});
                  	 }
                    }
                  }) 
                });
            }
		
	</script>
</body>
</html>