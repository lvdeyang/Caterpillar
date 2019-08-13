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
<title>美食大赛pc</title>
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
    background:#fff;
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
}
.zong{
    overflow: hidden;
}
 
* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	touch-action: none;
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
	height: auto;
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
.xia button{
background:#C3181E;
color:#fff;
font-weight: bpld;
padding:0 15px;
border:none;
outline:none;
border-radius:6px;
box-shadow:0 3px #D50931;
font-size:12px;
font-weight:bold;
margin:5px 5%;
}

.di::before{
  content:"";
  position:absolute;
  left:0;
  bottom:-40px;
  width:100%;
  height:30px;
  box-sizing:border-box;
  border-bottom:1px solid deeppink;
  transform-origin:bottom center;
  transform:rotateZ(140deg) scale(1.414);
  /* animation:slash 5s infinite ease; */
}

/* .lists img{
width:30px;
height:30px;
margin:0 5px;
} */

.nav{
   height:15em;
   width:100%;
   background-image: url('lib/images/dasaibeijing.jpg');
  background-size:100% 100%;
  position: relative;
}

.logo-in,.logo-on,.logo-ou{
    color: #C3181E;
    font-weight: bold;
    font-size:4.5em;
    font-family: "Just Another Hand",cursive;
    text-transform: uppercase;
}

/*定义选项卡内容*/
.contentt{
	display: none;
	width:auto;
	height:auto;
}
/*显示动态选项卡*/
.active{
	display: block;
}
.tab-btn li{
	list-style: none;
	float: left;
	width: 14%;
	height:auto;
	font-weight: bold;
	line-height: 2em;
	font-size: 3em;
	text-align: center;
	margin:0 0 20px 2.5%;
	color:black;
	
}
.tab-btn li img{
 width:100%;
}
.btn-active{
	/* background: orange; */
	z-index:11111;
	color:#FFEF39 !important;
	
}

 .xiaoguo{
width:40px;
height:40px;
}
table tr th{
background: #C3181E;
color:#fff;
border:none;
text-align: center;
height:3em;
width:30%;
}
table tr td{
border:none;
border:0.05em solid #CF4D50;
height:3em;
}          
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/layui/js/x-layui.js"charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath()%>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script>
var productId="${productId}";
$(function() {
	getproduct();
})

	function getproduct(){
		var url=window.BASEPATH + 'judges/getoneproduct';
		$.post(url,{"productId":productId},function(data){
			list(data.id);
			$("#name").html(data.productName);
			$(".name").html(data.productName);
			$(".pic").attr('src', "http://www.guolaiwan.net/file"+data.productShowPic);
		})
	}
//时间格式化
	function fmat(time){
		var da = time;
	    da = new Date(da);
	    var year = da.getFullYear()+'年';
	    var month = da.getMonth()+1+'月';
	    var date = da.getDate()+'日';
		return year+month+date+"";
	}

	//展开数据
	function list(id){
		var url=window.BASEPATH + 'admin/vote/showjudges';
			$.post(url,{"productId":id},function(data){
				var html=[];
				 html.push('<table border="1"  style="font-size:2em;text-align: center;margin:0 auto;border-collapse:   separate;   border-spacing:3px; width:100%;border:none;">'); 
				 html.push('<tr>'); 
			     html.push('<th>日期</th>'); 
			     html.push('<th>评委姓名</th>'); 
			     html.push('<th>分数</th>'); 
			  	 html.push('</tr>'); 
		     	for(var i=0;i<data.all.length;i++){
		     	 var time=fmat(data.all[i].updateTime);
			  	 html.push('<tr>'); 
			     html.push('<td>'+time+'</td>'); 
			     html.push('<td>'+data.all[i].username+'</td>'); 
			     html.push('<td>'+data.all[i].score+'</td>'); 
			     html.push('</tr>'); 
		     	}
		     	 html.push('<tr>'); 
		   		 html.push('<td>平均分</td>'); 
		  	  	 html.push('<td colspan="2">'+data.score+'</td>'); 
		         html.push('</tr>'); 
				 html.push('</table>'); 
		        $('.lists').children().remove();
		        $('.lists').append(html.join(''));
	        })
		}
  
</script>
<script>
	$(function(){
		setInterval("test()",3000);
	})
     function test() {
     	var url=window.BASEPATH + 'admin/vote/selectshowproduct';
         $.post(url,{"optionId":"${optionId}"},function(data){
         	if(data.length==0){
         		window.history.back(-1); 
         	}else{
         		list(productId);
         	}
         })
     }
     
</script>

<body>
     	<div class="nav">
        <img style="width:10%;position:absolute;left:3%;top:10px; " src="lib/images/zhengfu.png">
        <p style="height:2em;line-height: 2em;width:100%;font-size:2.5em;text-align: center;color:#C3181E;">2019中国·遵化</p>
         <p class="logo-in" style="vertical-align: inherit;text-align: center;">美食节大赛评选活动</p>
         <p style="text-transform:uppercase;text-align: center;font-size:1.5em;color:#C3181E;">food festival competition selection activities</p>
       </div>
		   
		<div class="zong" style="width:100%;height:auto;margin:0 auto;overflow: hidden;position: relative;padding:0 5%;">  
			  <!-- 选项卡 -->    
			  <div class="tab" >
					<ul class="tab-btn active" id="menu">
						
					</ul>
				
					<div class="contentt-box" style="" >
							<div class="contentt active" style="text-align: center;">
							
							</div>
							
							<div class="contentt" style="text-align: center;">
			
							</div>
				  		
			        </div> 
	  
	          </div>
	   </div>

		 
		 
        <div  style="width:100%;height:auto;text-align: center;font-weight: bold;">
          <p class="logo-on" style="line-height:2em;color:black;font-size:3em;">当前评分菜品</p>
          <p id="name" class="logo-ou" style="line-height:2em;font-size:3em;"></p>
          <img class="pic" style="width:35%;border-radius:1em;float: left;margin-left:12%;" src="lib/images/ceshide.jpg">
   			<div class="lists" style="width:35%;float: right;margin-right:12%;">
   			
   			</div>
   		</div>
	    
	  	<div style="height: 200px"></div>
	   
	   
</body>
 




</html>