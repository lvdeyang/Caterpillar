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
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>订单中心</cite></a>
              <a><cite>订单验单</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
        <div style="margin-bottom:15px">
        	<div class="layui-inline">
        		<img style="width:200px;height:200px;" src="<%=path%>/webtheme/theme/img/admin/smcode.png">
        	</div>
        	<div class="layui-inline" style="margin-left:60px;margin-top:120px">
        	<div style="font-family:楷体;font-size:20px;margin-bottom: 40px" >扫描二维码或手动输入订单号:</div>
        	<form class="layui-form" >
        	<!-- 隐藏、去边框、取消提示 -->
        	<input type="password" id="sOrderNo" name="sOrderNo"  style="width:0px;height:0px; border:0;ime-mode:active"  autocomplete="off" >
        		<div class="layui-form-item">
        			<div class="layui-inline">
        				<input type="text" id="wOrderNo" class="layui-input" name="wOrderNo" style="width:300px" placeholder="手动输入订单号" autocapitalize="on">
        			</div>
        			<div class="layui-inline">
        				<button class='layui-btn' lay-filter="yandan" lay-submit>验单<button>
        			</div>
        		</div>
        	</form>
        	</div>
        	</div>
        	<div align="right">今日验单数量：<span id="cn">${count}</span></div>
        	<table id="ydList" lay-filter="ydList" ></table>
        	
        </div>
        <div id="view" hidden="hidden"></div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
        
        
           
            layui.use(['element','laypage','layer','laytpl','form','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
              form = layui.form;
              table = layui.table;
            
			 //光标到input中            
              $("#sOrderNo").focus();
             	
             	
           /*   $("#wOrderNo").bind("blur",function(){
             	$("#sOrderNo").focus();
             })
             $("#sOrderNo").bind("blur",function(){
             	$("#wOrderNo").focus();
             })
             document.activeElement.id   获取焦点元素id
             
              */
             $(document).click(function(e) { 
  				var clickId = $(e.target).attr("id")    
  				if(clickId!="wOrderNo"){
  					$("#sOrderNo").focus();
  				}
			})
              
              
               table.on('tool(ydList)', function(obj){
    				var data = obj.data;
    				if(obj.event === 'getOrderInfo'){
      					open_order(data);
    				} 
  				});
              
              
              
              form.on("submit(yandan)",function(data){
              	sOrderNo = data.field.sOrderNo;
              	wOrderNo = data.field.wOrderNo;
              	var OrderNo;
              	if(wOrderNo!=''){
              		OrderNo = wOrderNo;
              	}else{
              		OrderNo = sOrderNo;
              	}
              	
              	//提交前清楚表单数据
              	$("#wOrderNo").val("");
              	$("#sOrderNo").val("");
              	
              	
              	if(OrderNo==''){
              		layer.alert("没有输入订单号",{ skin: 'layui-layer-molv' ,closeBtn: 0},function(index){
              			$("#sOrderNo").focus();
              			layer.close(index);
              		});
              		return false;
              	}
              	
             
              
         		$.ajax({
         			type:"post",
         			url:"yandan.do",
         			data:{"orderNo":OrderNo},
         			success:function(msg){
         			console.log(msg.msg);
         				if(msg.msg=="success"){
							layer.msg("验单成功！",{icon:1,time:1000},function(){
								getydList();
								$("#sOrderNo").focus();
								$("#cn").text(msg.count);
							
							})      						
         						
         				//没有订单
         				}else if(msg.msg=="nothave"){
         					layer.alert("没有订单："+OrderNo,{ skin: 'layui-layer-molv' ,closeBtn: 0},function(index){
              					layer.close(index);
              					$("#sOrderNo").focus();
              					
         					});
         				//订单状态不对
         				}else if(msg.msg=="stateError"){
         					layer.alert("操作失败！<br/>订单："+OrderNo+" <br/>"+msg.orderState,{ skin: 'layui-layer-molv' ,closeBtn: 0,btn:["确定","查看订单"],yes:function(index){
         						 layer.close(index);
              					 $("#sOrderNo").focus();
         					
         					},btn2:function(){
         						open_order(msg.order);
         					}});
         				//不属于
         				}else if(msg.msg=="nobelong"){
         					layer.alert("操作失败！<br/>订单："+OrderNo+" <br/>是商家："+msg.order.shopName+"的",{ skin: 'layui-layer-molv' ,closeBtn: 0},function(index){
              					layer.close(index);
              					$("#sOrderNo").focus();
              					
         					});
         				//系统出现问题 
         				}else{
         					layer.alert("系统错误！",{ skin: 'layui-layer-molv' ,closeBtn: 0},function(index){
              					 layer.close(index);
              					 $("#sOrderNo").focus();
              					
         					});
         				}
         			
         			}
         		})
         	   
               return false;
              })
              
              //获取验单成功的表
               getydList();
            
            });
            
            
            function getydList(){
            	table.render({
            		elem:"#ydList"
            		,method:'post'
   					,url:'ydList.do'
   					,page:true
   					,skin: 'nob'
   					,even:'true'
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{field: 'orderNO', title: '订单号',width:280} 
   						,{field: 'orderState', title: '状态',width:80 }
     					,{field: 'ydDate', title: '验单时间' ,width:250}   
      					,{field: 'productName', title: '商品' ,style:"background: #FF5722;color:#fff"} 
      					,{field: 'productNum', title: '数量',width:60 ,style:"background: #FF5722;color:#fff"} 
     					,{field: 'orderAllMoney', title: '总金额',width:120 ,style:"background: #FF5722;color:#fff"} 
   						
      					,{field: 'userName', title: '用户' ,width:120,style:"background: #009688;color:#fff"} 
      					,{field: 'userTel', title: '手机号',width:120 ,style:"background: #009688;color:#fff"}
    					,{title: '操作' ,width:80,toolbar:"#orderTpl"}
   					]]
   					,done:function(res, curr, count){
   						$("#sOrderNo").focus();
   					}
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
		  			anim:2,
		 	 		shadeClose: true, //开启遮罩关闭
		 	 		btn:"确定",
		 	 		yes:function(index){
		 	 			layer.close(index);
		 	 			$("#sOrderNo").focus();
		 	 		},
		  			content: $("#view")
				});
			
			}
			
            </script>
            
        <script	type="text/html" id="orderTpl">
 		<div class="layui-btn-group">
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="getOrderInfo">详情>></a>
</div>
        </script>
        <script	type="text/html" id="orderInfoTpl">
 		<div style="margin:22px">
    		<div>
    			<div><span style="font-size:20px">订单详情</span></div>
    			
    			<hr >
    			订单号：{{   d.order.orderNO}}<br>
    			下单时间：{{   d.order.createDate}}<br>
				<hr >
    			商品：{{   d.order.productName }}X {{   d.order.productNum}}<br>
    			单价：{{   d.order.productPrice}}<br>
    			总金额：{{   d.order.orderAllMoney}}<br>
				支付方式：{{   d.order.payMode}}<br>
				<hr >
				用户：{{   d.order.userName}}<br>
				手机号：{{   d.order.userTel}}
				<br>
				<br>
	{{# if( d.order.orderState=="已验单" ){ }}
    			<span style="font-size:20px;">已验单！</span>验单时间：{{   d.order.ydDate}}
	{{# }else{ }}  
				<span style="font-size:20px;color:red">{{d.order.orderState}}</span>
	{{# } }} 
 		</div>
    	</div>
        </script>
       
    </body>
</html>