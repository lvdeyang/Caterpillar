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
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    <style type="text/css">
	.layui-table img {
		max-width: 1000px;
	} /* 照片的最大宽度  */
	a {
		cursor: pointer !important;
	}
	
	a, a:link, a:active, a:visited, a:hover {
		color: inherit;
		text-decoration: none;
	}
	
	html, body {
		width: 100%;
		min-height: auto;
		background: #fff !important;
		position: relative;
		-webkit-text-size-adjust: none;
		text-decoration: none !important;
	}
	
	* {
		box-sizing: border-box;
		list-style: none;
		text-decoration: none;
	}
	

	
	.edui-toolbar .edui-btn-toolbar {
	width:80% !important;
	height:auto !important;
	}
	.edui-container{
	width:100% !important;
	}
	.layui-input{
	width:100% !important;
	}
	
	
	
</style>
    </head>
      <link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/lang/zh-cn/zh-cn.js"></script>
    <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
    <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
	<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script>
	var overall ;
	$(function() {
	        var html = [];
	        var tier = null;
	        var floor=null;
			$.ajax({
				type : "post",
				url : "getlist.do",
				data:{"merchantId":${merchantId}},
				success : function(msg) {
				msg = msg.po;
				overall = msg;
				for(var i=0; i<msg.length;i++) {
					if(tier==null||tier!=msg[i].tier){
						tier=msg[i].tier;
						if(msg[i].tier==1)floor="一层"; if(msg[i].tier==2)floor="二层"; if(msg[i].tier==3)floor="三层"; if(msg[i].tier==4)floor="四层"; 
						if(msg[i].tier==5)floor="五层"; if(msg[i].tier==6)floor="六层"; if(msg[i].tier==7)floor="七层"; if(msg[i].tier==8)floor="八层";
				    	html.push('<p style=" background-color:#f2f2f2; font-size:18px;font-weight:bold;height:60px;line-height: 60px;">'+floor+'</p> ');
						}
					html.push('<div class="xuanzhong" id="'+msg[i].id+'" style="width:auto;height:auto;text-align: center;margin:20px;display: inline-block;overflow: hidden;z-index:111111;">');
					if(msg[i].state == 0)html.push('<img  style="height:45px;width:50px;" src="../../lib/images/xuanzhong.png">');
					if(msg[i].state == 1)html.push('<img  style="height:50px·;width:50px;" src="../../lib/images/weixuan.png">');
					html.push('<p style="font-size:18px;font-weight:bold;height:50px;line-height:50px;">'+msg[i].name+'</p>');
					html.push('</div>');
				 }
				   $('body').append(html.join('')); 
				}
			})
	
			var um = UM.getEditor('roomdetails');
		
			$(document).on('click', '.xuanzhong', function() {
				for(var i=0; i<overall.length;i++) {
				  if(this.id == overall[i].id){
			       var img="http://www.guolaiwan.net/file"+overall[i].roomimg;
			       document.getElementById("imgurl5").src=img;
			       um.setContent(overall[i].roomdetails);
				   $("#name").val(overall[i].name);
				   $("#inpu").val(overall[i].id);
			       $("#price").val(overall[i].price/100);
			       if(overall[i].isreception=="1")$("#isreception").next().click();
			       if(overall[i].iswifi=="1")$("#iswifi").next().click();
			       if(overall[i].iskettle=="1")$("#iskettle").next().click();
			       if(overall[i].istoilet=="1")$("#istoilet").next().click();
			       if(overall[i].istv=="1")$("#istv").next().click();
			       if(overall[i].isfan=="1")$("#isfan").next().click();
			       var tier = document.getElementsByName("tier");
					for (var o = 0; o < tier.length; o++) {
						    if(overall[i].tier == tier[o].value){
							   $(tier[o]).next().click();
						}
					}
				   var identity = document.getElementsByName("identity");
				   for (var p = 0; p < identity.length; p++) {
						    if(overall[i].identity == identity[p].value){
							   $(identity[p]).next().click();
							}
					}
					

				  }
				}
				$(".xinxi").fadeIn();
			});
			$(document).on('click', '#right', function() {
				$(".xinxi").fadeOut();
			});
			
			$(document).on('click', '#putaway', function() {
			  $.ajax({
					type : "post",
					url : "amend.do",
					data : {"state":1,"id":$("#inpu").val()},
					success : function(data) {
					$("#sty i").trigger('click');
					}	
				}) 
			});
			$(document).on('click', '#below', function() {
			   $.ajax({
					type : "post",
					url : "amend.do",
					data : {"state":0,"id":$("#inpu").val()},
					success : function(data) {
					$("#sty i").trigger('click');
					}	
				}) 
			});
			$(document).on('click', '#quit', function() {
			   $.ajax({
					type : "post",
					url : "amend.do",
					data : {"state":1,"id":$("#inpu").val()},
					success : function(data) {
					$("#sty i").trigger('click');
					}	
				}) 
			});
			
			$(document).on('click', '#delete', function() {
			   $.ajax({
					type : "post",
					url : "delete",
					data : {"id":$("#inpu").val()},
					success : function(data) {
					$("#sty i").trigger('click');
					}	
				}) 
			});
			
		});
	</script>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>房间管理</cite></a>
              <a><cite>添加房间</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" id="sty"title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>
        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="open_win('添加房间','addv?merchantId=${merchantId}','1200','800')"><i class="layui-icon">&#xe608;</i>添加房间</button><span class="x-right" style="line-height:40px"></xblock>
         	<table id="activityList" lay-filter="activityList"></table>
        </div>
      

	<!-- 信息登记 -->
	<div class="xinxi" style="width:70%;height:700px;border:5px solid #393D49;padding:30px 2%;position: fixed;top:50%;left:50%;margin:-350px 0 0 -35%;z-index:11;background:#fff;display: none;overflow: hidden;overflow-y: auto">
		<!-- 加东西从这加 -->
		<form class="layui-form layui-form-pane">
				<input style="display:none;" name="roomId" id="inpu" value="">
           	 	<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                                层                                     
                    </label>
                    <div class="layui-input-block">
                        <input type="radio" name="tier" id="tier" value="1" title="一层"/>
						<input type="radio" name="tier" id="tier" value="2" title="二层"/>
						<input type="radio" name="tier" id="tier" value="3" title="三层"/>
						<input type="radio" name="tier" id="tier" value="4" title="四层"/>
						<input type="radio" name="tier" id="tier" value="5" title="五层"/>
						<input type="radio" name="tier" id="tier" value="6" title="六层"/>
						<input type="radio" name="tier" id="tier" value="7" title="七层"/>
						<input type="radio" name="tier" id="tier" value="8" title="八层"/>
                    </div>
                </div>
                
 				<div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                                                            名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="name" name="name" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                         价钱                                     
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="price" name="price" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                     房间类型                                                                                  
                    </label>
                    <div class="layui-input" >
                        <input type="radio" name="identity" id="identity" value="标准间" title="标准间"/>
						<input type="radio" name="identity" id="identity" value="豪华间" title="豪华间"/>
						<input type="radio" name="identity" id="identity" value="三人间" title="三人间"/>
						<input type="radio" name="identity" id="identity"value="五人间" title="五人间"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                    房间设施                                                                                
                    </label>
                    <div class="layui-input" >
                    	<input type="checkbox" name="isreception" id="isreception" value="1" title="全天前台"/>
						<input type="checkbox" name="iswifi" id="iswifi" value="1" title="wifi"/>
						<input type="checkbox" name="iskettle" id="iskettle" value="1" title="热水壶"/>
						<input type="checkbox" name="istoilet" id="istoilet" value="1" title="卫生间"/>
						<input type="checkbox" name="istv" id="istv" value="1" title="电视"/>
						<input type="checkbox" name="isfan" id="isfan" value="1" title="空调"/>
                
                    </div>
                </div>
                <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 房间详情图 </label>
					<div class="layui-input-inline">
						<table>
							<tr>
							<td>
							<img alt="" src="" style=" height:100px;width:100px " id="imgurl5">
							<input type="hidden" id="img5" name="img5">
							<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img5&img=imgurl5','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
							<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl5','#img5')">删除图片</button>	
							</td>
							</tr>
						</table>
		        	</div>
				</div>
                <div class="layui-form-item">
                    <label for="voterule" class="layui-form-label">
 					房间详情
                    </label>
                    <div class="layui-input-block">
                        <textarea name="roomdetails"  placeholder="请输入内容" class="layui-input" id="roomdetails" style="height:300px">
                        </textarea>
                    </div>
                </div>
				<div id="view"></div> 
				<div class="layui-form-item" style="text-align: center;position: absolute;margin-left: 400px;bottom: -180px">
                    <span class="layui-btn" lay-filter="add" lay-submit> 保存  </span>
					<span class="layui-btn" id="putaway">上架</span>
					<span class="layui-btn" id="below">下架</span>
					<span class="layui-btn" id="quit">退房</span>
					<span class="layui-btn" id="delete">删除</span>
					<span class="layui-btn" id="right">关闭窗口</span>
                </div>
            </form>
	</div>
	<script>
		
		
		layui.use([ 'form', 'layer','laytpl' ], function() {
        		$ = layui.jquery;
        		var form = layui.form,
        			layer = layui.layer,
        			laytpl = layui.laytpl;
        
        		//监听提交
        		form.on('submit(add)', function(data) {
        			console.log(data.field);
	        		layer.load();
        			$.ajax({
        				type : "post",
        				url : "updateroom",
        				data : data.field,
        				success : function(msg) {
        					if(msg=="success"){
        						layer.alert("修改成功",{icon:2,time:1000});
        					$("#sty i").trigger('click');
        					}
        				}
        			})
        
        			return false;
        		});
        });
        
        	//打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
            //打开分类
            function open_win (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
	</script>
</body>
</html>