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
            活动中心
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
              <a><cite>活动中心</cite></a>
              <a><cite>活动列表</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="open_win('添加活动','addv','600','500')"><i class="layui-icon">&#xe608;</i>添加活动</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
         	<table id="activityList" lay-filter="activityList"></table>
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
              
              getactivityList();
              
              
              //监听活动内容
              table.on("tool(activityList)",function(obj){
              	var data = obj.data;
              	if(obj.event==="setContent"){
					if(data.type==="积分"){
						layer.prompt({
        					formType: 0
        					,title: '设置['+ data.name +']积分比率：%'
        					,value: data.ratio
      						}, function(value, index){
      								$.ajax({
      									type:"post"
      									,url:"edit.do"
      									,data:{"id":data.id,"field":"type","type":"JIFEN","ratio":value}
      									,success:function(msg){
      										layer.close(index);
      										if(msg=="success"){
      											layer.msg("修改成功！",{icon:1});
      											obj.update({
          											content: "积分比率："+value+"%"
        										});
      										}else{
      											layer.msg("系统错误！",{icon:2});
      										}
      									}
      								})
        							return false;
      					});
					}
					if(data.type==="打折"){
						layer.prompt({
        					formType: 0
        					,title: '设置['+ data.name +']折扣：'
        					,value: data.discount
      						}, function(value, index){
      								$.ajax({
      									type:"post"
      									,url:"edit.do"
      									,data:{"id":data.id,"field":"type","type":"DAZHE","discount":value}
      									,success:function(msg){
      										layer.close(index);
      										if(msg=="success"){
      											layer.msg("修改成功！",{icon:1});
      											obj.update({
          											content: "打"+value+"折"
        										});
      										}else{
      											layer.msg("系统错误！",{icon:2});
      										}
      									}
      								})
        							return false;
      					});
					}
					if(data.type==="满减"){
						layer.open({
        					type: 1
        					,title: '设置['+ data.name +']满减：'
        					,btn:["确定","取消"]
        					,content:"<div style='margin:20px'>满：<input style='width:100px;display:inline' class='layui-input' id='ceil' value='"+data.ceil+"'>减：<input id='cut' style='width:100px;display:inline' class='layui-input' value='"+data.cut+"'>	</div>"
        					,yes:function(index){
        						var ceil =  $("#ceil").val();
        						var cut =  $("#cut").val();
        						$.ajax({
      									type:"post"
      									,url:"edit.do"
      									,data:{"id":data.id,"field":"type","type":"MANJIAN","ceil":ceil,"cut":cut}
      									,success:function(msg){
      										layer.close(index);
      										if(msg=="success"){
      											layer.msg("修改成功！",{icon:1});
      											obj.update({
          											content: "满"+ceil+"减"+cut
        										});
      										}else{
      											layer.msg("系统错误！",{icon:2});
      										}
      									}
      								})
        						return false;
        					}
      					});
					}
					if(data.type==="固定价格"){
						layer.prompt({
        					formType: 0
        					,title: '设置['+ data.name +']固定价格：'
        					,value: data.fixedPrice
      						}, function(value, index){
      								$.ajax({
      									type:"post"
      									,url:"edit.do"
      									,data:{"id":data.id,"field":"type","type":"FIXEDPRICE","fixedPrice":value}
      									,success:function(msg){
      										layer.close(index);
      										if(msg=="success"){
      											layer.msg("修改成功！",{icon:1});
      											obj.update({
          											content: "固定价格："+value+"元"
        										});
      										}else{
      											layer.msg("系统错误！",{icon:2});
      										}
      									}
      								})
        							return false;
      					});
					}
              	}
              })
              table.on("edit(activityList)",function(obj){
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
	         
	         function getactivityList(){
	         	table.render({
            		elem:"#activityList"
            		,method:'post'
   					,url:'list.do'
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field:"name",title:"名称",edit:"text"}
   						,{field:"type",title:"类型",width:160}
   						,{field:"fixedPrice",title:"固定价格（元）",width:160,edit:"text"}
   						,{field:"content",title:"活动内容",event:"setContent", style:'cursor: pointer;',width:160}
   						,{title:"活动图片",templet:"#actImg",width:100}
   						,{field:"activityRule",title:"活动规则",edit:"text"}
   						,{title:"规则图片",templet:"#actruleImg",width:100}
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
    		
    		function recommend(id){
    		
    		    $.ajax({
					url:'recommend.do',
					type:'post',
					data:{'id':id},
					success:function(msg){
						console.log(msg);
						layer.closeAll('loading');
						if(msg=='success'){
							layer.msg("推荐成功！");
							getactivityList();
						}	
					}
				});
    		}
    		
    		function cancelrecommend(id){
    		
    		    $.ajax({
					url:'cancelrecommend.do',
					type:'post',
					data:{'id':id},
					success:function(msg){
						console.log(msg);
						layer.closeAll('loading');
						if(msg=='success'){
							layer.msg("取消推荐成功！");
							getactivityList();
						}	
					}
				});
    		}
    		
    		
    function amend_info(id,state){ //上移下移
         　　　　　 $.ajax({
              type:"post",
              url:"chengesortindex",
              data:{"activityId":id,"state":state},
              success:function(msg){
            	 if(msg.data == "success"){
            	 	getactivityList();
            	 }else{
            	    alert(msg.data);
            	 }
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
						getactivityList();
					}	
				}
			})
		})
		
	}
		
            </script>
          
          <script type="text/html" id="zsgc">
			<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:void(0)' onclick="amend_info('{{ d.id }}','T')">上移</a>
			<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:void(0)' onclick="amend_info('{{ d.id }}','D')">下移</a>                  
			<a class='layui-btn layui-btn-primary layui-btn-xs' href="javascript:open_win('活动图片','<%=path%>/admin/picture/addlist?&sImg=actImg{{d.id}}&sId={{ d.id }}&source=activity','600','500')">活动图片</a>
			<a class='layui-btn layui-btn-primary layui-btn-xs' href="javascript:open_win('规则图片','<%=path%>/admin/picture/addlist?&sImg=actruleImg{{d.id}}&sId={{ d.id }}&source=activityrule','600','500')">规则图片</a>
			<a class="layui-btn layui-btn-xs" href="productList?activityId={{ d.id }}&content={{ d.content }}">商品列表</a>
			<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
     
            {{#  if(d.recommend === 0){ }}
                <a class="layui-btn layui-btn-xs" href='javascript:recommend("{{ d.id }}")'>推荐</a>
            {{#  } }} 

            {{#  if(d.recommend === 1){ }}
                <a class="layui-btn layui-btn-xs" href='javascript:cancelrecommend("{{ d.id }}")'>取消推荐</a>
            {{#  } }} 
		  </script>
		  <script type="text/html" id="actImg">
			<a href="javascript:show_pic('actImg{{d.id}}')"><img src='${sysConfig.webUrl}{{ d.pic }}' id='actImg{{d.id}}' style="width:35px;height:35px" alt='' ></a></div>
 		  </script>
 		  <script type="text/html" id="actruleImg">
			<a href="javascript:show_pic('actruleImg{{d.id}}')"><img src='${sysConfig.webUrl}{{d.rulepic}}' id='actruleImg{{d.id}}' style="width:35px;height:35px" alt='' ></a></div>
 		  </script>
    </body>
</html>