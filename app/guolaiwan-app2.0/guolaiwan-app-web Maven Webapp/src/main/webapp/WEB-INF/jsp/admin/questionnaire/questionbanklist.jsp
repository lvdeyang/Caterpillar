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
            问卷浏览
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
	.layui-form-label{
		display:inline-block !important;
		width:200px !important;
	}
	</style>
	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
    </script>
    <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
    </script>
    </head>
    <body>
    <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>问卷答题</cite></a>
              <a><cite>问卷列表</cite></a>
              <a><cite>问卷题库</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:getquestionbankList()" title="刷新"><i class="layui-icon" style="line-height:38px">ဂ</i></a>
 			<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>

        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="open_win('添加新题目','addquestion?questionnaireId=${questionnaireId}','1000','600')"><i class="layui-icon">&#xe608;</i>添加新题目</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
         	<table id="questionbankList" lay-filter="questionbankList" v></table>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
        
           
        layui.use(['element','layer','laytpl','upload','laydate','form','table'], function(){
	    	$ = layui.jquery;//jquery
	        lement = layui.element;//面包导航
	        layer = layui.layer;//弹出层
	        laytpl = layui.laytpl;//模板引擎
	        form = layui.form;//分页
	        upload = layui.upload;
	        laydate = layui.laydate;
	        table = layui.table; //需要引入
             
              getquestionbankList();
              
              table.on("edit(questionbankList)",function(obj){
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
              			url:"editquestionnaires",
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
	         
	         
	         function getquestionbankList(){
	           
	         	table.render({
            		elem:"#questionbankList"
            		,method:'post'
   					,url:'getquestionbank?questionnaireId=${questionnaireId}'
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field:"topic",title:"题目",edit:"text",align:'center',width:1000}
   						,{field:"questiontype",title:"问题类型",align:'center',width:150,templet:'#status'}
   						,{title:"操作",templet:"#zsgc",width:428}
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
				url:'<%=path%>/questionbank/delquestion',
				type:'post',
				data:{'id':id},
				success:function(msg){
					console.log(msg);
					layer.closeAll('loading');
					if(msg=='success'){
						layer.msg("删除成功！");
						getquestionbankList();
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
    	
    function question_info(title,url,id,w,h) {
	    x_admin_show(title,url+"?id="+id,w,h); 
	}	
    
    function open_win(title,url,w,h){
				x_admin_show(title,url,w,h)
	}
            </script>
          
<script type="text/html" id="zsgc">
<a title="修改" href="javascript:;" onclick="question_info('问题修改','infoquestion','{{ d.id }}','1000','600')" style="text-decoration:none"><i class="layui-icon">&#xe642;</i></a>
<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
</script>
<script type="text/html" id="picTpl">
<img id="caImg{{d.id}}"  src= "http://www.guolaiwan.net/file/{{ d.questionnairePic}}" alt="" style="width:35px;height:35px">
</script>
<script type="text/html" id="status">
    {{#  if(d.questiontype=='1'){ }}
   	单选
    {{#  }if(d.questiontype=='2'){ }}
          多选
    {{# }if(d.questiontype=='3'){ }}
	判断
	{{# } }}
</script>
    </body>
</html>