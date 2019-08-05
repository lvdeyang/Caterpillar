<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>阳光成单系统</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>
  <div class="x-nav">
   <span class="layui-breadcrumb">
    <a><cite>首页</cite></a>
    <a><cite>投票大赛</cite></a>
    <a><cite>标签列表</cite></a>
    <a><cite>${voteModular.modularName}</cite></a>
  </span> 
 		
  <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:getProductList()" title="刷新"><i class="layui-icon" style="line-height:38px">ဂ</i></a>
 <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>


</div>

<div class="x-body">
	<xblock>
		<button class="layui-btn" onclick="show_win('添加商品','bdPro?moId=${voteModular.id}','1000','700')"><i class="layui-icon">&#xe608;</i>添加</button>
		<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>清空</button>
	    <div class="layui-inline">
  			商品Id：
  			<div class="layui-inline">
    			<input class="layui-input" name="pId" id="pId" autocomplete="off">
  			</div>
  			商品：
  			<div class="layui-inline">
    			<input class="layui-input" name="pName" id="pName" autocomplete="off">
  			</div>
  			
  			<button class="layui-btn" data-type="reload" onclick="selectPName(this)" >搜索</button>
	    </div>
	</xblock>
	<table id="productList" lay-filter="productList"></table>
</div>



<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
<script>
	var id = '${voteModular.id}';
  layui.use(['element','layer','laytpl','upload','laydate','laypage','form','table'], function(){
  $ = layui.jquery;//jquery
  lement = layui.element;//面包导航
  layer = layui.layer;//弹出层
  laytpl = layui.laytpl;//模板引擎
  laypage = layui.laypage;//分页
  upload = layui.upload;
  laydate = layui.laydate;
  form = layui.form;
  table = layui.table;

  //获取产品列表
  getProductList();
  
  
  table.on("tool(productList)",function(obj){
	layer.msg("数据请求中",{icon: 16,shade: 0.01})
	var data = obj.data;
	if(obj.event === "del"){
		$.ajax({
			url:"delRel.do",
			type:"post",
			data:{"relId":data.id},
			success:function(msg){
				layer.closeAll("loading");
				if(msg=="success"){
					layer.msg("操作成功！",{icon:1,time:"1000"});
					obj.del();
					$("#account").text(parseInt($("#account").text())-1);
				}else{
					layer.msg("系统错误！",{icon:2,time:"1000"});
				}
			}
		})
	}
  })
  
 	active = {
        reload: function(){
            var pName = $('#pName');
            var pId = $('#pId');
            table.reload('activityRel', {
                page: {
          					curr: 1 //重新从第 1 页开始
        				}
                ,where: {
                    pName: pName.val(),
                    pId: pId.val()
                }
            });
        }
    };
  
  
});


function show_win(title,url,w,h){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: true,
		shadeClose: true,
		shade:0.4,
		title: title,
		closeBtn: 0,
		btn:["确定"],
		yes:function(index){
			 getProductList();
			layer.close(index);
		},
		maxmin:false,     //关闭最小按钮最大按钮
		success: function(layero){
  			layero.find('.layui-layer-btn').css('text-align', 'center')
		},                //确定按钮居中
		content: url
	});
}

 function product_info(title,url,id,w,h) {
    x_admin_show(title,url+id,w,h); 
  }

function getProductList(){
  table.render({               
    elem:'#productList'
    ,method:'post'
    ,url:'productList.do'
    ,where:{"moId":id}
    ,page:true
    ,limits: [10,30,50,100]
    ,limit: 10
    ,cols: [[
    {type:'checkbox'}
    ,{field: 'productId', title: '商品Id',sort: true,width:80} 
    ,{field: 'productName', title: '商品名称',sort: true,width:180} 
    ,{field: 'peoplevotenum', title: '群众投票',sort: true,width:180} 
    ,{field: 'judgesvotenum', title: '评委投票',sort: true,width:180} 
    ,{field: 'ordernum', title: '销售量',sort: true,width:180} 
    ,{fixed: 'right',title: '操作',width:230,toolbar:'#zsgc',unresize:true} 
    ]]
    ,id:'activityRel'
    ,done:function(res, curr, count){
    	$("#account").text(count);
    }
    
  });
}

function selectPName(obj){
	var type = $(obj).data('type');
    active[type] ? active[type].call(this) : '';
}

function open_win(title,url,w,h){
	x_admin_show(title,url,w,h);
}


function delAll(){

	layer.confirm("真的要清空所有的商品吗？",function(){
		
	layer.msg("数据请求中",{icon: 16,shade: 0.01})
	$.ajax({
		type:"post",
		url:"delAll.do",
		data:{"moId":id},
		success:function(msg){
			layer.closeAll("loading");
			if(msg=="success"){
				layer.msg("操作成功！",{icon:1,time:1000},function(){
					getProductList();
				})
			}else{
				layer.msg("系统错误！",{icon:2,time:1000});
			}
		}
	})
	})
}



</script>
<script type="text/html" id="zsgc">
	<a class="layui-btn layui-btn-primary layui-btn-xs" href="javascript:open_win('产品详情','<%=path%>/admin/product/infoId?id={{d.productId}}','','510');">详情</a>
	<a class="layui-btn layui-btn-xs" href="gotojudgesvotemsg?productId={{ d.id }}">评委评分</a>	
	<a class="layui-btn layui-btn-xs" lay-event="del">不参与活动</a>
</script>
<script type="text/html" id="content">
	${content}
</script>


</body>
</html>