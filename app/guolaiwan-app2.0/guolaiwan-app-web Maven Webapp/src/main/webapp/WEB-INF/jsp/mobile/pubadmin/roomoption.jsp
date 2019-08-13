<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl=WXContants.Website;
%>
<!DOCTYPE HTML>
<html lang="zh-cmn-Hans">
<head>
<!-- 声明文档使用的字符编码 -->
<meta charset='utf-8'>
<!-- 优先使用 IE 最新版本和 Chrome -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- 页面描述 -->
<meta name="description" content="" />
<!-- 页面关键词 -->
<meta name="keywords" content="" />
<!-- 网页作者 -->
<meta name="author" content="name, email@gmail.com" />
<!-- 搜索引擎抓取 -->
<meta name="robots" content="index,follow" />
<!-- 为移动设备添加 viewport -->
<meta name="viewport"
	content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<!-- `width=device-width` 会导致 iPhone 5 添加到主屏后以 WebApp 全屏模式打开页面时出现黑边 http://bigc.at/ios-webapp-viewport-meta.orz -->
<!-- iOS 设备 begin -->
<meta name="apple-mobile-web-app-title" content="标题">
<!-- 添加到主屏后的标题（iOS 6 新增） -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- 是否启用 WebApp 全屏模式，删除苹果默认的工具栏和菜单栏 -->

<meta name="apple-itunes-app"
	content="app-id=myAppStoreID, affiliate-data=myAffiliateData, app-argument=myURL">
<!-- 添加智能 App 广告条 Smart App Banner（iOS 6+ Safari） -->
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<!-- 设置苹果工具栏颜色 -->
<meta name="format-detection" content="telphone=no, email=no" />
<!-- 忽略页面中的数字识别为电话，忽略email识别 -->
<!-- 启用360浏览器的极速模式(webkit) -->
<meta name="renderer" content="webkit">
<!-- 避免IE使用兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 不让百度转码 -->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- 针对手持设备优化，主要是针对一些老的不识别viewport的浏览器，比如黑莓 -->
<meta name="HandheldFriendly" content="true">
<!-- 微软的老式浏览器 -->
<meta name="MobileOptimized" content="320">
<!-- uc强制竖屏 -->
<meta name="screen-orientation" content="portrait">
<!-- QQ强制竖屏 -->
<meta name="x5-orientation" content="portrait">
<!-- UC强制全屏 -->
<meta name="full-screen" content="yes">
<!-- QQ强制全屏 -->
<meta name="x5-fullscreen" content="true">
<!-- UC应用模式 -->
<meta name="browsermode" content="application">
<!-- QQ应用模式 -->
<meta name="x5-page-mode" content="app">
<!-- windows phone 点击无高光 -->
<meta name="msapplication-tap-highlight" content="no">
<title>住宿-选房</title>
<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<style type="text/css">
a {
	cursor: pointer !important;
}

a, a:link, a:active, a:visited, a:hover {
	color: inherit;
	text-decoration: none;
}

html, body {
	width: 100%;
	min-height:auto;
	background:#E0E0E0 !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	
	text-decoration: none !important;
}

* {
	box-sizing: border-box;

	text-decoration: none;
}
/* 页面样式 */
.header {
	height: 40px;
	line-height: 40px;
	background-color: #18b4ed;
	color: #fff;
	border-bottom: 1px solid #bababa;
}

.header .link-left {
	margin-left: 10px;
	margin-right: 10px;
	position: relative;
	z-index: 1;
}

.header-content {
	height:auto;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	padding-left: 40px;
	padding-right: 40px;
	text-align: center;
	z-index: 0;
}

  .swiper-container {
    width: 100%;
    padding:0;
    margin:0;
    height:200px;
  } 

  .swiper-container img {
    display: block;
    width: 100%;
  }
    
.weui-navbar{
 display: none !important;
}
  .inp::-webkit-input-placeholder{
        text-align: center;
}  

.nav p{
margin:0 0.5%;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"charset="utf-8"></script>
 <script src="<%=request.getContextPath() %>/layui/js/x-layui.js"charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript">
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
		    //开始日期 
			laydate.render({ 
			elem: '#useDate1' 
			//指定元素uscDate 
			,done:function(value){
			 //当前日期
			  var todayDate = new Date();
			  var nowDate = new Date(formatDate(todayDate,0));
			  //选择开始日期
			  var inRoomDate = new Date(value);
			  if($("#uscDate2").val() == ""){
			   if(inRoomDate < nowDate){
			     $.toast("日期不能少于当前日期", "forbidden");
			     setTimeout(function(){ $("#useDate1").val("")},2000);
			     }			    
			   }else{
			     var outRoomDate = new Date($("#uscDate2").val()); 
			     if(inRoomDate >= outRoomDate ){
			      $.toast("开始日期不能大于结束日期", "forbidden");
			      setTimeout(function(){
			       $("#useDate1").val("");
			       $("#uscDate2").val("");
			      },2000);			     			   				  
			     }else{  
			      roomlist($("#tier").val(),$("#identity").val());
			    }			   		   
			}
		  }
		}); 
			
			//结束日期
			laydate.render({ 
			elem: '#uscDate2' 
			//指定元素 
			 ,done:function(value){
			   if( $("#useDate1").val() == ""){
			   $.toast("请先选择入店时间", "forbidden");
			   setTimeout(function(){ $("#uscDate2").val("")},2000);
			   }else{
			     var outRoomDate = new Date(value);
			   	 var inRoomDate = new Date($("#useDate1").val());	
			     if(outRoomDate <= inRoomDate){
			     $.toast("离店时间应大于入店时间", "forbidden");
			     setTimeout(function(){ $("#uscDate2").val("")},2000);
			     }else{				      	     
			      roomlist($("#tier").val(),$("#identity").val());   			     
			     }
			   } 			   			   
			 }
			});
        });

  $(function() {
  
  	 $("#useDate1").val(formatDate(new Date(),0));
     $("#uscDate2").val(formatDate(new Date(),1)); 
     //根据条件查询列表
     roomlist($("#tier").val(),$("#identity").val()); 
      	
	 $(document).on('click', '.close', function() {
		$.closePopup();
		})
		
		$(document).on('click', '.closelist', function() {
		    $("#option").fadeOut();	
		    $(".homePage").fadeIn();		    
		})
		
	});
	
	var overall="";
	
	//房间列表
	function roomlist(tier,identity){	
	 $('.main').children().remove(); 
	 var url=window.BASEPATH + 'business/getallroom'; 
		$.post(url,{"merchantId":${merchantId},"tier":tier,"identity":identity,"inRoomDate":$("#useDate1").val(),"outRoomDate":$("#uscDate2").val()},function(data){
		       var room = data.room;
		       overall = room;
		       var roomState = data.roomState;
		       if(room.length == 0){return;}
				var html= [];
				for(var i =0;i<room.length;i++){
					html.push('<div onclick="openoption(this.id)" id="'+room[i].id+'-'+roomState[i]+'" style="background: #fff;width:30%;height: 0;padding-bottom: 30%;border-radius:50%;position: relative;margin:5px 5px;overflow: hidden;display: inline-block;">');															 
					if(roomState[i] == "0"){ html.push('<img style="width:40%;height:40%;position: absolute;left:50%;margin-left:-20%;" src="lib/images/weixuan.png">');} 					
					if(roomState[i] == "1"){ html.push('<img style="width:40%;height:40%;position: absolute;left:50%;margin-left:-20%;" src="lib/images/lvse.png">');} 					  
					if(roomState[i] == "2"){ html.push('<img style="width:40%;height:40%;position: absolute;left:50%;margin-left:-20%;" src="lib/images/xuanzhong.png">');}  																																						
					html.push('<p style="width:80%;text-align: center;position: absolute;left:50%;top:45%;margin:0 0 0 -40%;"><span>'+room[i].name+'</span>   <span>'+room[i].identity+'</span></p>');
					html.push('<p style="width:80%;text-align: center;position: absolute;left:50%;top:68%;margin:0 0 0 -40%;color:#FB9361;"><span>￥'+(room[i].price/100)+'</span></p>');    
				    html.push('</div>');   
				}
				
				$('.main').append(html.join(''));
		})
	}
	//更改层数
	function changetier(){
		var tier=$("#tier").val();
		var identity=$("#identity").val();
		if($("#identity").val()=="全部"){
			identity="";
		}
		roomlist(tier,identity);
	}
	//更改房间规格
	function changeidentity(){
		var tier=$("#tier").val();
		var identity=$("#identity").val();
		
		roomlist(tier,identity);
	}
	
	//房间操作信息
	function openoption(obj){	 
	   var id = obj.split("-");
		$.actions({
		  actions: [{
		    text: "上架",
		    onClick: function() {
		      change("1",id[0]);
		    }
		  },{
		    text: "下架",
		    onClick: function() {
		      change("0",id[0]);
		    }
		  },{
		    text: "退房",
		    onClick: function() {
		      if(id[1] == '2'){		     
		       noliveRoom("0",id[0]);
		    }else{
		     $.toast("该房间没有被预订", "forbidden");		    
		    }		    
		    }
		  },{
		    text: "房间信息",
		    onClick: function() {
		    if(id[1] == "2"){		     
		     $(".homePage").fadeOut();
		     $("#option").fadeIn();	
		    }else{
		    $.toast("该房间暂无住户信息", "forbidden");		    
		    }		     	     
		    }		  
		  }]
		});
															
	 	//回显住房预定信息				
		if(id[1] == "2"){	
		   $('.roomclient').children().remove();						
			var echo_url = window.BASEPATH + 'admin/room/echoRoomClient';
			var echo_date = {"roomId":id[0],"inRoomDate":$("#useDate1").val(),"outRoomDate":$("#uscDate2").val()}
			 $.post(echo_url,echo_date,function(msg){
			 if(msg.length ==0){return;}
				var names = msg.nameList;
				var phones = msg.phoneList;
				var cards = msg.cardList;
			    var message = msg.message;				   
			    //房间号 和规格
			     var html =[];
			    for(var i = 0;i<overall.length;i++){
			      if(overall[i].id == id[0]){			      
			      html.push('<p class="" style="text-align: center;margin:20px 0;font-weight: bold;"><span>'+overall[i].name+'</span><span>'+overall[i].identity+'</span>入住信息</p>');
			      $(".roomclient").append(html.join(""));
			      }
			    }			    	    
			    //房间住户信息
			    var html2 = [];
			    for(var j = 0; j<names.length;j++){
			     html2.push('<img style="width:35%;float:left;height:100px;" src="'+message[j].facePicWebUrl+'" alt="暂无图片"">');
			     html2.push('<ul style="float:left;width:60%;margin:0 0 20px 2%;line-height:30px;font-size:12px;">');
			     html2.push('<li style="width:100%;height:40px;">入住人姓名：<span style="width:60%;border-bottom:1px solid #A2A2A2;display: inline-block;float:right;">'+names[j]+'</span></li>');
			     html2.push('<li style="width:100%;height:40px;">联系电话：<span style="width:60%;border-bottom:1px solid #A2A2A2;display: inline-block;float:right;">'+phones[j]+'</span></li>');
			     html2.push(' <li style="width:100%;height:40px;">身份证号：<span style="width:60%;border-bottom:1px solid #A2A2A2;display: inline-block;float:right;">'+cards[j]+'</span></li>');
			     html2.push('</ul>');	
			     				     
			    }
			    $(".roomclient").append(html2.join(""));			   				 				 
			})
		} 				
	}
	//退房
	function  noliveRoom(state,id){
	  var noLive_url = window.BASEPATH + 'admin/room/deleteInRoomMessage';
	  var date = {"roomId":id,"inRoomDate":$("#useDate1").val(),"outRoomDate":$("#uscDate2").val()};
	  $.psot(noLive_url,date,function(msg){
	     if(msg == "success"){
	     $.toast("退房成功", "forbidden");	     
	     }else{
	      $.toast("退房失败", "forbidden");
	     }	      
	  })	
	}
	
	//更改状态 并重新刷新列表
	function change(type,id){
		var url=window.BASEPATH + 'admin/room/amend.do'; 
		$.post(url,{"state":type,"id":id},function(){
				roomlist($("#tier").val(),$("#identity").val());
		})
	}
	//设置时间转换格式
	function formatDate(date,obj){
	   var y = date.getFullYear();//获取年
	   var m = date.getMonth()+1;//获取月
	   m = m < 10?'0'+m:m; //判断月是否大于10
	   var d = date.getDate()+obj;//获取日      
	   d=d<10?('0'+d):d;//判断日期是否大于10
	   return y+'-'+m+'-'+d;//返回时间格式 
	  }	 
</script>
<body>
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
		</div>
         <div class="homePage">
          <div class="nav" style="background:#fff;height:50px;width:auto;line-height:50px;text-align: center;font-size:12px;width:100%;font-weight:bold;border-top:2px solid #CECACB;border-bottom:2px solid #CECACB;">
           <p style="display: inline-block;"><span style="color:green;">绿色</span>空闲</p>
	       <p style="display: inline-block;"><span style="color:#A4A2A0;">灰色</span>下架</p>
	       <p style="display: inline-block;"><span style="color:#D13035;">红色</span>已预定</p>
	       <span>楼层：</span>
	       <select class="tier" id="tier" onchange="changetier()"  style="touch-action: none;width:auto;height:30px;padding: 0 1%;border:none;outline:none;text-align: center;margin: 0; text-align-last: center;">
		       <option value="1">1层</option>
		       <option value="2">2层</option>
		       <option value="3">3层</option>
		       <option value="4">4层</option>
		       <option value="5">5层</option>
		       <option value="6">6层</option>
		       <option value="7">7层</option>
		       <option value="8">8层</option>
	       </select> 
	       <span>房型：</span>
	       <select class="identity" id="identity" onchange="changeidentity()" style="touch-action: none;width:auto;height:30px;padding: 0 1%;border:none;outline:none;text-align: center;margin: 0; text-align-last: center;">
		        <option value="全部">全部</option>
		       <option value="标准间">标准间</option>
		       <option value="豪华间">豪华间</option>
		       <option value="三人间">三人间</option>
		       <option value="五人间">五人间</option>
	      </select> 
	      </div>
	      <div style="height:50px;line-height:50px;text-align: center;font-size:12px;width:100%;">
	        <p style="display: inline-block;width:40%"><span>入住时间：</span><input type="text" placeholder="yyyy-mm-dd"   style="cursor: pointer;width:50%;height:25px;padding:0 2%;display: inline-block;border-radius:12px;border:1px solid #A2A2A2;background:#E0E0E0;" class="layui-input" id="useDate1"></p>
	        <p style="display: inline-block;width:40%"><span>离店时间：</span><input type="text" placeholder="yyyy-mm-dd"   style="cursor: pointer;width:50%;height:25px;padding:0 2%;display: inline-block;border-radius:12px;border:1px solid #A2A2A2;background:#E0E0E0;" class="layui-input" id="uscDate2"></p>
	      </div>
	      
	      <!-- 房间选择 -->
	      <div class="main" style="width:100%;height:auto;padding:0 5%;">
	       
	        
	      </div>
	      </div>
	      <!-- 住房用户信息 --> 
				<div id="option"  style="height:auto;width:96%;padding:10px 10px;margin:10px auto 0;background:#fff;overflow: hidden;display: none;">
				     <span class="closelist" style="float:right;">关闭</span>
				     <div class="roomclient" style="overflow: hidden;"><div>
				  																
		  </div>	      	      	      		      
</body>

</html>