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
            投票标题
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
              <a><cite>投票中心</cite></a>
              <a><cite>投票标签列表</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>
        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="open_win('添加标签','addv?optionId=${optionId}','600','500')"><i class="layui-icon">&#xe608;</i>添加标签</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
         	<table id="voteList" lay-filter="voteList"></table>
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
              
              getvoteList();
              
              
              table.on("edit(voteList)",function(obj){
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
	         
	         function getvoteList(){
	         	table.render({
            		elem:"#voteList"
            		,method:'post'
   					,url:'list.do?optionId=${optionId}'
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field:"modularName",title:"标签名称",edit:"text",width:160}
   						,{field:"addTime",title:"添加时间",width:160}
   						,{field:"modularDescribe",title:"标签描述",edit:"text",width:1000}
   						,{title: '标签缩略图',templet:"#picTpl",width:100} 
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
						getvoteList();
					}	
				}
			})
		})
		
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
    
    function open_win(title,url,w,h){
				x_admin_show(title,url,w,h)
	}
            </script>
          
<script type="text/html" id="zsgc">
<a class='layui-btn layui-btn-xs' href="javascript:open_win('选择图片','<%=path%>/admin/picture/addlist?sImg=caImg{{d.id}}&sId={{ d.id }}&source=vote','800','600')">选择图片</a>
			<a class="layui-btn layui-btn-xs" href="productList?modularId={{ d.id }}&content={{ d.content }}">商品列表</a>
			<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
</script>
<script type="text/html" id="picTpl">
 <a href="javascript:show_pic('caImg{{d.id}}')"><img id="caImg{{d.id}}"  src= "http://www.guolaiwan.net/file/{{ d.slidepic}}" alt="" style="width:35px;height:35px"></a>
</script>
    </body>
</html>