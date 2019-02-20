<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/image";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            轮播图管理
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
              <a><cite>轮播图片</cite></a>
              <a><cite>轮播图管理</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            
            <xblock><button class="layui-btn" onclick="addRevideo()"><i class="layui-icon">&#xe608;</i>添加视频推荐</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
            <table id='revideoList' lay-filter='revideoList' ></table>

        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
           
            layui.use(['element','laypage','layer','laytpl','table','form'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
              table = layui.table;
              form = layui.form;
              //以上模块根据需要引入

              getrevideoList();
              
               //编辑
        table.on('edit(revideoList)',function(obj){
        	layer.load();
        	var data = obj.data
        	,field = obj.field
        	,value = obj.value;
			console.log(value);
        	$.ajax({
        		type:'post',
        		url:'update.do',
        		data:{'id':data.id,'field':field,'value':value},
        		success:function(msg){
        		console.log(msg);
        			layer.closeAll("loading");
        			if(msg=="success"){
        				layer.msg("修改成功！",{icon:1,time:1000});
        			}
        			else{
        				layer.msg("修改失败！",{icon:2,time:3000});
        			}
        		}
        	})
        })
        
        
        //是否显示
              form.on('switch(enable)', function(obj){
                layer.load();
                var str;
                var bl = obj.elem.checked;
                var val;
                if(bl){
                    str = "显示";
                    val = 1;
                }else{
                    str = "不显示";
                    val = 0;
                }
                $.ajax({
                    type:'post',
                    url:'changeIsv.do',
                    data:{'id':this.id,'value':val},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                        	
                           layer.msg("操作成功",{icon:1,time:500})
                        }else{
                           layer.msg("系统错误！",{icon:2,time:3000});
                           $(obj.elem).removeAttr("checked");
                           form.render("checkbox")
                        }
                    }

                })

                layer.tips("在"+this.value+' : '+str,obj.othis);
              });
              return false;
               
            });
			
          
             /*打开地址窗口*/
            function open_win(title,url,w,h){
				x_admin_show(title,url,w,h)
			}

           
            /*删除*/
         	function revideo_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    $.ajax({
                    type:"post",
                    url:"del.do",
                    data:{"id":id},
                    success:function(msg){
                    	if(msg=="success"){
                    		
                    		layer.msg('已删除!',{icon:1,time:1000});
                    		getrevideoList();
                    	}
                    }
                    })
                    
                });
            }  


         function getrevideoList(){
            table.render({
              elem:'#revideoList'
              ,method:'post'
              ,url:'list.do'
              ,page:true
              ,limits: [10,30,50,100]
              ,limit: 10
              ,cols: [[
                {type:'checkbox'}
                ,{field: 'id',title: 'ID',sort: true,width:100}
                ,{field: 'name' ,title: '视频名称',sort: true,edit:'text'}  
                ,{field: 'slideurl' ,title: 'url地址',sort: true,edit:'text'}
                ,{field: 'sort' ,title: '排序',sort: true,edit:'text',width:80}  
                ,{field: 'comName' ,title: '显示位置',sort: true,width:130}
                ,{title: '是否显示',templet:"#switchTpl",width:100}
              	,{title: '操作',sort: true,templet:'#zsgcTpl'}
              ]]
              ,done:function(res, curr, count){
              	console.log(count);
              		$("#allcount").text(count);
              }
              })
            }
            
            
           function addRevideo(){
           		layer.load();
           		 $.ajax({
                	  type:"post",
           			  url:"add.do",
                      success:function(msg){
                      	layer.closeAll("loading");
                        if(msg=="success"){
                          layer.msg("增加成功", {icon: 1,time:500},function () {
                         		getrevideoList();
                           });
                        }
                        //提示
                        else{
                          layer.msg("系统错误",{icon:2,time:3000});
                        }
                       }
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
            </script>
<script type="text/html" id="reCountTpl">
 <span id="reCount{{d.id}}">{{d.reCount}}</span>
</script>
<script type="text/html" id="zsgcTpl">
	
	<a class='layui-btn layui-btn-xs' href="javascript:open_win('选择图片','#','600','500')">选择视频</a>
	<a class='layui-btn layui-btn-danger layui-btn-xs' href="javascript:revideo_del(this,'{{ d.id }}')">删除</a>		
	
</script>
<!-- switch -->
<script type="text/html" id="switchTpl">
	<input type="checkbox" name="enable" class="car{{d.id}}" id="{{d.id}}" value={{d.comName}} lay-skin="switch" lay-text="on|off" lay-filter="enable" {{ d.enable == 1 ? 'checked' : '' }} >
</script>

</body>
</html>