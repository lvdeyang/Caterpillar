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
            超级卖场中心
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
              <a><cite>超级卖场</cite></a>
              <a><cite>卖场列表</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="open_win('添加卖场','addv','600','500')"><i class="layui-icon">&#xe608;</i>添加卖场</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
         	<table id="supersellList" lay-filter="supersellList"></table>
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
              
              getsupersellList();
              
              
             
              table.on("edit(supersellList)",function(obj){
              		//加载
              		layer.msg('加载中', {
 						 icon: 16
  						,shade: 0.01
					})
              		var data = obj.data,
              		value = obj.value,
              		field = obj.field;
              		
              		$.ajax({
              			type:"post",
              			url:"edit.do",
              			data:{"id":data.id,"value":value,"field":field},
              			success:function(msg){
              				layer.closeAll("loading");
              				if(msg=="success"){
              					layer.msg("修改成功！",{icon:1});
              				}else{
              					layer.msg("系统错误！",{icon:2});
              				}
              			
              			}
              		})
              })
            });
	         
	         function getsupersellList(){
	         	table.render({
            		elem:"#supersellList"
            		,method:'post'
   					,url:'list.do'
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field:"name",title:"名称",edit:"text",style:'cursor: pointer;',width:800}
   						,{title:"活动图片",templet:"#superImg",width:100}
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
	         
	         
	         function show_pic(id){
				idn = "#"+id;
        		$(idn).css('width','600px').css('height','400px');
        		layer.open({
  					type: 1,
  					title: false,
  					closeBtn: 0,
  					area:'600',
  					skin: 'layui-layer-nobg', //没有背景色
  					shadeClose: true,
  					content: $(idn),
  					end: function(){
  						$(idn).css("height","35px");
						$(idn).css("width","35px");
  					}
				})
    		}

    		//删除
			function del(id){
				layer.confirm("真的要删除么？",function(index){
					layer.close(index);
					layer.load();
					$.ajax({
						url:'del.do',
						type:'post',
						data:{'id':id},
						success:function(msg){
							console.log(msg);
							layer.closeAll('loading');
							if(msg=='success'){
								layer.msg("删除成功！");
								getsupersellList();
							}	
						}
					})
				})	
			}
		
            </script>
          
          <script type="text/html" id="zsgc">      
			<a class='layui-btn layui-btn-primary layui-btn-xs' href="javascript:open_win('活动图片','<%=path%>/admin/picture/addlist?&sImg=superImg{{d.id}}&sId={{ d.id }}&source=supersell','600','500')">活动图片</a>
			<a class="layui-btn layui-btn-xs" href="productList?supersellId={{ d.id }}">商品列表</a>
			<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
		  </script>
		  <script type="text/html" id="superImg">
			<a href="javascript:show_pic('superImg{{d.id}}')"><img src='${sysConfig.webUrl}{{ d.pic }}' id='superImg{{d.id}}' style="width:35px;height:35px" alt='' ></a></div>
 		  </script>
    </body>
</html>