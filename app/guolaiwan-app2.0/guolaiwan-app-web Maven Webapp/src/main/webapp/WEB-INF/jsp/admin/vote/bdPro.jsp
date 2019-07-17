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
  <title>阳光成单系统</title>
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
   <div class="layui-inline">
  			Id：
  			<div class="layui-inline">
    			<input class="layui-input" name="pId" id="pId" autocomplete="off">
  			</div>
  			商品：
  			<div class="layui-inline">
    			<input class="layui-input" name="pName" id="pName" autocomplete="off">
  			</div>
  			商家：
  			<div class="layui-inline">
    			<input class="layui-input" name="mName" id="mName" autocomplete="off">
  			</div>
  			<button class="layui-btn" data-type="reload" onclick="select(this)" >搜索</button>
	    </div>
  </div>
<div class="x-body">
	<table id="productList" lay-filter="productList"></table>
</div>



<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
<script>
	var id = '${voteModular.id}';
	var path = '<%=path%>';
	var acId = '${moId}';
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

  //获取产品列表
  getProductList();
  active = {
        reload: function(){
            var pName = $('#pName');
            var mName = $('#mName');
            var pId = $('#pId');
            table.reload('productListS', {
                page: {
          					curr: 1 //重新从第 1 页开始
        				}
                ,where: {
                    pName: pName.val(),
                    mName: mName.val(),
                    pId:pId.val()
                }
            });
        }
    };
});


function show_win(title,url,w,h){
	x_admin_show(title,url,w,h);
}


function bdPro(pId,pName){
	layer.load();
	$.ajax({
		url:"bdPro.do",
		type:"post",
		data:{"pId":pId,"acId":acId,"pName":pName},
		success:function(msg){
			layer.closeAll("loading");
			if(msg=="success"){
				layer.msg("添加成功！",{icon:1,time:1000});
				$("#ptj"+pId).hide();
				$("#ytj"+pId).show();
			}else if(msg=="chongfu"){
				layer.msg("添加失败<br>产品已经存在于标签中（本标签或者其它标签）！",{icon:2,time:3000});
			}else{
				layer.msg("系统错误",{icon:2,time:3000});
			}
		}
	})

}

 

function getProductList(){
  table.render({               
    elem:'#productList'
    ,method:'post'
    ,url:'proList.do'
    ,page:true
    ,limits: [10,30,50,100]
    ,limit: 10
    ,id:'productListS'
    ,cols: [[
    {type:'checkbox'}
    ,{field: 'id', title: 'ID',sort: true,width:60} 
  	,{field: 'productName', title: '商品名称',sort: true} 
  	,{field: 'productMerchantName', title: '商家名称',sort: true} 
  	,{fix:'right',title: '操作',templet:'#zsgc',width:150,unresize:true } 
  ]]
  });
}
function select(obj){
	var type = $(obj).data('type');
    active[type] ? active[type].call(this) : '';
}
</script>
<script type="text/html" id="zsgc">
	<a class="layui-btn layui-btn-primary layui-btn-xs" href="javascript:show_win('产品详情','<%=path%>/admin/product/info?uuid={{d.uuid}}','','510');">详情>></a>
	<a class="layui-btn layui-btn-xs" id="ptj{{ d.id }}" href="javascript:bdPro('{{ d.id }}','{{ d.productName }}')">添加</a>
	<a style="background:#5FB878" id="ytj{{d.id}}" hidden="hidden">已添加</a>
</script>


</body>
</html>