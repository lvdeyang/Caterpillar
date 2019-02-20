<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <title>选择商品</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>
  <div class="x-nav">
   <span class="layui-breadcrumb">
    <a><cite>首页</cite></a>
    <a><cite>产品管理</cite></a>
    <a><cite>产品列表</cite></a>
  </span> <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<form class="layui-form">
    <div class="layui-input-inline">
     <input class="layui-input" name="mohus" >
   </div>
<div align="right" class="layui-input-inline">
 <button class="layui-btn" lay-filter="getPro" lay-submit>搜索</button>
</div>
</form>
<table id="productList" lay-filter="productList"></table>
<!-- 产品名称引擎模板 -->
<script type="text/html" id="productNameTpl">
  <a href="javascript:;" onclick="full_show('子产品列表','child/list','{{ d.id }}','400','600')">{{ d.productName }}</a>
</script>
<!-- 板块引擎模板 -->
<script type="text/html" id="bankuaiTpl">
  {{ d.productModularCodeName }}&nbsp{{ d.productClassName }}
</script>
<!-- 审核状态引擎模板 -->
<script type="text/html" id="shenheTpl">
  {{#  if(d.productAuditstatus === '草稿'||d.productAuditstatus === '未通过'){ }}
  <span id="introduce" style="color: #F581B1;">{{ d.productAuditstatus }}</span>
  {{#  }else if(d.productAuditstatus === '待审核'){ }}
  <span id="introduce" style="color: #01AAED;">{{ d.productAuditstatus }}</span>
  {{#  } else { }}
  {{ d.productAuditstatus }}
  {{#  } }}                       
</script>
<!-- 操作引擎模板 -->
<script type="text/html" id="procaozuoTpl">
<a title="选择" href="javascript:;" onclick="addToDistribute('{{ d.id }}','${carid}','PRODUCT')" class="layui-btn layui-btn-xs">选择</a>
</script>
<script type="text/html" id="mercaozuoTpl">
<a title="选择" href="javascript:;" onclick="addToDistribute('{{ d.id }}','${carid}','MERCHANT')" class="layui-btn layui-btn-xs">选择</a>
</script>
<script type="text/html" id="actcaozuoTpl">
<a title="选择" href="javascript:;" onclick="addToDistribute('{{ d.id }}','${carid}','ACTIVITY')" class="layui-btn layui-btn-xs">选择</a>
</script>

<!-- <a title="分销" href="javascript:;" onclick="product_info('选择分销商','<%=path%>/admin/distributor/distributorlist','{{ d.id }}','','510')" class="layui-btn layui-btn-xs">分销</a>
 -->
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
<script>

  layui.use(['element','layer','laytpl','upload','laydate','laypage','form','table'], function(){
  $ = layui.jquery;//jquery
  lement = layui.element;//面包导航
  layer = layui.layer;//弹出层
  laytpl = layui.laytpl;//模板引擎
  laypage = layui.laypage;//分页
  upload = layui.upload;
  laydate = layui.laydate;
  form = layui.form;
  table = layui.table;

  //获取列表
  getList();
  
  
	//表单提交
  form.on('submit(getPro)',function(data){
  	layer.load();
   getProductListByf(data.field);
   return false;
 });
});



  function product_add(title,url,w,h){
    x_admin_show(title,url,w,h);
  }

  function addToDistribute(id , carid,cla){
    
     $.ajax({
          	  type:"get",
     			  url:"../selectPro.do/"+id+"/"+carid+"/"+cla,
                  data:{},
                  success:function(msg){
                  	if(msg == 'success')
	                   layer.alert('选择成功', {icon: 6},function () {
                           // 获得frame索引
                           try{
                               var index = parent.layer.getFrameIndex(window.name);
                               console.log(index);
                               //关闭当前frame
                               parent.window.location.reload();
                               parent.layer.close(index);
                             }catch(exception)
                             {
                               window.location.reload();
                             }
                           });
                 }
            });
  }

	//产品详情
  function product_info(title,url,id,w,h) {
    x_admin_show(title,url+"?uuid="+id,w,h); 
  }

  function full_show(title,url,id,w,h) {
   var index = layer.open({
     type: 2,
     area: [w+'px', h +'px'],
	  fix: false, //不固定w
	  maxmin: true,
	  shadeClose: true,
	  shade:0.4,
	  title: title,
	  content: url+"?uuid="+id
	}); 
   layer.full(index);
 }
 
//审核意见
function advice_show(advice){
	layer.open({
   type: 1
  	,title: false //不显示标题栏
  	,closeBtn: false
  	,area: '300px;'
  	,shade: 0.8
  	,id: 'LAY_layuipro' //设定一个id，防止重复弹出
  	,btn: ['知道啦']
  	,btnAlign: 'c'
  	,moveType: 1 //拖拽模式，0或者1
  	,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">审核意见：<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+advice+'</div>'
  });
}

function getList(){
	if('${classify}'=='PRODUCT')
	{
	  table.render({               
	    elem:'#productList'
	    ,method:'post'
	    ,url:'../proList.do'
	    ,page:true
	    ,limits: [10,30,50,100]
	    ,limit: 10
	    ,skin:'row'
	    ,even:true
	    ,cols: [[
	    {type:'checkbox'}
	    ,{field: 'id', title: 'ID',sort: true,width:60} 
	    ,{field: 'productName', title: '商品名称',sort: true,templet:'#productNameTpl'}  
	    ,{fixed: 'right',title: '操作',width:160,templet:'#procaozuoTpl',unresize:true} 
	    ]]
	  });
	}else if('${classify}'=='MERCHANT'){
	  table.render({               
	    elem:'#productList'
	    ,method:'post'
	    ,url:'../merList.do'
	    ,page:true
	    ,limits: [10,30,50,100]
	    ,limit: 10
	    ,skin:'row'
	    ,even:true
	    ,cols: [[
	    {type:'checkbox'}
	    ,{field: 'id', title: 'ID',sort: true,width:60} 
	    ,{field: 'shopName', title: '商户名称',sort: true}  
	    ,{fixed: 'right',title: '操作',width:160,templet:'#mercaozuoTpl',unresize:true} 
	    ]]
	  });	
	}else if('${classify}'=='ACTIVITY'){
		table.render({               
	    elem:'#productList'
	    ,method:'post'
	    ,url:'../actList.do'
	    ,page:true
	    ,limits: [10,30,50,100]
	    ,limit: 10
	    ,skin:'row'
	    ,even:true
	    ,cols: [[
	    {type:'checkbox'}
	    ,{field: 'id', title: 'ID',sort: true,width:60} 
	    ,{field: 'name', title: '活动名称',sort: true}  
	    ,{fixed: 'right',title: '操作',width:160,templet:'#actcaozuoTpl',unresize:true} 
	    ]]
	  });	
	}


}

function getProductListByf(data){
	table.render({               
   elem:'#productList'
   ,method:'post'
   ,url:'../mohuSelect.do'
   ,where:data
   ,page:true
   ,skin:'row'
   ,even:true
   ,cols: [[
   {type:'checkbox'}
    ,{field: 'id', title: 'ID',sort: true,width:60} 
    ,{field: 'productName', title: '商品名称',sort: true,templet:'#productNameTpl'}  
    ,{fixed: 'right',title: '操作',width:160,templet:'#procaozuoTpl',unresize:true} 
   ]]
   ,done: function(res, curr, count){
     layer.closeAll("loading");
   }
 });
}
</script>


</body>
</html>