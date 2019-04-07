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
<!--         <div class="layui-inline">
  			<button class="layui-btn"  onclick="order_add('出票','addv','900','600')">出票</button>
	    </div>	  -->  	 
        <div class="layui-inline">
        	订单号：
  			<div class="layui-inline">
    			<input class="layui-input" name="orderNO" id="orderNO" autocomplete="off">
  			</div>
  			<button class="layui-btn"  onclick="getListg()">查一个订单</button>
	    </div>
	    	<div class="layui-inline">
        			商家：
        			<div class="layui-inline">
    					<input class="layui-input" name="mName" id="mName" autocomplete="off">
  					</div>
  					<button class="layui-btn"   data-type="reload"  onclick="selectPName(this)">模糊搜索</button>
  					<button type="button" class="layui-btn" id="deriveallorder"
			onclick="deriveallorder()">导出所有订单</button>	
	   	 </div>
        	<!-- tab -->
            <div class="layui-tab layui-tab-brief" lay-filter="demo">
  				<ul class="layui-tab-title">
    				<li>已付款<span id="PAYSUCCESSbadge"  class="layui-badge"></span></li>
    				<li>正在停车<span id="DELIVERbadge" hidden="hidden"  class="layui-badge"></span></li>
    				<li>已过期<span id="RECEIPTbadge" hidden="hidden" class="layui-badge"></span></li>
    				<li>申请退款<span id="CONFIRMEDbadge" hidden="hidden"  class="layui-badge"></span></li>
    				<li>退款成功<span id="REFUNDEDbadge" hidden="hidden" class="layui-badge"></span></li>
    				<li>退款失败<span id="REFUNDFAILbadge" hidden="hidden" class="layui-badge"></span></li>
    				<li>全部<span id="REFUNDFAILbadge" hidden="hidden" class="layui-badge"></span></li>
  				</ul>
  				
  				<table id="orderList" lay-filter="orderListF"></table>
  				<!-- <div class="layui-tab-content">
   					<div class="layui-tab-item layui-show"></div>
    				<div class="layui-tab-item"><table id="orderList1" lay-filter="orderList1"></table></div>
   			 		<div class="layui-tab-item"><table id="orderList2" lay-filter="orderList2"></table></div>
   			 		<div class="layui-tab-item"><table id="orderList3" lay-filter="orderList3"></table></div>
   			 		<div class="layui-tab-item"><table id="orderList4" lay-filter="orderList4"></table></div>
   			 		<div class="layui-tab-item"><table id="orderList5" lay-filter="orderList5"></table></div>
   			 		<div class="layui-tab-item"><table id="orderList6" lay-filter="orderList6"></table></div>
   			 		<div class="layui-tab-item"><table id="orderList7" lay-filter="orderList7"></table></div>
   			 		<div class="layui-tab-item"><table id="orderList8" lay-filter="orderList8"></table></div>
   			 		<div class="layui-tab-item"><table id="orderList9" lay-filter="orderList9"></table></div>
  				</div> -->
			</div>
			
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
              
			 //切换tab
              element.on('tab(demo)', function(data){
    				i = data.index;
    				active["reload"].call(this);
  			  });
  			  
  				
  			active = {
        		reload: function(){
        		mName=$("#mName").val();
            	table.reload('orderList', {
                	page: {
          				curr: 1 //重新从第 1 页开始
        			},
        			where: {
                    	mName: mName,
                    	type:i
                	},
                	  url:'switchover.do'
            	});
        	  }
    		};
        });
        
        
        
        	function getList() {
        		ele = 0;
        		table.render({
        			elem : '#orderList',
        			method : 'post',
        			url : 'order.do',
        			page : true, 
        			where : {
        				"type" : ele,
        				"mName" : mName
        			},
        			limits : [ 5, 10, 30, 100 ],
        			limit : 10,
        			cols : [ [
        				{
        					type : 'checkbox'
        				}
        				, {
        					field : 'id',
        					title : 'Id',
        					align : 'center',
        					width : 60
        				}
        				, {
        					field : 'orderId',
        					title : '用户Id',
        					align : 'center',
        					width : 60
        				}
        				, {
        					field : 'attractionsId',
        					title : '景区Id',
        					width : 70
        				}
        				, {
        					field : 'parkingLayer',
        					title : '层',
        					width : 50
        				}
        				, {
        					field : 'parkingName',
        					title : '停车场名称',
        					width : 100
        				}
        				, {
        					field : 'Time',
        					title : '营业时间',
        					width : 95
        				}
        				, {
        					field : 'parkingDistrict',
        					title : '区',
        					width : 50
        				}
        				, {
        					field : 'parkingNumber',
        					title : '车位编号',
        					width : 90
        				}
        				, {
        					field : 'parkingCost',
        					title : '停车费用',
        					width : 90
        				}
        				, {
        					field : 'stoppingTime',
        					title : '停车总时间',
        					width : 100
        				}
        				, {
        					field : 'bookingTime',
        					title : '预订时间',
        					width : 100
        				}
        				, {
        					field : 'dueTime',
        					title : '到期时间',
        					width : 100
        				}
        				, {
        					field : 'orderStatus',
        					title : '订单状态',
        					width : 100
        				}
        				, {
        					field : 'refund',
        					title : '退款理由  ',
        					width : 100
        				}
        				, {
        					field : 'platenumber',
        					title : '车牌号',
        					width : 100
        				}
        				, {
        					field : 'overTimeMoney',
        					title : '超时金额',
        					width : 100
        				}
        				, {
        					field : 'overTime',
        					title : '超时时间',
        					width : 100
        				}
        				, {
        					field : 'orderNo',
        					title : '购买编号',
        					width : 100
        				}
        				, {
        					field : 'path',
        					title : '二维码路径',
        					width : 100
        				}
        			/* 	,{fixed: 'dueTime', title: '操作',width:80,toolbar:'#zsgc'} */
        			] ],
        			id : "orderList",
        			done : function(res, curr, count) {
        				layer.closeAll("loading");
        			}
        		})
        	}
        
        	function getListg() {
        		var orderId = $('#orderNO').val();
        		console.log(orderId);
        		table.render({
        			elem : '#orderList',
        			method : 'post',
        			url : 'demand.do',
        			page : true,
        			where : {
        				"orderIds" : orderId
        			},
        			limits : [ 5, 10, 30, 100 ],
        			limit : 10,
        			cols : [ [
        				{
        					type : 'checkbox'
        				}
        				, {
        					field : 'id',
        					title : 'Id',
        					align : 'center',
        					width : 60
        				}
        				, {
        					field : 'orderId',
        					title : '用户Id',
        					align : 'center',
        					width : 60
        				}
        				, {
        					field : 'attractionsId',
        					title : '景区Id',
        					width : 70
        				}
        				, {
        					field : 'parkingLayer',
        					title : '层',
        					width : 50
        				}
        				, {
        					field : 'parkingName',
        					title : '停车场名称',
        					width : 100
        				}
        				, {
        					field : 'Time',
        					title : '营业时间',
        					width : 95
        				}
        				, {
        					field : 'parkingDistrict',
        					title : '区',
        					width : 50
        				}
        				, {
        					field : 'parkingNumber',
        					title : '车位编号',
        					width : 90
        				}
        				, {
        					field : 'parkingCost',
        					title : '停车费用',
        					width : 90
        				}
        				, {
        					field : 'stoppingTime',
        					title : '停车总时间',
        					width : 100
        				}
        				, {
        					field : 'bookingTime',
        					title : '预订时间',
        					width : 100
        				}
        				, {
        					field : 'dueTime',
        					title : '到期时间',
        					width : 100
        				}
        				, {
        					field : 'orderStatus',
        					title : '订单状态',
        					width : 100
        				}
        				, {
        					field : 'refund',
        					title : '退款理由  ',
        					width : 100
        				}
        				, {
        					field : 'platenumber',
        					title : '车牌号',
        					width : 100
        				}
        				, {
        					field : 'overTimeMoney',
        					title : '超时金额',
        					width : 100
        				}
        				, {
        					field : 'overTime',
        					title : '超时时间',
        					width : 100
        				}
        				, {
        					field : 'orderNo',
        					title : '购买编号',
        					width : 100
        				}
        				, {
        					field : 'path',
        					title : '二维码路径',
        					width : 100
        				}
        			] ],
        			done : function(res, curr, count) {
        				//如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
        				$(".layui-tab-title").children("li").removeClass("layui-this");
        				$($(".layui-tab-title").children("li")[res.type]).addClass("layui-this");
        				$(".layui-badge").css("display", "none");
        			}
        		})
        	}
        
        
        	function selectPName(obj) { // 模糊查询
        		active["reload"].call(this);
        	}
        
        	//导出所有订单
        	function deriveallorder() {
        		$.ajax({
        			type : "post",
        			url : "deriveorder.do",
        			data : {
        				"type" : i,
        				"mName" : $("#mName").val()
        			},
        			success : function(msg) {
        			var url = "<%=path%>/admin/parkingorder/order";
        		    window.open(url);
        		    layer.close(index);
        			}
        		})
        	}
        </script>
        <script type="text/html" id="zsgc">
			<div class="layui-btn-group">
				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="getOrderInfo">详情>></a>
			</div>
		</script>
    </body>
</html>