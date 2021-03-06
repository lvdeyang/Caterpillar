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
            投票参数设置
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
              <a><cite>投票中心</cite></a>
              <a><cite>投票活动列表</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
            
        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="open_win('添加新投票','addoptions','600','500')"><i class="layui-icon">&#xe608;</i>添加新投票</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
         	<table id="voteList" lay-filter="voteList" v></table>
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
              			url:"editoption",
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
              
              
               form.on('switch(enable)', function(obj){
                layer.load();
                var str;
                var bl = obj.elem.checked;
                var val;
                if(bl){
                    str = "开启";
                    val = 'START';
                }else{
                    str = "停播";
                    val = 'STOP';
                }
                console.log(str)
                $.ajax({
                    type:'post',
                    url:'optionstatus',
                    data:{'id':this.id,'value':val,'type':'status'},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           getvoteList();
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }
                })
                layer.tips(this.value+' : '+str,obj.othis);
              });
              
             form.on('switch(enable1)', function(obj){
                layer.load();
                var str;
                var bl = obj.elem.checked;
                var val;
                if(bl){
                    str = "显示";
                    val = 'SHOW';
                }else{
                    str = "隐藏";
                    val = 'HIDE';
                }
                console.log(str)
                $.ajax({
                    type:'post',
                    url:'optionstatus',
                    data:{'id':this.id,'value':val,'type':'logoshow'},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           getvoteList();
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }
                })
                layer.tips(str,obj.othis);
              });
              
	         
	         form.on('switch(enable2)', function(obj){
                layer.load();
                var str;
                var bl = obj.elem.checked;
                var val;
                if(bl){
                    str = "显示";
                    val = 'SHOW';
                }else{
                    str = "隐藏";
                    val = 'HIDE';
                }
                console.log(str)
                $.ajax({
                    type:'post',
                    url:'optionstatus',
                    data:{'id':this.id,'value':val,'type':'titleshow'},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           getvoteList();
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }
                })
                layer.tips(str,obj.othis);
              }); 
              
              form.on('switch(enable3)', function(obj){
                layer.load();
                var str;
                var bl = obj.elem.checked;
                var val;
                if(bl){
                    str = "显示";
                    val = 'SHOW';
                }else{
                    str = "隐藏";
                    val = 'HIDE';
                }
                console.log(str)
                $.ajax({
                    type:'post',
                    url:'optionstatus',
                    data:{'id':this.id,'value':val,'type':'downpicshow'},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           getvoteList();
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }
                })
                layer.tips(str,obj.othis);
              });
              
            });
	         
	         
	         function getvoteList(){
	         	table.render({
            		elem:"#voteList"
            		,method:'post'
   					,url:'alloptions'
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field:"votename",title:"投票名称",edit:"text",align:'center',width:150}
   						,{field:"judgesvote",title:"评委票权重（%）",edit:"text",align:'center',width:150}
   						,{field:"ordervote",title:"销量等同票数（票）",edit:"text",align:'center',width:160}
   						,{field:"pepolevote",title:"群众票等同票数（票）",edit:"text",align:'center',width:170}
   						,{field:"pollnum",title:"票/商品/天/人",edit:"text",align:'center',width:120}
   						,{field:"ordernum",title:"单/商品/人/活动",edit:"text",align:'center',width:130}
   						,{field: 'votestatustype',title: '活动状态',align:'center',width:90,templet:'#startandstop'}
   						,{field: 'logoshow',title: 'logo状态',align:'center',width:90,templet:'#logoshow'}
   						,{field: 'downpicshow',title: '页脚图状态',align:'center',width:90,templet:'#downpicshow'}
   						,{field: 'titleshow',title: '标题状态',align:'center',width:90,templet:'#titleshow'}
   						,{title: 'logo缩略图',templet:"#picTpl",align:'center',width:100} 
   						,{title: '页脚图',templet:"#downpicTpl",align:'center',width:100} 
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
				url:'deloption',
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
<a class='layui-btn layui-btn-xs' href="javascript:open_win('选择logo','<%=path%>/admin/picture/addlist?sImg=caImg{{d.id}}&sId={{ d.id }}&source=option','800','600')">选择logo</a>
<a class='layui-btn layui-btn-xs' href="javascript:open_win('选择页脚图(选择后请刷新页面)','<%=path%>/admin/picture/addlist?sImg=caImg{{d.id}}&sId={{ d.id }}&source=downpic','800','600')">选择页脚图</a>
<a class="layui-btn layui-btn-xs" onclick="open_win('标题','gototitle?optionId={{d.id}}','600','350')" >活动标题</a>	
<a class="layui-btn layui-btn-xs" href="list?optionId={{d.id}}">标签列表</a>	
<a class="layui-btn layui-btn-xs" href="<%=path%>/judges/getjudges?optionId={{d.id}}">评委列表</a>
<a class="layui-btn layui-btn-xs" href="gotovotepics?optionId={{d.id}}">轮播图管理</a>	
<a class="layui-btn layui-btn-xs" onclick="open_win('添加规则','gotovoterule?optionId={{d.id}}','900','600')" >活动规则</a>				
<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
</script>
<script type="text/html" id="startandstop">
 <input type="checkbox" name="votestatustype" id='{{d.id}}' value='{{ d.votestatustype }}'   lay-skin="switch" lay-text="开启|结束" lay-filter="enable" {{ d.votestatustype == 'STOP' ? '' : 'checked' }} >
</script>
<script type="text/html" id="logoshow">
 <input type="checkbox" name="logoshow" id='{{d.id}}' value='{{ d.logoshow }}'   lay-skin="switch" lay-text="显示|隐藏" lay-filter="enable1" {{ d.logoshow == 'HIDE' ? '' : 'checked' }} >
</script>
<script type="text/html" id="titleshow">
 <input type="checkbox" name="titleshow" id='{{d.id}}' value='{{ d.titleshow }}'   lay-skin="switch" lay-text="显示|隐藏" lay-filter="enable2" {{ d.titleshow == 'HIDE' ? '' : 'checked' }} >
</script>
<script type="text/html" id="downpicshow">
 <input type="checkbox" name="downpicshow" id='{{d.id}}' value='{{ d.downpicshow }}'   lay-skin="switch" lay-text="显示|隐藏" lay-filter="enable3" {{ d.downpicshow == 'HIDE' ? '' : 'checked' }} >
</script>
<script type="text/html" id="picTpl">
 <a href="javascript:show_pic('caImg{{d.id}}')"><img id="caImg{{d.id}}"  src= "http://www.guolaiwan.net/file/{{ d.slidepic}}" alt="" style="width:35px;height:35px"></a>
</script>
<script type="text/html" id="downpicTpl">
 <a href="javascript:show_pic('caImg{{d.id}}')"><img id="caImg{{d.id}}"  src= "http://www.guolaiwan.net/file/{{ d.downpic}}" alt="" style="width:35px;height:35px"></a>
</script>
    </body>
</html>