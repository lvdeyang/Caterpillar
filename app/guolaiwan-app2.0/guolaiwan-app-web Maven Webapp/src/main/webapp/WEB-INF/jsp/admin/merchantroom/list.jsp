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
	.layui-form-label{
	 width:auto !important;
	
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
	layui.use([ 'form', 'layer','laytpl','laydate' ], function() {
        		$ = layui.jquery;
        		var form = layui.form,
        			layer = layui.layer,
        			laytpl = layui.laytpl;
        			laydate = layui.laydate;  
        			      			       		                            
           //房间查询开始日期
                laydate.render({
                 elem: '#test1'
                 ,done:function(value){                               
			      var todayDate = new Date();
			      var beginDate = new Date(value);
			      var nowDate = new Date(formatDate(todayDate,0));
			     if(beginDate < nowDate){
			       layer.alert("日期不能少于当前日期");
			       setTimeout(function(){ $("#test1").val("")},1000);			    
			   }
			    if($("#test2").val() != ""){
			      var endDate = new Date($("#test2").val());			      
                  if(beginDate>=endDate){
                  layer.alert("开始时间不能大于结束时间");
                  setTimeout(function(){ $("#test1").val("");$("#test2").val("")},1000);                 
                  }
                 }			   
			   }
             }); 
                                 
             //房间查询结束日期   
				laydate.render({
                 elem: '#test2'
                 ,done:function(value){
			     if( $("#test1").val() == ""){
			     layer.alert("请先选择开始时间");
			      setTimeout(function(){ $("#test2").val("")},2000);
			      }else{
			     var endDate = new Date(value);
			   	 var beginDate = new Date($("#test1").val());	
			     if(endDate <= beginDate){
			     layer.alert("离店时间应大于入店时间");
			     setTimeout(function(){ $("#test2").val("")},2000);
			     }
			   } 			   			   
			}
          });                       
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
            	
	var overall;
	var roomId;
	   $(function() {	
	        //检索日期默认时间赋值
	        $("#test1").val(formatDate(new Date(),0)); 
	        $("#test2").val(formatDate(new Date(),1));
	         window.BASEPATH = '<%=basePath%>';	        	
	        getlist(); 	
			var um = UM.getEditor('roomdetails');
		   						
			//修改房间信息 关闭按钮
			$(document).on('click', '#right', function() {
				$(".xinxi").fadeOut();
			});
			
			//修改房间信息  上架按钮
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
			
			//修改房间信息   下架按钮
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
									
			//修改房间信息   删除按钮					
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
			
			// 修改房间信息 弹窗
			$(document).on('click', '#updateroom', function() {
			   $(".interlayer").fadeOut();
			   $(".xinxi").fadeIn();
			});
			
			//房间预定 弹窗	
			$(document).on('click', '#reserveroom', function() {
			   $(".interlayer").fadeOut();
			   $(".reserveInfo").fadeIn();
			});
				
			//房间预定和信息修改选择弹窗
			$(document).on('click', '#inlayeColse', function() {
			   $(".interlayer").fadeOut();			  
			});
			
			//房间预定关闭按钮	
			$(document).on('click', '#reserveInfo-close', function() {
			   $(".reserveInfo").fadeOut();	
			   $(".interlayer").fadeOut();
			   		  
			});	
			//查询日期范围内的房间
			$(document).on('click', '#selectRoomlist', function() {
			   $("#bookname").val("");
			   $("#bookphone").val("");	
			   $("#bookcard").val("");
			   $("#date1").val("");	 
			   $("#date2").val("");	   
			   getlist();			   		  
			});
			
		//房间点击事件
		   $(document).on('click', '.xuanzhong', function() {
			     var id = this.id.split("-");
			     roomId=id[0];	
			     //回显房间信息		    
				for(var i=0; i<overall.length;i++) {				  
				  if(id[0] == overall[i].id){
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
				
				//房间预定时间显示
				if(id[1] == 1){
				  $("#bookname").val("");
			      $("#bookphone").val("");	
			      $("#bookcard").val("");
				  $("#nowCacheRoom").attr("disabled",false);
				  $("#deleteCacheRoom").attr("disabled","disabled");				  				  
				  $("#date1").val($("#test1").val());
				  $("#date2").val($("#test2").val());				  
				}	
										
				//回显住房预定信息				
				if(id[1] == 2){	
				 $("#deleteCacheRoom").attr("disabled",false);		
				$("#nowCacheRoom").attr("disabled","disabled");				
				 var echo_url = window.BASEPATH + 'admin/room/echoRoomClient';
				 var echo_date = {"roomId":id[0],"inRoomDate":$("#test1").val(),"outRoomDate":$("#test2").val()}
				 $.post(echo_url,echo_date,function(msg){
				  if(msg.length ==0){return;}
				   var names = msg.nameList;
				   var phones = msg.phoneList;
				   var cards = msg.cardList;
				   $("#date1").val(msg.beginDate);
				   $("#date2").val(msg.endDate)			   
				   var ClientName="";
				   var ClientPhone="";
				   var ClientCard="";
				  for(var i =0;i<names.length;i++){
				     if(i != names.length - 1 ){
				      ClientName += names[i]+",";
				      ClientPhone += phones[i]+",";
				      ClientCard += cards[i]+",";
				     }else{
				      ClientName+=names[i];
				      ClientPhone+=phones[i];
				      ClientCard+=cards[i];
				     }
				   }
				  if(ClientName != "" && ClientPhone != "" && ClientCard !=""){
				   $("#bookname").val(ClientName);
				   $("#bookphone").val(ClientPhone);
				   $("#bookcard").val(ClientCard);
				  } 				 				 
				 })
				}								
				$(".interlayer").fadeIn();
			});	
																		
		});
		
	 //设置时间转换格式
	  function formatDate(date,obj){
	   var y = date.getFullYear();//获取年
	   var m = date.getMonth()+1;//获取月
	   m = m < 10?'0'+m:m; //判断月是否大于10
	   var d = date.getDate()+obj;//获取日      
	   d=d<10?('0'+d):d;//判断日期是否大于10
	   return y+'-'+m+'-'+d;//返回时间格式 
	  }
	  //展示房间信息
	  function 	getlist(){
	     var html = [];
	        var tier = null;
	        var floor=null;
			$.ajax({
				type : "post",
				url : "getlist.do",
				data:{"merchantId":${merchantId},"inRoomDate": $("#test1").val(),"outRoomDate":$("#test2").val()},
				success : function(msg) {
				var po = msg.po;
				var cur = msg.CurrentState;				
				overall = po;
				for(var i=0; i<po.length;i++) {		
					if(tier==null||tier!=po[i].tier){
						tier=po[i].tier;
						if(po[i].tier==1)floor="一层"; if(po[i].tier==2)floor="二层"; if(po[i].tier==3)floor="三层"; if(po[i].tier==4)floor="四层"; 
						if(po[i].tier==5)floor="五层"; if(po[i].tier==6)floor="六层"; if(po[i].tier==7)floor="七层"; if(po[i].tier==8)floor="八层";
				    	html.push('<p style=" background-color:#f2f2f2; font-size:18px;font-weight:bold;height:60px;line-height: 60px;">'+floor+'</p> ');
						}										
					html.push('<div class="xuanzhong" id="'+po[i].id+'-'+cur[i]+'" style="width:auto;height:auto;text-align: center;margin:20px;display: inline-block;overflow: hidden;z-index:111111;">');
					if(cur[i] == 0){html.push('<img  style="height:50px·;width:50px;" src="../../lib/images/weixuan.png">');}
					if(cur[i] == 1){html.push('<img  style="height:45px;width:50px;" src="../../lib/images/lvse.png">');}
					if(cur[i] == 2){html.push('<img  style="height:50px·;width:50px;" src="../../lib/images/xuanzhong.png">');}															 																									
					html.push('<p style="font-size:18px;font-weight:bold;height:50px;line-height:50px;">'+po[i].name+'</p>');
					html.push('</div>');
				 }
				   $('.roombody').children().remove();
				   $('.roombody').append(html.join('')); 
				}
			})	  	  
	  }
	  
	  //立即预定
	  function nowCacheRoom(){
	    //信息判空
	    if($("#bookname").val() == ''){
	     alert("住宿人姓名不能为空");
	     return false;
	    }
	    if($("#bookphone").val() == ''){
	     alert("手机号不能为空");
	     return false;
	    }
	   if($("#bookcard").val() == ''){
	     alert("身份证号不能为空");
	     return false;
	    }
	    var url = window.BASEPATH + 'admin/room/addInRoomMessage';
	    var date = {"roomId":roomId,"name":$("#bookname").val(),"phone":$("#bookphone").val(),"card":$("#bookcard").val(),"inRoomDate":$("#test1").val(),"outRoomDate":$("#test2").val(),"merchantId":${merchantId}}
	    $.post(url,date,function(msg){
	    
	      if(msg == "success"){
	        getlist();
	        alert("保存成功");
	        $(".reserveInfo").fadeOut();	
			$(".interlayer").fadeOut();
	      }	    	    	    
	    })	  
	  }	
	  
	  //取消预订
	  function deleteCacheRoom(){
	   var url =  window.BASEPATH + 'admin/room/deleteInRoomMessage?roomId='+roomId+'&inRoomDate='+$("#test1").val()+'&outRoomDate='+$("#test2").val();
	   $.get(url,null,function(msg){
	     if(msg == 'success'){	     
	         alert("取消预订成功");
	         $(".reserveInfo").fadeOut();	
			 $(".interlayer").fadeOut();
			  getlist();
	     }else{	         
	         alert("取消预订失败");
	     }	   	   
	   })
	  }	
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
             <xblock> <button class="layui-btn" onclick="open_win('添加房间','addv?merchantId=${merchantId}','1200','800')">
             <i class="layui-icon">&#xe608;</i>添加房间</button>    	
         	  <font>&nbsp &nbsp </font>
         	  <div class="layui-inline">
         	      <button type="button" class="layui-btn">开始日期:</button>		    
			      <div class="layui-input-inline">
			        <input type="text" class="layui-input" id="test1" placeholder="yyyy-MM-dd">
			      </div>
			    </div>    
			    <font>&nbsp &nbsp </font>
			    <div class="layui-inline">
			     <button type="button" class="layui-btn">结束日期:</button>			      
			      <div class="layui-input-inline">
			        <input type="text" class="layui-input" id="test2" placeholder="yyyy-MM-dd">
			      </div>
			    </div>
			    <font>&nbsp &nbsp </font>
			    <button id="selectRoomlist" type="button" class="layui-btn">查询</button>
         	 </xblock> 
         	 
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
					<span class="layui-btn" id="delete">删除</span>
					<span class="layui-btn" id="right">关闭窗口</span>
                </div>
            </form> 
	</div>
	<div class="interlayer" style="width:400px;height:200px;border:1px solid black;position: fixed;top:40%;left:50%;text-align: center;margin:0 0 -90px -200px;display: none;">
	
	 <button id="updateroom"  style="color:#fff;width:60%;background: #009688;margin-top:45px;border:none;outline:none;padding:5px 0;">修改房间信息</button>
	 <button id="reserveroom" style="color:#fff;width:60%;background: #009688;margin-top:45px;border:none;outline:none;padding:5px 0;">房间预订</button>
	 
	 <button id="inlayeColse" style="position: absolute;top:10px;left:85%;color:#fff;font-size: 14px;font-weight: bold;background: #009688;border:none;outline:none;padding:2px 5px;border-radius:8px;">关闭</button>
	</div>
	
	<!--房间预定信息添加  -->
	<div class="reserveInfo" style="width:70%;height:600px;border:5px solid #393D49;padding:30px 2%;position: fixed;top:50%;left:50%;margin:-350px 0 0 -35%;z-index:11;background:#fff;display: none;overflow: hidden;overflow-y: auto">
    
    <div class="layui-form-item">
    <label class="layui-form-label" style="font-size: 16px;">姓名:</label>
    <div class="layui-input-block">
      <input type="text" name="bookname" id="bookname" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
    </div>
    </div>
	
	<div class="layui-form-item">
    <label class="layui-form-label" style="font-size: 15px;">手机号:</label>
    <div class="layui-input-block">
      <input type="text" name="bookphone" id="bookphone" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
    </div>
    </div>	
	
	<div class="layui-form-item">
    <label class="layui-form-label" style="font-size: 15px;">身份证:</label>
    <div class="layui-input-block">
      <input type="text" name="bookcard" id="bookcard" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
    </div>
    </div>	
    
    <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">住房开始时间</label>
      <div class="layui-input-inline">
        <input type="text" name="date" id="date1" lay-verify="date" disabled="disabled" autocomplete="off" class="layui-input" >
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">住房结束时间</label>
      <div class="layui-input-inline">
        <input type="text" name="date" id="date2" lay-verify="date" disabled="disabled" autocomplete="off" class="layui-input">
      </div>
    </div>
    </div>
    
    <div style="width:400px;height:200px;position: fixed;top:40%;left:50%;text-align: center;margin:0 0 -90px -200px;">
    <button id="nowCacheRoom" onclick="nowCacheRoom()"  type="button" class="layui-btn layui-btn-primary"  style="text-align: center;">立即预定</button>
    <button id="deleteCacheRoom" onclick="deleteCacheRoom()"  type="button" class="layui-btn layui-btn-primary" style="text-align: center;">取消预定</button>
    <button id="reserveInfo-close" type="button" class="layui-btn layui-btn-primary" style="text-align: center;">关闭</button>
	</div>		
	</div>
	<div class="roombody"></div>
</body>
</html>