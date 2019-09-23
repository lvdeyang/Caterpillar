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
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
            
        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="apendquestionnaire()"><i class="layui-icon">&#xe608;</i>添加新问卷</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
         	<table id="questionnaireList" lay-filter="questionnaireList" v></table>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
		function apendquestionnaire(){
			var url="<%=basePath%>/admin/questionnaire/apendquestionnaire"
			$.post(url,null,function(data){
				if(data=="success")getquestionnaireList();
			})
		}
	</script>
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
             
              getquestionnaireList();
              
              table.on("edit(questionnaireList)",function(obj){
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
	         
	         
	         function getquestionnaireList(){
	         	table.render({
            		elem:"#questionnaireList"
            		,method:'post'
   					,url:'getquestionnaires'
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field:"title",title:"问卷标题",edit:"text",align:'center',width:330}
   						,{field:"onthertitle",title:"问卷副标题",edit:"text",align:'center',width:330}
   						,{field:"questionnum",title:"答题数量",edit:"text",align:'center',width:100}
   						,{field:"redpacketnum",title:"红包数量/人（0未开始~-1已结束~-2无限制）",edit:"text",align:'center',width:350}
   						,{field:"questiontime",title:"答题时限/题（秒）",edit:"text",align:'center',width:150}
   						/* ,{title: '问卷背景图',templet:"#picTpl",align:'center',width:100}  */
   						,{title:"操作",templet:"#zsgc",width:250}
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
				url:'delquestionnaires',
				type:'post',
				data:{'id':id},
				success:function(msg){
					console.log(msg);
					layer.closeAll('loading');
					if(msg=='success'){
						layer.msg("删除成功！");
						getquestionnaireList();
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
<%-- <a class='layui-btn layui-btn-xs' href="javascript:open_win('选择背景图','<%=path%>/admin/picture/addlist?sImg=caImg{{d.id}}&sId={{ d.id }}&source=questionnaire','800','600')">选择背景图</a> --%>
<a class="layui-btn layui-btn-xs" href="<%=path%>/questionbank/questionbanklist?questionnaireId={{d.id}}">题库管理</a>
<a class="layui-btn layui-btn-xs" onclick="open_win('添加规则','<%=path%>/admin/questionnaire/gotoquestionrule?questionnaireId={{d.id}}','900','600')" >答题规则</a>		
<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
</script>
<script type="text/html" id="picTpl">
 <a href="javascript:show_pic('caImg{{d.id}}')"><img id="caImg{{d.id}}"  src= "http://www.guolaiwan.net/file/{{ d.questionnairePic}}" alt="" style="width:35px;height:35px"></a>
</script>
    </body>
</html>