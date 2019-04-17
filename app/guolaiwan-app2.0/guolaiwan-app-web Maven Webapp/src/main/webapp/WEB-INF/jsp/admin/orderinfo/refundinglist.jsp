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
            订单中心
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
  		<style type="text/css">
   			.layui-badge {
   				display:none;
   			}
   		</style>
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>订单信息</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
         
        <div class="x-body">


	        <table id="orderList" lay-filter="orderList"></table>
  	
			
        </div>
        <div id="view" hidden="hidden"></div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
        var i = 0;
        var pName = "";
        var mName = ""; 
        var pmenus = ""; 
            layui.use(['element','laypage','layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              element = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
              table = layui.table;
              
              getList();
              
/* 			table.on('tool(orderList)', function(obj){
    				console.log(1);
    				var data = obj.data;
    				if(obj.event === 'agree'){
      					
      					
      					
      					
    				} 
  				}); */
            	agreeOrder(obj,data.id);
  				rejectOrder(obj,data.id);
  			active = {
        		reload: function(){
            	table.reload('mClassTable', {
                	page: {
          				curr: 1 //重新从第 1 页开始
        			}
                	,where: {
                    	pName: pName,
                    	mName: mName,
                    	pmenus:pmenus,
                    	type:i
                	}
            	});
        		}
    		};
        }); 
             
             function agreeOrder(obj,orderId){
                 var _urirefund = '../../website/wxpay/refund?orderId='+orderId;
		          $.get(_urirefund, null, function(data){
					changeOrderStatus(obj,orderId,'REFUNDED');
					
				 });
             }
             function rejectOrder(obj,orderId){
					rejectOrderStatus(obj,orderId,'PAYSUCCESS');
             }
	        function changeOrderStatus(obj,orderId,status){
			   var _urichangeorder = 'changeOrderStatus?orderId='+orderId+'&status='+status;
		          $.get(_urichangeorder, null, function(data){
						layer.msg("已退款");
						$(obj).parents("tr").remove();
			      });
			}       
		   function rejectOrderStatus(obj,orderId,status){
			   var _urichangeorder = 'rejectOrderStatus?orderId='+orderId+'&status='+status;
		          $.get(_urichangeorder, null, function(data){
						layer.msg("拒绝退款");
						$(obj).parents("tr").remove();
			      });
			}      
             function getList(){
             console.log(1);
            	ele=0;
             	table.render({
                    elem:'#orderList'
                    ,method:'post'
                    ,url:'refundlist.do'
                    ,page:false
                    ,cols: [[
                         {field: 'id',title: 'Id',align:'center',width:60}
      					,{field: 'userTel', title: '用户手机号',width:120}
   						,{field: 'createDate', title: '下单时间' ,width:200}  
      					,{field: 'productName', title: '商品' ,width:700} 
     					,{field: 'orderAllMoney', title: '总金额',width:120 } 
     					,{field: 'reason', title: '退款原因',width:120 } 
      					,{fixed: 'right', title: '操作',width:200,toolbar:'#zsgc,#tui1'}
    					 
                     ]]
                	,id:"mClassTable"
                })
             }

        </script>
        <script type="text/html" id="zsgc">
			<div class="layui-btn-group">
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="agree" onclick="agreeOrder(this,'{{ d.id }}')">同意退款>></a>
                
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="agree" onclick="rejectOrder(this,'{{ d.id }}')"><<拒绝退款 </a>
			</div>
		</script>
    </body>
</html>