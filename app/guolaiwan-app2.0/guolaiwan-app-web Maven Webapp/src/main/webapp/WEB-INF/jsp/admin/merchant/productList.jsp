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
	<title>商户商品列表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
</head>
<body>
	<div class="x-nav">

		<span class="layui-breadcrumb">
			<a><cite>首页</cite></a>
			<a><cite>商户管理</cite></a>
			<a><cite>${merchant.shopName}</cite></a>
		</span>
		
		<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:38px">ဂ</i></a>
		<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>
		<button class="layui-btn"  style="margin-top:3px;float:right"; id="sptj" onclick="class_add('添加分类','<%=path%>/admin/merModular/addClass','${merchant.uuid}','600','600')"><i class="layui-icon">&#xe608;</i>分类</button>
	</div>
	<div class="x-body">
		<div>
			<div style="width:39%;float:right;">
				<xblock style="margin:0">
					<span style="margin-left: 30px;">分类列表：</span>
					<button class="layui-btn layui-btn-xs"  style="float:right" onclick="getmerMList()"><i class="layui-icon">ဂ</i></button>
				</xblock>
				<table id="merMList" lay-filter="merMList"></table>
			</div>
			<div style="width:60%">
				<xblock style="margin:0">
					<span style="margin-left: 30px">商品列表：</span>
					<button class="layui-btn layui-btn-xs"  style="float:right" onclick="getproductList()"><i class="layui-icon">ဂ</i></button>
				</xblock>
				<table class="layui-table" id="productList"></table>
			</div>
		</div>
	</div>
<!-- 所属分类 -->
<script id="merMNameTpl" type="text/html">			
	{{# if( d.merMId === 0){ }}
		<div class="gh{{d.uuid}}" hidden="hidden"><span class="{{d.uuid}}">{{d.merMName}}</span><button class="layui-btn layui-btn-danger layui-btn-xs " style="float:right" onclick="jbfl('{{d.uuid}}')">解绑</botton><button class="layui-btn layui-btn-xs " style="float:right" onclick="openMap('选择分类','<%=request.getContextPath() %>/admin/merModular/bdMerM?merchant=${merchant.uuid}&product={{d.uuid}}','600','400')">更换</botton></div>
		<div class="bd{{d.uuid}}"><button class="layui-btn layui-btn-xs layui-btn-danger" onclick="openMap('选择分类','<%=request.getContextPath() %>/admin/merModular/bdMerM?merchant=${merchant.uuid}&product={{d.uuid}}','600','400')">绑定分类</botton></div>
	{{# }else{ }}
		<div class="gh{{d.uuid}}"><span class="{{d.uuid}}">{{d.merMName}}</span><button class="layui-btn layui-btn-danger layui-btn-xs " style="float:right" onclick="jbfl('{{d.uuid}}')">解绑</botton><button class="layui-btn layui-btn-xs " style="float:right" onclick="openMap('选择分类','<%=request.getContextPath() %>/admin/merModular/bdMerM?merchant=${merchant.uuid}&product={{d.uuid}}','600','400')">更换</botton></div>
		<div class="bd{{d.uuid}}" hidden="hidden"><button class="layui-btn layui-btn-xs layui-btn-danger" onclick="openMap('选择分类','<%=request.getContextPath() %>/admin/merModular/bdMerM?merchant=${merchant.uuid}&product={{d.uuid}}','600','400')">绑定分类</botton></div>
	{{# } }}
</script>
<!-- 删除 -->
<script type="text/html" id="zsgc">                                      
    <a class="layui-btn layui-btn-danger  layui-btn-xs" lay-event="del">删除</a>
</script> 
<script type="text/html" id="shenheTpl">                                      
	{{#  if(d.productAuditstatus === '草稿'||d.productAuditstatus === '未通过'){ }}
		<span id="introduce" style="color: #F581B1;">{{ d.productAuditstatus }}</span>
		{{#  }else if(d.productAuditstatus === '待审核'){ }}
		<span id="introduce" style="color: #01AAED;">{{ d.productAuditstatus }}</span>
	{{#  } else { }}
		{{ d.productAuditstatus }}
	{{#  } }}
</script> 


	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		 var merchant = '${merchant.uuid}';
		 var merchantId = '${merchant.id}';
		 var has = '${has}';
		 var path = '<%=path%>';
		 layui.use(['element','laypage', 'layer','laytpl','form','table'], function(){
                $ = layui.jquery;//jquery
              var form = layui.form;//表单
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
              table = layui.table; 
      
         fenleISV();

         getproductList();


         getmerMList();
         
        //分类编辑
		table.on('edit(merMList)', function(obj){
			layer.load();
    		var value = obj.value //得到修改后的值
    		,data = obj.data //得到所在行所有键值
    		,field = obj.field; //得到字段
    		$.ajax({
    			type:"post",
				url:path+"/admin/merModular/editMerM.do",
            	data:{'merMId':data.id,'merMValue':value},
				success:function(msg){
					layer.closeAll("loading");
					if(msg=="success"){
						layer.msg("修改成功！");
					}
					
				}
    		})
		});

  		//分类删除
 		table.on('tool(merMList)',function(obj){                                  
  			var data = obj.data;
  			if(obj.event === 'detail'){
      			layer.msg('ID：'+ data.id + ' 的查看操作');
    		} else if(obj.event === 'del'){
      			layer.confirm('真的删除"'+obj.data.name+'"么', function(index){
      				layer.close(index);
      				layer.load();
      				console.log("msg");
      				$.ajax({
    					type:"post",
						url:path+"/admin/merModular/delMerM.do",
            			data:{'merMId':data.id},
						success:function(msg){
							console.log("msg");
							layer.closeAll("loading");
							if(msg=="success"){
								layer.msg("删除成功！");
								obj.del();
							}else if(msg=="father"){
								layer.msg("删除失败，该分类下有子分类！",{icon:5})
							}else{
								layer.msg("系统错误！",{icon:5})
							}
						}
    				})
      			});
    		} else if(obj.event === 'edit'){
   		   		layer.alert('编辑行：<br>'+ JSON.stringify(data))
    		} 
		}) 
         
 		
		});
		
		
		
		
	
	
	function class_add(title,url,id,w,h){
         x_admin_show(title,url+"?uuid="+id,w,h); 
	}
	function openMap(title,url,w,h){
         x_admin_show(title,url,w,h); 
         var merMName = $("input[name='merMName']").val();
         var merMId = $("input[name='merMId']").val();
         console.log("ti"+merMId);
         console.log("ti"+merMName);
	}
	

	
	//判断是否有商品分类
	function fenleISV(){
		if(has==0){
			layer.msg('商家还没有商品分类！', {time: 1000 },function(){
				$("#sptj").click();
			})
		}
	}


	//获取分类表
	function getmerMList(){
		table.render({                  /*3.引入table*/
   			elem:'#merMList'
   			,method:'post'
   			,where:{"merchant":merchant}
   			,url:path+'/admin/merModular/merMList.do'
   			,size:'sm'
   			,height:'340'
   			,cols: [[
   			    {type:'numbers'}
   				,{field: 'level', title: '级别', width:80, align:'center',sort: true}  
   				,{field: 'parentName', title: '上一级'}       
      			,{field: 'name', title: '分类名称', width:100,edit: 'text'}
    			,{field: 'updateTime', title: '上传时间',width:160,sort: true} 
     			,{fixed: 'right',align:'center',width:100,minWidth:100,toolbar:'#zsgc',unresize:true}
   			]]
   		});


	}
	

	//获取商品表
	function getproductList(){
		table.render({                  /*3.引入table*/
   			elem:'#productList'
   			,method:'post'
   			,where:{"merchant":merchantId}
   			,url:'productList.do'
   			,page:true
   			,limits: [10, 20, 30]
            ,limit: 10
   			,cols: [[
   				{field: 'productName', title: '商品名称',align:'center',sort: true}  
   				,{field: 'merMName', title: '所属分类',align:'center', width:180,sort: true,templet:'#merMNameTpl'}       
      			,{field: 'productOldPrice', title: '原价',align:'center',sort: true, width:80}
    			,{field: 'productPrice', title: '现价',align:'center',width:80,sort: true} 
     			,{field: 'updateTime', title: '上传时间',align:'center',width:160,sort: true} 
     			,{field: 'productAuditstatus', title: '审核状态',align:'center',width:120,sort: true,templet:'#shenheTpl'} 
     			
   			]]
   		});
	}

	//解绑分类
	function jbfl(product){
		console.log("jinglaig;");
		layer.load();
		$.ajax({
			type:'post'
			,url:path+'/admin/merModular/jbfl.do'
			,data:{'product':product}
			,success:function(msg){
				if(msg=='success'){
					layer.closeAll('loading');
					layer.msg("解绑成功！");
					$(".bd"+product).show();
					$(".gh"+product).hide();
				}
			}
		})
	}
		
	
	
    </script>
</body>
</html>