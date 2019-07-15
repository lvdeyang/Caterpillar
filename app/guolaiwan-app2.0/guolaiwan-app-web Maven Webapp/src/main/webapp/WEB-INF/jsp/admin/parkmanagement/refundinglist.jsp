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
            	/* agreeOrder(obj,data.id);
  				rejectOrder(obj,data.id); */ 				
  			/* active = {
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
    		}; */
        }); 
          
             //同意退款
             function agreeOrder(obj,orderId,id){                            
                 var _urirefund = 'wxpay/refund?orderId='+orderId+'&id='+id;
		          $.get(_urirefund, null, function(data){
					//changeOrderStatus(obj,'REFUNDED',id);
					
				 });
             }
             
             function rejectOrder(obj,id){
             		//添加弹窗输入理由的方法
             		inputJustification(id);
             		if(confirm("确定要拒绝吗?")){
             		rejectOrderStatus(obj,id,'PAYSUCCESS');
             		}
					
             }
         /*     //修改同意退款状态
	        function changeOrderStatus(obj,status,id){	        
			   var _urichangeorder ='changeOrderStatus?status='+status+'&id='+id;
		          $.get(_urichangeorder, null, function(data){
						layer.msg("已退款");
						$(obj).parents("tr").remove();
						location.reload();
			      });
			}   */
			//修改拒绝退款状态     
		   function rejectOrderStatus(obj,id,status){		  	   
			   var _urichangeorder ='rejectOrderStatus?id='+id+'&status='+status;
		          $.get(_urichangeorder, null, function(data){
						layer.msg("拒绝退款");
						$(obj).parents("tr").remove();
						location.reload();
			      });
			}      
			
			
			//输入理由的方法
			function inputJustification(id){
        		var val = prompt("请输入您拒绝退款的理由","您的退款理由不充分~");
        		var Justification="";
      	  		if(val!=null&&val!=""){
      	  			Justification=val;
      	  		}else{
      	  			Justification="您的退款理由不充分~"
      	  			 }
      	  		$.ajax({ 
			        type: "POST", 
			        data :{"id":id,"justification":Justification},
			        url: "updateJustification.do",
			        success: function(data) {
			         }
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
                        ,{field: 'orderId', title: '订单编号' ,width:200}      					  						     					
      					,{field: 'parkingName', title: '停车场名称',width:120 }
      					,{field: 'parkingDistrict', title: '停车区',width:120 }
      					,{field: 'parkingNumber', title: '车位编号' ,width:100}
      					,{field: 'parkingLayer', title: '停车场层',width:120 }
      					,{field: 'platenumber', title: '车牌号',width:120 }   
     					,{field: 'parkingCost', title: '停车费用',width:120 } 
     					,{field: 'refund', title: '退款原因',width:120 } 
      					,{fixed: 'right', title: '操作',width:200,toolbar:'#zsgc,#tui1'}
    					 
                     ]]
                	,id:"mClassTable"
                })
             }

        </script>
        <script type="text/html" id="zsgc">
			<div class="layui-btn-group">
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="agree" onclick="agreeOrder(this,'{{ d.orderId }}','{{d.id}}')">同意退款>></a>
                
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="agree" onclick="rejectOrder(this,'{{d.id}}')"><<拒绝退款 </a>
			</div>
		</script>
    </body>
</html>