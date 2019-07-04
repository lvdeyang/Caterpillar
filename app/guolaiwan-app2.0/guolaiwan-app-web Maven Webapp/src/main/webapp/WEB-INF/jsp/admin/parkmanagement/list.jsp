<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>停车场管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
<link rel="stylesheet" href="<%=path %>/webtheme/theme/css/admin/merchant/list.css">
<style type="text/css">
	.layui-table img{max-width: 1000px;}/* 照片的最大宽度  */
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> 
			<a><cite>首页</cite></a> 
			<a><cite>停车场管理</cite></a>
		</span> 
		<a class="layui-btn layui-btn-small merflash" style="line-height:1.6em;margin-top:3px;float:right"
				href="javascript:location.replace(location.href);" title="刷新">
			<i class="layui-icon" style="line-height:30px">ဂ</i>
		</a>
	</div>
	<div class="x-body">
		<xblock>
			<button class="layui-btn layui-btn-danger" onclick="delAll()">
				<i class="layui-icon">&#xe640;</i>批量删除
			</button>
			<button class="layui-btn" onclick="park_add('添加停车场','addv','900','550')">
				<i class="layui-icon">&#xe608;</i>添加停车场
			</button>
			<%-- <span class="x-right lheight4">共有 ${allcount} 个停车场</span> --%>
			<div class="layui-inline">
				停车场名称：
				<div class="layui-inline">
					<input class="layui-input" name="parkingName" id="parkingName" autocomplete="off">
				</div>
				<button class="layui-btn" data-type="reload" onclick="select(this)">搜索</button>
			</div>
		</xblock>
		<table class="layui-table" id="parkList" lay-filter="parkListFilter"></table>

<script id="parkingNameTpl" type="text/html">      
	<a style="color:blue" title="添加车位" href="parklist?parking={{ d.uuid }}">{{ d.parkingName }}</a>
</script>
<script type="text/html" id="parkingImgTpl">
	<a href="javascript:show_pic('caImg{{d.id}}')"><img id="caImg{{d.id}}" src="<%=basePath%>{{ d.parkingImg}}" alt="" style="width:35px;height:35px;"></a>
</script>
<script id="chargingColumnTpl" type="text/html">
	{{#  if(d.chargingColumn == '0'){ }}
  		{{ '无' }}
	{{#  }else if(d.chargingColumn == '1'){ }}
		{{ '有' }}
	{{#  } else { }}
    	{{ '数据异常' }}
	{{#  } }}
</script>
<script type="text/html" id="zsgc">  
	<a title="修改" href="javascript:;" onclick="park_edit('修改','updatev','{{ d.id }}','','510')" class="tdn">
		<i class="layui-icon">&#xe642;</i>
	</a>
	<a title="删除" href="javascript:;" onclick="park_del(this,'{{ d.id }}')"  class="tdn">
		<i class="layui-icon">&#xe640;</i>
	</a>
 </script>

	</div>
	
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		layui.use(['element','laypage','layer','laytpl','table'], function(){
			$ = layui.jquery;//jquery
	        lement = layui.element;//面包导航
	        laypage = layui.laypage;//分页
	        layer = layui.layer;//弹出层
	        laytpl = layui.laytpl;//模板引擎
	        table = layui.table;
            //以上模块根据需要引入
            // 异步请求数据
            getAttractionsList();
			//检索table表重新加载
			active = {
				reload: function(){
					var parkingName = $('#parkingName');
					table.reload('parkList', {
					  	page: {
							curr: 1 //重新从第 1 页开始
						}
						,where: {
					  		parkingName: parkingName.val(),
						}
					});
					layer.closeAll("loading");
				}
			};
		});
		/*添加*/
		function park_add(title,url,w,h){
		    x_admin_show(title,url,w,h);
		}
		// 批量删除提交
		function delAll () {
		    layer.confirm('确认要删除吗？',function(index){
		        //捉到所有被选中的，发异步进行删除
		        layer.msg('删除成功', {icon: 1});
		    });
		}
		// 详情
		function park_info(title,url,id,w,h) {
			x_admin_show(title,url+"?uuid="+id,w,h); 
		}
		// 编辑
		function park_edit (title,url,id,w,h) {
		    x_admin_show(title,url+"?uuid="+id,w,h); 
		}
		/*删除*/
		function park_del(obj,id){
			layer.confirm('确认要删除吗？（该停车场下车位信息也将删除）',function(index){
				layer.close(index);
				layer.load();
				//发异步删除数据
				$.ajax({
					type:"post",
					url:"del.do",
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
       	//获取列表
		function getAttractionsList(){
			layer.load(2);
			table.render({                  /*3.引入table*/
				 elem:'#parkList'
				 ,method:'post'
				 ,url:'list.do'
				 ,page:true
				 ,limits: [10,30,50,100]
				 ,limit: 10
				 ,height:'full'
				 ,id:'parkList'
				 ,cols: [[
					  {type: 'checkbox'}
					  ,{field: 'id', title: 'ID',sort: true,width:60} 
					  ,{field: 'parkingName', title: '停车场名称',width:100,sort: false,templet:'#parkingNameTpl'}  
					  ,{field: 'parkingImg', title: '图片',width:100,sort: false,templet:'#parkingImgTpl'}
					  ,{field: 'address', title: '停车场地址',width:150,sort: false}
					  ,{field: 'phone', title: '电话',width:120,sort: false}
					  ,{field: 'commonParking', title: '总停放位',width:100,sort: true} 
					  ,{field: 'usedParking', title: '已用车位',width:100,sort: true} 
					  ,{field: 'position', title: '车位位置',width:100,sort: false} 
					  ,{field: 'chargingColumn',title: '充电柱',width:80,templet:'#chargingColumnTpl'} 
					  ,{field: 'parkingDistrict', title: '停车场经纬度',width:80,sort: false}
					  ,{field: 'regulations', title: '停车条例',width:100,sort: false}
					  ,{field: 'fineMultiple', title: '罚款倍数',width:100,sort: false}
					  ,{field: 'stoppingTime', title: '停车时间',width:100,sort: false}
					  ,{fixed: 'right',title: '操作',width:120,minWidth:100,templet:'#zsgc',unresize:true}
				 ]]
				,done:function(res, curr, count){
				 	layer.closeAll("loading");
				}
			});
		}
		//检索
		function select(obj){
			layer.load(2);
			var type = $(obj).data('type');
	  		active[type] ? active[type].call(this) : '';
		}
		// show pics
		function show_pic(id){
			idn = "#"+id;
			$(idn).css('width','600px').css('height','400px');
			layer.open({
				type: 1,
				title: "图片",
				closeBtn: 1,
				area:'600',
				skin: 'layui-layer-nobg', //没有背景色
				shadeClose: true,
				content: $(idn),
				end: function(){
					$(idn).css("width","35px");
					$(idn).css("height","35px");
				}
			})
		}

	</script>
</body>
</html>