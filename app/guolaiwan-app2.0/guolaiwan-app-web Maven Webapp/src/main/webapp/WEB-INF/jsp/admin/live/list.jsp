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
.layui-table img{     max-width: 1000px;}/* 照片的最大宽度  */
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>直播管理</cite></a>
	</div>
	<div class="x-body">
	<xblock>
		<a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:getCompanyList();" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
		
		</xblock>
		<table id="liveList" lay-filter="liveList"></table>
	</div>
	
	

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
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
        		,{field: 'userId',title: '用户Id',align:  'center',sort: true,width:120}
        		,{field: 'merchantId',title: '商户Id',align:  'center',sort: true,width:120}
        		,{field: 'pubName',title: '发布名称',align:  'center',sort: true,width:120}
        		,{field: 'liveType',title: '主播类型',align:  'center',sort: true,width:120}
        		,{field: 'liveStatusType',title: '状态',align:  'center',sort: true,width:180,templet:'#switchTpl'}
        		,{field: 'liveStatusType',title: '状态',align:  'center',sort: true,width:180}
   			]]
		})
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
<!-- <a class='layui-btn layui-btn-primary layui-btn-xs' href="javascript:open_win('选择城市','chooseC','500','500')">城市</a> -->
<script type="text/html" id="zsgc">
<div class="layui-btn-group">
	<a class='layui-btn layui-btn-primary layui-btn-xs' href="javascript:open_win('选择图片','<%=path%>/admin/company/piclist?&img=comImg{{d.id}}&comId={{ d.id }}','600','500')">选择图片</a>
	<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.uuid }}","{{ d.cType }}")'>删除</a>
</div>
</script>
<script type="text/html" id="switchTpl">
	<input type="checkbox" name="liveStatusType" id='{{d.id}}' value='{{ d.liveStatusType }}'  lay-skin="switch" lay-text="正常|封号" lay-filter="enable" {{ d.liveStatusType == 'FORBID' ? '' : 'checked' }} >
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

</body>
</html>