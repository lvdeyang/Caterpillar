<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/image";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>直播打赏记录</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<style type="text/css">
.layui-table img {
	max-width: 1000px;
} /* 照片的最大宽度  */
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>直播打赏记录</cite></a>
	</div>
	<div class="x-body">
		<xblock> <a class="layui-btn layui-btn-sm"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:getCompanyList();" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a> </xblock>
		<table id="liveList" lay-filter="liveList"></table>
	</div>



	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"
		charset="utf-8"></script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"
		charset="utf-8"></script>
	<script>
	
	layui.use(['element','layer','laytpl','upload','laydate','form','table'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
        form = layui.form;//分页
        upload = layui.upload;
        laydate = layui.laydate;
        table = layui.table;

        getLiveTipGiftList();
      
       })
        
       
        
        
       


	//获取列表
	function getLiveTipGiftList(){
		table.render({
			elem:'#liveList'
   			,method:'post'
   			,url:'tipgiftlist.do'
   			,page:true
      		,limits: [10,30,50,100]
      		,limit: 10
   			,cols: [[
        		{type:'checkbox'}
        		,{field: 'id',title: '记录Id',align:  'center',sort: true,width:260}
        		,{field: 'username',title: '打赏人昵称',align:  'center',sort: true,width:260}
        		,{field: 'giftname',title: '礼物名称',align:  'center',sort: true,width:260}
        		,{field: 'giftnumber',title: '打赏个数',align:  'center',sort: true,width:260}
        		,{field: 'price',title: '打赏金额（分）',align:  'center',sort: true,width:260}
        		,{field: 'liveid',title: '打赏直播id',align:  'center',sort: true,width:260}
   			]]
		})
	}
	
     
</script>



</body>
</html>