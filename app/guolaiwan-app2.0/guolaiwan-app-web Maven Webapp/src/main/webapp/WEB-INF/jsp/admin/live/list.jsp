<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/image";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>过来玩管理平台系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<style type="text/css">
.layui-table img {
	max-width: 1000px;
} /* 照片的最大宽度  */
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>直播管理</cite></a>
	</div>
	<div class="x-body">
		<xblock> <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a> </xblock>
			
		<xblock>
		<button type="button" class="layui-btn layui-btn-normal" onclick="addlive('添加直播','add','500','600')">添加直播</button>
		</xblock>	
		<table id="liveList" lay-filter="liveList"></table>
		
	</div>



	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"
		charset="utf-8"></script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"
		charset="utf-8"></script>
	<script>
	
	layui.use(['element','layer','laytpl','upload','laydate','form','table'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
        form = layui.form;//分页
        upload = layui.upload;
        laydate = layui.laydate;
        table = layui.table;

        getLiveList();
      
        //编辑
        table.on('edit(liveList)',function(obj){
        	layer.load();
        	var data = obj.data
        	,field = obj.field
        	,value = obj.value;
			console.log(value);
        	$.ajax({
        		type:'post',
        		url:'edit1.do',
        		data:{'id':data.id,'field':field,'value':value},
        		success:function(msg){
        		console.log(msg);
        			layer.closeAll("loading");
        			if(msg=="success"){
        				layer.msg("修改成功！");
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
                    str = "停播";
                    val = 'STOP';
                }else{
                    str = "封号";
                    val = 'FORBID';
                }
                console.log(str)
                $.ajax({
                    type:'post',
                    url:'edit.do',
                    data:{'id':this.id,'value':val},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           getLiveList();
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
                    str = "停播";
                    val = 'LIVING';
                }else{
                    str = "开播";
                    val = 'STOP';
                }
                console.log(str)
                $.ajax({
                    type:'post',
                    url:'edit.do',
                    data:{'id':this.id,'value':val},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           getLiveList();
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }
                })
                layer.tips(this.value+' : '+str,obj.othis);
              });
              
              
              form.on('switch(enablered)', function(obj){
                layer.load();
                var str;
                var bl = obj.elem.checked;
                var val;
                if(bl){
                    str = "发红包";
                    val = 1;
                }else{
                    str = "取消红包";
                    val = 0;
                }
                console.log(str)
                $.ajax({
                    type:'post',
                    url:'edit2.do',
                    data:{'id':this.id,'value':val},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           getLiveList();
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }
                })
                layer.tips(this.value+' : '+str,obj.othis);
              });
              
		});
		
		
		
	
		

		
		
		
	//获取列表
	function getLiveList(){
		table.render({
			elem:'#liveList'
   			,method:'post'
   			,url:'list.do'
   			,page:true
      		,limits: [10,30,50,100]
      		,limit: 10
   			,cols: [[
        		{type:'checkbox'}
        		,{field: 'id',title: '直播Id',align:  'center',sort: true,width:120}
        		,{field: 'liveName',title: '直播名称',align:  'center',sort: true,width:120,edit: 'text'}
        		,{field: 'leshiyunId',title: '直播平台Id',align:  'center',sort: true,width:120,edit: 'text'}
        		,{field: 'pubName',title: '发布名称',align:  'center',sort: true,width:220,edit: 'text'}
        		,{field: 'amountRed',title: '红包总数剩余(分)',align:  'center',sort: true,width:220,edit: 'text'}
        		,{field: 'maxRed',title: '最大红包额度(分)',align:  'center',sort: true,width:220,edit: 'text'}
        		,{field: 'redName',title: '红包名称',align:  'center',sort: true,width:220,edit: 'text'}
        		,{field: 'isOpenRed',title: '是否红包',align:  'center',sort: true,width:180,templet:'#isredornot'}
        		,{field: 'liveStatusType',title: '开播/停播',align:  'center',sort: true,width:180,templet:'#startandstop'}
        		,{field: 'delectMessage',title: '评论管理',align:  'center',sort: true,width:180,templet:'#zsgc'}
        		,{field: 'sendMessage',title: '直播推送通知',align:  'center',sort: true,width:180,templet:'#zbtstz'}
        		,{title: '红包宣传图',templet:"#redpicTpls",width:100}
                ,{title: '礼物缩略图',templet:"#picTpls",width:100}
        		,{title: '操作',templet:'#zsgcTpl',width:150}
   			]]
		})
	}
	function addlive(title,url,w,h){
		x_admin_show(title,url,w,h);
	}
	//添加
	function addBiao(title,url,w,h){
		$.ajax({
			url:'add.do',
			type:'post',
			success:function(msg){
				console.log(msg);
				layer.closeAll('loading');
				if(msg=='success'){
					getLiveList();
				}	
			}
		})
		return false;
	}
	function open_win(title,url,w,h){
		x_admin_show(title,url,w,h)
	}

	//评论弹出层
	 function stock_show(title,url,id,w,h) {
	  
		   var index = layer.open({
		     type: 2,
		     area: [w+'px', h +'px'],
			  fix: false, //不固定
			  maxmin: true,
			  shadeClose: true,
			  shade:0.4,
			  title: title,
			  content: url+"?liveId="+id
			}); 
		   layer.full(index);
		 } 	

	//删除
	function del(uuid,cType){
		layer.confirm("真的要删除么？",function(index){
			layer.close(index);
			if(cType=='总公司'){
				layer.msg("总公司不能删除！",{icon:5});
				return false;
			}
			layer.load();
			$.ajax({
				url:'del.do',
				type:'post',
				data:{'uuid':uuid},
				success:function(msg){
					console.log(msg);
					layer.closeAll('loading');
					if(msg=='success'){
						layer.msg("删除成功！");
						getCompanyList();
					}	
				}
			})
		})
	}
	
	function deleteComment(liveId){
	
	   $.ajax({
				url:'delComment.do',
				type:'post',
				data:{'liveId':liveId},
				success:function(msg){
					console.log(msg);
					layer.closeAll('loading');
					if(msg=='success'){
						layer.msg("已清除");
						
					}	
				}
	   })
	}
	
	
	 /*打开地址窗口*/
            function open_win(title,url,w,h){
				x_admin_show(title,url,w,h)								
			}
	
	 function show_picc(id){
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


<script type="text/html" id="zsgc">
	<a title="评论管理" href="javascript:;" onclick="stock_show('评论管理','<%=path%>/admin/live/liveDelect','{{d.id}}','','')" class="layui-btn layui-btn-xs">评论管理</a>
    <a title="全部清除" href="javascript:;" onclick="deleteComment({{d.id}})" class="layui-btn layui-btn-xs">全部清除</a>
</script>
<script type="text/html" id="zbtstz">
	<a title="推送通知编辑" href="javascript:;" onclick="stock_show('推送通知编辑--直播间：{{d.id}}','<%=path%>/admin/live/addWxMessage','{{d.id}}','','5100')" class="layui-btn layui-btn-xs">推送通知编辑</a>
</script>
	
     <script type="text/html" id="startandstop">
	<input type="checkbox" name="liveStatusType" id='{{d.id}}' value='{{ d.liveStatusType }}'  lay-skin="switch" lay-text="开播|停播" lay-filter="enable1" {{ d.liveStatusType == 'STOP'||d.liveStatusType == 'FORBID' ? '' : 'checked' }} >
</script>
<script type="text/html" id="isredornot">

	<input type="checkbox" name="isOpenRed" id='{{d.id}}' value='{{ d.isOpenRed }}'  lay-skin="switch" lay-text="红包|取消" lay-filter="enablered"  {{ d.isOpenRed == '0' ? '' : 'checked' }}>
</script>



	<script type="text/html" id="picTpl">
	<a href="javascript:show_pic('comImg{{d.id}}')"><img src='${sysConfigPO.webUrl}{{ d.pic }}' id='comImg{{d.id}}' style="width:35px;height:35px" alt='' ></a></div>
</script>

	<script type="text/html" id="levelTpl">
	 {{#  if(d.cType === '总公司'){ }}
     	<span style="color: #F581B1;">{{ d.cType }}</span>
  	 {{#  } else { }}
     {{ d.cType }}
     {{#  } }}
</script>

<script type="text/html" id="picTpls">
 <a href="javascript:show_picc('caIm{{d.id}}')"><img id="caIm{{d.id}}"  src= "https://glw-old-file.oss-cn-beijing.aliyuncs.com/file{{ d.cover}}" alt="" style="width:35px;height:35px"></a>
</script>

<script type="text/html" id="redpicTpls">
 <a href="javascript:show_picc('redcaIm{{d.id}}')"><img id="redcaIm{{d.id}}"  src= "https://glw-old-file.oss-cn-beijing.aliyuncs.com/file{{ d.redCover}}" alt="" style="width:35px;height:35px"></a>
</script>


<script type="text/html" id="zsgcTpl">
	
	<a class='layui-btn layui-btn-xs' href="javascript:open_win('选择礼物图片','<%=path%>/admin/picture/addlist?sImg=caIm{{d.id}}&sId={{ d.id }}&source=live2','600','500')">选择礼物图片</a>
    <a class='layui-btn layui-btn-xs' href="javascript:open_win('选择红包图片','<%=path%>/admin/picture/addlist?sImg=redcaIm{{d.id}}&sId={{ d.id }}&source=live3','600','500')">选择红包图片</a>
				
</script>

</body>
</html>