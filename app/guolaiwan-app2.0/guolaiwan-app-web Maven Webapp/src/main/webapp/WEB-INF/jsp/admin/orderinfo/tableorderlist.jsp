<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            餐桌订单
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    <style type="text/css">
	.layui-table img{     max-width: 1000px;}/* 照片的最大宽度  */
	</style>
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>订单中心</cite></a>
              <a><cite>餐桌订单</cite></a>
            </span>
			<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>        	
        	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>
        </div>
        <div class="x-body">
         	<table id="tableorderList" lay-filter="tableorderList"></table>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
        
        
           
            layui.use(['element','laypage','layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;
              table = layui.table;//模板引擎
              //以上模块根据需要引入
              
              gettableorderList();
              
            });
	         
	         function gettableorderList(){
	         	table.render({
            		elem:"#tableorderList"
            		,method:'post'
   					,url:'alltableorder'
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field:"orderid",title:"订单号",align:'center',width:100}
   						,{field:"merchantName",title:"商家名称",align:'center',width:230}
   						,{field:"tableId",title:"桌子Id",align:'center',width:100}
   						,{field:"userName",title:"客户姓名",edit:"text",width:100}
   						,{field:"userPhone",title:"客户电话",edit:"text",width:150}
   						,{field:"type",title:"用餐时间",edit:"text",width:100}
   						,{field:"tableDate",title:"预定时间",edit:"text",width:150}
   						,{field:"tableState",title:"支付状态",edit:"text",width:130}
   						,{field:"bookPrice",title:"预定金额",edit:"text",width:100}
   						,{field:"dishState",title:"订餐状态",edit:"text",width:130}
   						,{field:"dishPrice",title:"订餐金额",edit:"text",width:100}
   						,{title:"操作",templet:"#zsgc"}
   						]]
   					 ,done:function(res, curr, count){
   						$("#allcount").text(count); 
   					}
            	})
	         }
	         
	         
	         function open_win(title,url,w,h){
	         	x_admin_show(title,url,w,h);
	         }
    		
    
    function open_win(title,url,w,h){
				x_admin_show(title,url,w,h)
	}
            </script>
          
<script type="text/html" id="zsgc">
 {{#  if(d.dishPrice !=0){ }}
			<a class="layui-btn layui-btn-xs" href="getdishlist?tableId={{ d.tableId}}&userId={{d.userId}}&merchantId={{d.merchantId}}">预定菜品</a>

 {{#  } else { }}

 {{#  } }} 

</script>
    </body>
</html>