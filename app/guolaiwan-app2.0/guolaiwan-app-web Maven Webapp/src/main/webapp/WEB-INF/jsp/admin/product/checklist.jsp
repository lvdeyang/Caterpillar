<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>阳光成单系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>产品管理</cite></a>
			<a><cite>产品列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
		style="line-height:1.6em;margin-top:3px;float:right"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>	
	<div class="x-body" id="prosel" >
		<form class="layui-form" >
			城市名称：
			<div class="layui-input-inline">
				<select name="cityCode" id="cityCode">
					<option value=""></option>
					<c:forEach items="${cityList}" var="city">
					<option value="${city.cityCode}">${city.cityName}</option>
					</c:forEach>
				</select>
			</div>
			模块名称：
			<div class="layui-input-inline">
				<select name="modularCode"  id="modularCode" lay-filter="filter">
					<option value=""></option>
					<c:forEach items="${modulars}" var="modular">  
					<option  value="${modular.modularCode}" >${modular.modularName}</option>  
					</c:forEach> 
				</select>
			</div>
			分类名称：
			<div class="layui-input-inline">
				<select name="classCode"  id="classCode">
					<option value=""></option>
					<c:forEach items="${Classes}" var="Class">  
					<option  value="${Class.classCode}" >${Class.className}</option>  
					</c:forEach> 
				</select>
			</div>
			产品名称：
			<div class="layui-input-inline">
				<input class="layui-input" name="productName" >
			</div>
			商家名称：
			<div class="layui-input-inline">
				<input class="layui-input" name="shopName" >
			</div>
			<div align="right" class="layui-inline">
				<button  type="reset"  class="layui-btn layui-btn-primary">重置</button>
				<button  class="layui-btn" lay-filter="getPro" lay-submit>搜索</button>
			</div>
		</form>
	</div>

<div class="x-body">
	<xblock><div align="right"><span  style="line-height:40px">共有数据：<span id="count">${acount}</span>条</span></div></xblock>
	<table  id='productList'  lay-filter='productList'></table>
</div>

<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
<script>
	
	var ipage ;	
	var ilimit;
	var doneCount = 0;
	layui.use(['element','layer','laytpl','upload','laydate','laypage','form','table'], function(){
	$ = layui.jquery;//jquery
    lement = layui.element;//面包导航
    layer = layui.layer;//弹出层
    laytpl = layui.laytpl;//模板引擎
    laypage = layui.laypage;//分页
    upload = layui.upload;
    laydate = layui.laydate;
     table = layui.table;
    var form = layui.form;
   

    getproductList();
    
    //查询按钮
 	form.on('submit(getPro)',function(data){
 		data.field.productAuditstates="D";
 		layer.load();
 		console.log(data.field);
   		getProductBy(data.field);//getProductByC
   		return false;
 	});
 	
 	
 	table.on('tool(productList)',function(obj){
 		var obj = obj.data;
 		
 	
 	})

    return false;

 	/* //模块
 	form.on('select(filter)',function(data){
 		$.ajax({
 			type:"post",
 			url:"getProBymc.do",
 			async:false,
 			data:{"modularCode":data.value},
 			success:function(msg){
 				var sbHtml="<option value=''></option>";
 				classn = msg.classn;
 				console.log(classn);
 				for(i=0;i<classn.length;i++){
 					classn[i].classCode;
 					classn[i].className;
 					sbHtml=sbHtml+"<option value='"+classn[i].classCode+"'>"+classn[i].className+"</option>"
 				}
    		//清空下拉框
    		$("#classCode").html(sbHtml);
    		form.render();
    	}
    })
 	}); */
 	
 	



 });


function getproductList(){
  table.render({               
    elem:'#productList'
    ,method:'post'
    ,url:'checklist.do'
    ,page:true
    ,limits: [10,30,50,100]
    ,limit: 10
    ,skin:'row'
    ,even:true
    ,cols: [[
    {type:'checkbox'}
    ,{field: 'id', title: 'ID',sort: true,width:60} 
    ,{field: 'productName', title: '商品名称',sort: true}  
    ,{field: 'productMerchantName', title: '商家',sort: true} 
    ,{title: '板块',width:160,templet:'#bankuaiTpl'} 
    ,{field: 'productOldPrice', title: '原价',width:100,sort: true} 
    ,{field: 'productPrice', title: '现价',width:100,sort: true} 
    ,{field: 'productCityName', title: '城市',sort: true, width:80}
    ,{field: 'comName', title: '所属公司',sort: true,width:200}
    ,{field: 'updateTime', title: '上传时间',width:200,sort: true} 
    ,{title: '状态',width:100,templet:'#shenheTpl'} 
    ,{fixed: 'right',title: '操作',width:200,templet:'#procaozuoTpl',unresize:true} 
    ]]
  });
}

//查询列表
function getProductBy(data){
	table.render({               
    elem:'#productList'
    ,method:'post'
    ,url:'getProductBy.do'
    ,page:true
    ,where:data
    ,limits: [10,30,50,100]
    ,limit: 10
    ,skin:'row'
    ,even:true
    ,cols: [[
    {type:'checkbox'}
    ,{field: 'id', title: 'ID',sort: true,width:60} 
    ,{field: 'productName', title: '商品名称',sort: true}  
    ,{field: 'productMerchantName', title: '商家',sort: true} 
    ,{title: '板块',width:160,templet:'#bankuaiTpl'} 
    ,{field: 'productOldPrice', title: '原价',width:100,sort: true} 
    ,{field: 'productPrice', title: '现价',width:100,sort: true} 
    ,{field: 'productCityName', title: '城市',sort: true, width:80}
    ,{field: 'comName', title: '所属公司',sort: true,width:200}
    ,{field: 'updateTime', title: '上传时间',width:200,sort: true} 
    ,{title: '状态',width:100,templet:'#shenheTpl'} 
    ,{fixed: 'right',title: '操作',width:200,templet:'#procaozuoTpl',unresize:true} 
    ]]
    ,done: function(res, curr, count){
     layer.closeAll("loading");
   }
  });
}


//弹页面
function show_win(title,url,id,w,h) {
	x_admin_show(title,url+"?uuid="+id,w,h); 
}


function audit(pass,uuid){
	layer.load();
    $.ajax({
    	type:"post",
        url:"audit.do",
        data:{"pass":pass,"uuid":uuid},
        success:function(msg){
        	layer.closeAll("loading");
            if(msg.code=="0"){
                 layer.msg(msg.message, {icon: 1,time:500},function () {
                      // 获得frame索引
                  /* try{
                          	var index = parent.layer.getFrameIndex(window.name);
                          	console.log(index);
                          	//关闭当前frame
                         	parent.window.location.reload();
                          	parent.layer.close(index);
                          }catch(exception){
                          	window.location.reload();
                          } */
                    getproductList();
                   });
                }else if(msg.code=="1"){
                 	layer.msg(msg.message, {icon: 2});
                }else{
                	layer.msg("系统错误！", {icon: 2});
                }
           }
      })
   } 




</script>

<!-- 板块引擎模板 -->
<script type="text/html" id="bankuaiTpl">
  {{ d.productModularCodeName }}&nbsp{{ d.productClassName }}               
</script>
<!-- 审核状态引擎模板 -->
<script type="text/html" id="shenheTpl">
  {{#  if(d.productAuditstatus === '草稿'||d.productAuditstatus === '未通过'){ }}
  <span id="introduce" style="color: #F581B1;">{{ d.productAuditstatus }}</span>
  {{#  }else if(d.productAuditstatus === '待审核'){ }}
  <span id="introduce" style="color: #01AAED;">{{ d.productAuditstatus }}</span>
  {{#  } else { }}
  {{ d.productAuditstatus }}
  {{#  } }}                       
</script>
<script type="text/html" id="procaozuoTpl">
		<a title="详情" class='layui-btn layui-btn-primary layui-btn-xs' href="javascript:show_win('产品详情','info','{{d.uuid}}','','510');" >详情>></a>
		<a title="通过" class='layui-btn  layui-btn-xs' href="javascript:audit('yes','{{d.uuid}}');">通过</a>
		<a title="不通过" class='layui-btn  layui-btn-xs layui-btn-danger' href="javascript:audit('no','{{d.uuid}}');">不通过</a>
</script>

</body>
</html>