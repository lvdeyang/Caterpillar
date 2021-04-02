<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>工会视频管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/x-admin.css"
	media="all">

</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>工会视频管理</cite></a>
		</span> <a class="layui-btn layui-btn-small merflash"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	
	
	<div  id="prosel" class="x-body">
	<form class="layui-form">

	  工会选择：
	  <div class="layui-input-inline" >
	    <select name="companyType"  id="companyType" lay-filter="companyType">
	      <option value="未选择">未选择</option> 
	      <option value="遵化市总工会">遵化市总工会</option> 
          <option value="乡镇（街道）总工会">乡镇（街道）总工会</option>
          <option value="系统工会">系统工会</option>
          <option value="对口单位工会">对口单位工会</option>
          <option value="其他">其他</option>      
	    </select>
	  </div>
	  <div id="companyCell" class="layui-input-inline">
	    <select name="company"  id="company">
	     
	    </select>
	  </div>
	  <div class="layui-input-inline">
	    <select name="status"  id="status">
	       <option value="3">全部</option>
	       <option value="0">审核中</option>
	       <option value="1">审核通过</option>
	       <option value="2">审核拒绝</option>
	    </select>
	  </div>
	<div align="right" class="layui-input-inline">
	 <button  type="reset"  class="layui-btn layui-btn-primary">重置</button>
	 <button class="layui-btn" lay-filter="getPro" lay-submit>搜索</button>
	</div>
	</form>
	</div>
	
	
	<div class="x-body">

		<xblock>

		<span class="x-right lheight4">共有 ${count} 个视频</span></xblock>
		<table class="layui-table" id="videoList"
			lay-filter="videoList"></table>

	</div>
	<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		function video_check(title, url, id, w, h) {
			x_admin_show(title, url + "?id=" + id, w, h);
		}
	
		layui.use([ 'element', 'laypage', 'layer', 'laytpl', 'table' ,'form'], function() {
			$ = layui.jquery; //jquery
			lement = layui.element; //面包导航
			laypage = layui.laypage; //分页
			layer = layui.layer; //弹出层
			laytpl = layui.laytpl; //模板引擎
			table = layui.table;
			form = layui.form;
			//以上模块根据需要引入

	       form.on('select(companyType)', function(data){  
	            initSelCompany();
	            form.render();
	       });
		     //表单提交
		form.on('submit(getPro)',function(data){
		   layer.load();
		   getvideoList(data.field);
		   layer.closeAll("loading");
		   return false;
		 });
	        
			getvideoList({});
	
			//工具
			//分类删除
			table.on('tool(videoList)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
	
				} else if (obj.event === 'del') {
					/*$.ajax({
					  type:"post",
					  url:"del.do",
					  data:{"uuid":obj.data.uuid},
					  success:function(msg){
					    layer.closeAll("loading");
					    if(msg=="success"){
					      layer.msg("删除成功！");
					      obj.del();
					    }
					  }
					})*/
	
				} else if (obj.event === 'edit') {
	
				}
			})
		});
	
	    
	
	    function initSelCompany(){
            $('#company').children().remove();
            var comType=$('#companyType').val();
            var html=[];
            if(comType=='遵化市总工会'){
               html.push('<option value="遵化市总工会">遵化市总工会</option>');
               $('#companyCell').show();
            }else if(comType=='乡镇（街道）总工会'){
               $('#companyCell').show();
               html.push('<option value="遵化镇总工会">遵化镇总工会</option>');
               html.push('<option value="西留村乡总工会">西留村乡总工会</option>');
               html.push('<option value="崔家庄乡总工会">崔家庄乡总工会</option>');
               html.push('<option value="西三里乡总工会">西三里乡总工会</option>');
               html.push('<option value="堡子店镇总工会">堡子店镇总工会</option>');
               html.push('<option value="汤泉乡总工会">汤泉乡总工会</option>');
               html.push('<option value="西下营乡总工会">西下营乡总工会</option>');
               html.push('<option value="兴旺寨乡总工会">兴旺寨乡总工会</option>');
               html.push('<option value="马兰峪镇总工会">马兰峪镇总工会</option>');
               html.push('<option value="东陵乡总工会">东陵乡总工会</option>');
               html.push('<option value="石门镇总工会">石门镇总工会</option>');
               html.push('<option value="平安城镇总工会">平安城镇总工会</option>');
               html.push('<option value="东新庄镇总工会">东新庄镇总工会</option>');
               html.push('<option value="刘备寨乡总工会">刘备寨乡总工会</option>');
               html.push('<option value="新店子镇总工会">新店子镇总工会</option>');
               html.push('<option value="团瓢庄乡总工会">团瓢庄乡总工会</option>');
               html.push('<option value="党峪镇总工会">党峪镇总工会</option>');
               html.push('<option value="地北头镇总工会">地北头镇总工会</option>');
               html.push('<option value="娘娘庄乡总工会">娘娘庄乡总工会</option>');
               html.push('<option value="东旧寨镇总工会">东旧寨镇总工会</option>');
               html.push('<option value="铁厂镇总工会">铁厂镇总工会</option>');
               html.push('<option value="苏家洼镇总工会">苏家洼镇总工会</option>');
               html.push('<option value="侯家寨乡总工会">侯家寨乡总工会</option>');
               html.push('<option value="建明镇总工会">建明镇总工会</option>');
               html.push('<option value="小厂乡总工会">小厂乡总工会</option>');
               html.push('<option value="华明路街道办总工会">华明路街道办总工会</option>');
               html.push('<option value="文化路街道办总工会">文化路街道办总工会</option>');
               
            }else if(comType=='系统工会'){
               $('#companyCell').show();
               html.push('<option value="教育局系统工会">教育局系统工会</option>');
               html.push('<option value="卫健局系统工会">卫健局系统工会</option>');
               html.push('<option value="工信局系统工会">工信局系统工会</option>');
               html.push('<option value="交通局系统工会">交通局系统工会</option>');
               html.push('<option value="水利局系统工会">水利局系统工会</option>');
               html.push('<option value="住建局系统工会">住建局系统工会</option>');
               html.push('<option value="民政局系统工会">民政局系统工会</option>');
               html.push('<option value="税务局系统工会">税务局系统工会</option>');
               html.push('<option value="公安局系统工会">公安局系统工会</option>');
               html.push('<option value="发展改革局系统工会">发展改革局系统工会</option>');
               html.push('<option value="农业农村局系统工会">农业农村局系统工会</option>');
               html.push('<option value="市直机关工会">市直机关工会</option>');
               html.push('<option value="经济开发区工会">经济开发区工会</option>');
               html.push('<option value="手工业联社工会">手工业联社工会</option>');
               html.push('<option value="市供销社工会">市供销社工会</option>');
               html.push('<option value="商业资产管理处工会">商业资产管理处工会</option>');
            }else if(comType=='对口单位工会'){
               $('#companyCell').show();
               html.push('<option value=" 应急局工会"> 应急局工会</option>');
               html.push('<option value="医保局工会">医保局工会</option>');
               html.push('<option value="市场监督管理局工会">市场监督管理局工会</option>');
               html.push('<option value="城管执法局工会">城管执法局工会</option>');
               html.push('<option value="财政局工会">财政局工会</option>');
               html.push('<option value="邮政局工会">邮政局工会</option>');
               html.push('<option value="电力局工会">电力局工会</option>');
               html.push('<option value="广播电视台工会">广播电视台工会</option>');
               html.push('<option value="广电网络工会">广电网络工会</option>');
               html.push('<option value="清东陵文管处工会">清东陵文管处工会</option>');
               html.push('<option value="国际饭店工会">国际饭店工会</option>');
               html.push('<option value="人民保险公司工会">人民保险公司工会</option>');
               html.push('<option value="烟草公司工会">烟草公司工会</option>');
               html.push('<option value="联通公司工会">联通公司工会</option>');
               html.push('<option value="人寿保险公司工会">人寿保险公司工会</option>');
               html.push('<option value="人民银行工会">人民银行工会</option>');
               html.push('<option value="建设银行工会">建设银行工会</option>');
               html.push('<option value="中国银行工会">中国银行工会</option>');
               html.push('<option value="发展银行工会">发展银行工会</option>');
               html.push('<option value="农村信用社工会">农村信用社工会</option>');
               html.push('<option value="工商银行工会">工商银行工会</option>');
               html.push('<option value="农业银行工会">农业银行工会</option>');
               html.push('<option value="栗源公司工会">栗源公司工会</option>');
               html.push('<option value="港陆公司工会">港陆公司工会</option>');
               html.push('<option value="广野公司工会">广野公司工会</option>');
               html.push('<option value="尚禾源工会">尚禾源工会</option>');
               html.push('<option value="唐百工会">唐百工会</option>');
               html.push('<option value="建投热电厂工会">建投热电厂工会</option>');
               html.push('<option value="亚太工会">亚太工会</option>');
               html.push('<option value="永兴建安工会">永兴建安工会</option>');
               html.push('<option value="金卓工会">金卓工会</option>');
               html.push('<option value="宝兑通工会">宝兑通工会</option>');
               html.push('<option value="圣龙水泥厂工会">圣龙水泥厂工会</option>');
               html.push('<option value="德嘉铝业工会">德嘉铝业工会</option>');
               html.push('<option value="亿通团建工会">亿通团建工会</option>');
               html.push('<option value="勇辉健身工会">勇辉健身工会</option>');
               html.push('<option value="龙宇建筑工会">龙宇建筑工会</option>');
               
            }else{
 
            }
            $('#company').append(html.join(''));
            
        }
	   
		//获取商家列表
		function getvideoList(data) {
			table.render({ /*3.引入table*/
				elem : '#videoList',
				method : 'post',
				url : 'videoList.do',
                where:{companyType:$('#companyType').val(),company:$('#company').val(),status:$('#status').val()},
				page : true,
				limits : [ 10, 30, 50, 100 ],
				limit : 10,
				height : 'full',
				cols : [ [
					{
						type : 'checkbox'
					}
					, {
						field : 'id',
						title : 'ID',
						sort : true,
						width : 60
					}
					, {
						field : 'videoName',
						title : '视频名称',
						sort : true,
						width : 200
					}
					, {
						field : 'phone',
						title : '手机号',
						sort : true,
						width : 200
					}
					, {
						field : 'company',
						title : '工作单位',
						sort : true,
						width : 200
					}
					, {
						field : 'name',
						title : '姓名',
						width : 160,
						sort : true
					}, {
						field : 'passedStr',
						title : '审核结果',
						width : 160,
						sort : true
					}
					, {
						fixed : '',
						align : 'center',
						title : '操作',
						width : 120,
						minWidth : 100,
						templet : '#handle',
						unresize : true
					}
				] ]
			});
		}
	</script>
	<script type="text/html" id="handle">  
 
 <a title="审核" href="javascript:;" onclick="video_check('审核','toCheck.do','{{ d.id }}','','580')"
                            class="ml-5" style="text-decoration:none">
     <i class="layui-icon">审核</i>
 </a>
 
</script>
</body>
</html>