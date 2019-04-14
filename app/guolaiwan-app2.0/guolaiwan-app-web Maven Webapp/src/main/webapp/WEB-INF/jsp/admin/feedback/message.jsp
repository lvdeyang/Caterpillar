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
            反馈中心
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
              <a><cite>反馈信息</cite></a>
            </span>
            <a id="alink"class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">?</i></a>
        </div>
         
        <div class="x-body">
        	<!-- tab -->
            <div class="layui-tab layui-tab-brief" lay-filter="demo">
  				<ul class="layui-tab-title">
    				<li>未回复<span id="PAYSUCCESSbadge"  class="layui-badge"></span></li>
    				<li>已回复<span id="DELIVERbadge" hidden="hidden"  class="layui-badge"></span></li>
  				</ul>
  				<table id="orderList" lay-filter="orderList">
  				
  				
  				</table>
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
    				if(obj.event == 'deleteOrder'){
    				 deletefeedback(data);
    				}
  				});
  				
  			active = {
        		reload: function(){
        		mName=$("#mName").val();
            	table.reload('mClassTable', {
                	page: {
          				curr: 1 //重新从第 1 页开始
        			},
        			where: {
                    	mName: mName,
                    	type:i
                	}
                
            	});
        	  }
    		};
        });
        
        
        
        	function getList() {
        		ele = 0;
        		table.render({
        			elem : '#orderList',
        			method : 'post',
        			url : 'inquire.do',
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
        					field : 'userId',
        					title : '用户Id',
        					align : 'center',
        					width : 90
        				}
        				, {
        					field : 'filialeId',
        					title : '分公司Id',
        					width : 90
        				}
        				, {
        					field : 'headportrait',
        					title : '用户头像',
        					width : 100
        				}
        				, {
        					field : 'username',
        					title : '用户名称',
        					width : 100
        				}
        				, {
        					field : 'date',
        					title : '反馈日期',
        					width : 95
        				}
        				, {
        					field : 'content',
        					title : '反馈内容',
        					width : 150
        				}
        				, {
        					field : 'replycontent',
        					title : '回复内容',
        					width : 150
        				}
        				, {
        					field : 'state',
        					title : '状态',
        					width : 150
        				}
        			   ,{fixed: 'right', title: '操作',width:120,toolbar:'#zsgc'}
        			] ],
        			id : "mClassTable"
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
		 	 		yes:function(index){
		 	 			layer.close(index);
		 	 		},
		  			content: $("#view")
				});
			} 
			
          function copyText(data){
          	$.ajax({
					type:"post"
					,url:"replymessage.do"
					,data:{"reply":$("#ver").val(),"orderid":data}
					,success:function(msg){
					alert("回复成功");
					document.getElementById("alink").click();
					}
				})
			}
			
          function deletefeedback(data){
          	$.ajax({
					type:"post"
					,url:"deleteorder.do"
					,data:{"orderid":data.id}
					,success:function(msg){
					alert("删除成功");
					document.getElementById("alink").click();
					}
				}) 
			}
         
        
        </script>
        <script type="text/html" id="zsgc">
			<div class="layui-btn-group">
               <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="getOrderInfo" >回复>></a> 
               <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="deleteOrder" >删除</a>
			</div>
		</script>
		<script	type="text/html" id="orderInfoTpl">
 		<div style="margin:22px">
    		<div>
    			用户反馈：<br>{{   d.order.content}}<br>
				<hr >
				管理员回复：<textarea id="ver" required lay-verify="required" placeholder="请输入" class="layui-textarea"></textarea>
			<hr > 
               <a hre="http://localhost:8080/guolaiwan-app-web//admin/my" onclick="copyText({{d.order.id}})" class="layui-btn">提交</a>
 		</div>
    	</div>
        </script>
    </body>
</html>