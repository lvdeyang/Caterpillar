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
	   	 </div>
        	<!-- tab -->
            <div class="layui-tab layui-tab-brief" lay-filter="demo">
  				<ul class="layui-tab-title">
    				<li>未付款<span id="NOTPAYbadge" class="layui-badge"></span></li>
    				<li>已付款<span id="PAYSUCCESSbadge"  class="layui-badge"></span></li>
    				<li>正在停车<span id="DELIVERbadge" hidden="hidden"  class="layui-badge"></span></li>
    				<li>已过期<span id="RECEIPTbadge" hidden="hidden" class="layui-badge"></span></li>
    				<li>申请退款<span id="CONFIRMEDbadge" hidden="hidden"  class="layui-badge"></span></li>
    				<li>退款成功<span id="TESTEDbadge" hidden="hidden" class="layui-badge"></span></li>
    				<li>退款失败<span id="COMMENTEDbadge" hidden="hidden" class="layui-badge"></span></li>
  				</ul>
  				
  				<table id="orderList" lay-filter="orderList"></table>
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
              //详情
              table.on('tool(orderList)', function(obj){
    				console.log(1);
    				var data = obj.data;
    				if(obj.event === 'getOrderInfo'){
      					open_order(data);
    				} 
  				});
  				
  				
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
             
             
             
             function getList(){
             console.log(1);
            	ele=0;
             	table.render({
                    elem:'#orderList'
                    ,method:'post'
                    ,url:'list.do'
                    ,page:true
                    ,where:{"type":ele,"pName":pName,"mName":mName,"pmenus":pmenus}
                    ,limits: [5,10,30,100]
                    ,limit: 10
                    ,cols: [[
                        {type:'checkbox'}  
                        ,{field: 'id',title: 'Id',align:'center',width:60}  
                        ,{field: 'parkingLayer', title: '停车场层',width:60} 
                        ,{field: 'parkingName', title: '停车场 名称',width:178 } 
      					,{field: 'parkingDistrict', title: '停车区',width:60}
   						,{field: 'parkingNumber', title: '车位编号' ,width:60}  
   						,{field: 'parkingCost', title: '停车费用' ,width:60}  
   						,{field: 'stoppingTime', title: '停车总时间' ,width:60} 
      					,{field: 'orderStatus', title: '订单状态' ,width:130 } 
      					,{field: 'bookingTime', title: '预订时间',width:170 } 
     					,{field: 'dueTime', title: '到期时间',width:170 } 
     					,{field: 'refund', title: '退款理由',width:100 } 
     					,{field: 'platenumber', title: '车牌号',width:100 } 
     					,{field: 'overTimeMoney', title: '超时金额',width:130 } 
     					,{field: 'overTime', title: '超时时间',width:100 } 
     					,{field: 'orderId', title: '订单Id',width:100 } 
                     ]]
                	,id:"mClassTable"
                })
             }
             
             function open_order(order){
				//使用引擎模板
				var data= {
					"order":order
				}
				var getTpl = orderInfoTpl.innerHTML
				,view = document.getElementById('view');
				laytpl(getTpl).render(data, function(html){
  					view.innerHTML = html;
				});
				//随机数
				var num=Math.floor(Math.random()*5+1);
				//打开窗口
				layer.open({
		 	 		type: 1,
		  			skin: 'layui-layer-molv', //样式类名
		  			closeBtn: 0, //不显示关闭按钮
		  			area: [600+'px', 450 +'px'],
		  			anim:2,  //动画类型
		 	 		shadeClose: true, //开启遮罩关闭
		 	 		btn:"确定",
		 	 		yes:function(index){
		 	 			layer.close(index);
		 	 		},
		  			content: $("#view")
				});
			}
			
			function selectPName(obj){
				$.ajax({
					type:"post"
					,url:"countMP.do"
					,data:{"pName":$("#pName").val(),"mName":$("#mName").val()}
					,success:function(msg){
						$(".layui-badge").css("display","none");
  							
						console.log(msg.cgroups);
						var ocount=0;
						if(msg.cgroups!=null){
						msg.cgroups.forEach(function(val,index,arr){
  							
  							$("#"+val.name+"badge").text(val.count);
  							$("#"+val.name+"badge").css("display","inline-block");
							$(".layui-tab-title").children("li").removeClass("layui-this");
    						$($(".layui-tab-title").children("li")[0]).addClass("layui-this");
  							ocount = ocount+val.count;
						})
						i=0;
  						pName=$("#pName").val();
  						mName=$("#mName").val();
  						active["reload"].call(this);	
						}
						layer.msg("共有"+ocount+"条订单！");
					}
				
				})
			}
			
			function fenyefind(obj){
	        	var pId = $("#pmenus").val();
	        	if(pId ==-1){
	        		window.location.reload();
	        		return false;
	        	}else{
	        		$.ajax({
						type:"post"
						,url:"pCount.do"
						,data:{"pmenus":$("#pmenus").val()}
						,success:function(msg){
							$(".layui-badge").css("display","none");
	  							
							console.log(msg.csgroups);
							var ocount=0;
							if(msg.csgroups!=null){
							msg.csgroups.forEach(function(val,index,arr){
	  							
	  							$("#"+val.name+"badge").text(val.count);
	  							$("#"+val.name+"badge").css("display","inline-block");
								$(".layui-tab-title").children("li").removeClass("layui-this");
	    						$($(".layui-tab-title").children("li")[0]).addClass("layui-this");
	  							ocount = ocount+val.count;
							})
							i=0;
	  						pmenus=$("#pmenus").val();
	  						active["reload"].call(this);
							}
							layer.msg("共有"+ocount+"条订单！");
						}
					
					})
	        	}

			}
		
		  function order_add(title,url,w,h){
		    x_admin_show(title,url,w,h);
		  }
		//导出所有订单
		function deriveallorder() {
			var url = "<%=path%>/admin/orderinfo/driveallorder";
			window.open(url);
			layer.close(index);
		}
        </script>
    </body>
</html>