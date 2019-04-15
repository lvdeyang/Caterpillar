<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<html>

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
<meta name=”viewport” content=”width=device-width, initial-scale=1″ />
<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<title></title>
<style type="text/css">
html, body {
	height: 100%;
	background:#F2F2F2;
}
.draw {
    position: fixed;
    width: 1px;
    line-height: 1px;
    pointer-events: none;
}
@keyframes floatOne {
   0% {
opacity:1;
   }
50% {
  opacity:1;
  }
  100% {
  opacity:0;
  transform:translate3D(0, -20px, 0) scale(5) rotate(45deg);
  }
}
.btn:active {
  transform: translateY(3px);
}
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>

		
    $(function() {
    
     	 //点击效果
     	var H = 0;
   		$(document).click('mousemove touchmove',function(e) {
		    e.preventDefault();
		    var drawSize = 10;
		    var drawType = '○';
		    var floatType = 'floatOne';
		    var xPos = e.originalEvent.pageX;
		    var yPos = e.originalEvent.pageY;
		    $('body').append('<div class="draw" style=" font-size:'+drawSize+'px;left:'+xPos+'px;top:'+yPos+'px;-webkit-animation:'+floatType+' .9s 1;-moz-animation:'+floatType+' .9s 1;color:#33a0fc;">'+drawType+'</div>');
		    $('.draw').each(function() {
		      var div = $(this);
		      setTimeout(function() {$(div).remove();},800);
		    });
  	  });
  	  
        var uri = window.BASEPATH + 'back/selefeedback';
        var html = [];
		$.post(uri, null, function(data) {
		   data = parseAjaxResult(data);
           for(var i =0;i<data.length;i++){
	         html.push('<div class="main_in" style="display: inline-block;width:100%;margin:5% 0;">');
	         html.push(' <img class="pic" style="width:55px;height:55px;border-radius:50%;float:left;margin:3% 3% 3% 3%;" alt="" src="'+data[i].headportrait+'">');
	         html.push(' <div class="main_on" style="float:right;width:75%;padding-right:3%;margin-top:5%;">');
	         html.push('<span class="pid" style="font-size:16px;font-weight: bold;">'+data[i].username+'</span><br>');
	         html.push(' <p class="pid_a" style="font-size:14px;">'+data[i].content+'</p>');
	         if(data[i].replycontent != null)html.push(' <p class="pid_b" style="font-size:14px;"><span style="color:#19B4ED">回复：</span>'+data[i].replycontent+'</p>');
	         html.push(' </div>');
	         html.push(' </div>');
	         html.push(' <div style="width:100%;border-bottom:1px dashed #C8CCCF;margin:0 auto;text-align: center;"></div>');
          }
          $('.main').append(html.join('')); 
   	    });
    
   		 $(document).on('click','.btn', function() {
   		 var val =$("#text").val();
   		 var reg = new RegExp("\n","g");//g,表示全部替换。
   		  val =  val.replace(reg,"<br>");
   			 if( val != ""){
		        var uri = window.BASEPATH + 'back/addfeedback';
			    var para = {};
			    para.content = val;
   		        $(".btn").css("background","#3ECF70"); 
				$.post(uri, $.toJSON(para), function(data){
                $.showLoading("反馈成功!");
				  window.location.href=window.location.href+"?id="+10000*Math.random();
				});  
		    }else{
	 		   $.toast("请输入反馈信息!", "cancel");
		    }
   		 });  
     
  });


 </script>
<script>
	$(function() {
		var txtAnim = {
			len : 0,
			txtDom : "",
			arrTxt : [],
			init : function(obj) {
				this.obj = obj;
				this.txtDom = obj.innerHTML.replace(/\s+/g, "");
				this.len = this.txtDom.length;
				obj.innerHTML = "";
				this.addDom();
			},
			addDom : function() {
				for (var i = 0; i < this.len; i++) {
					var spanDom = document.createElement("span");
					spanDom.innerHTML = this.txtDom.substring(i, i + 1);
					this.obj.appendChild(spanDom);
					this.arrTxt.push(spanDom);
				}
				;
				for (var j = 0; j < this.len; j++) {
					this.arrTxt[j].style.position = "relative";
				}
				;
				this.strat();
			},
			strat : function() {
				for (var i = 0; i < this.len; i++) {
					this.arrTxt[i].onmouseover = function() {
						this.stop = 0;
						this.speed = -1;
						var $this = this;
						this.timer = setInterval(function() {
							$this.stop += $this.speed; //0 += -1
							if ($this.stop <= -20) {
								$this.speed = 1;
							}
							$this.style.top = $this.stop + "px";
							if ($this.stop >= 0) {
								clearInterval($this.timer)
								$this.style.top = 0 + "px";
							}
							;
						}, 15);
					};
				}
			}
		}
		var txtDom = document.getElementById("txtDom");
		txtAnim.init(txtDom);
	});
</script>
<body>
	<div class="header" style="width:100%;">
		<p style="color:#919191;font-size:18px;padding:3% 3%;text-align: center;">评价</p>
		
	</div>
	 <div class="header_in" style="width:100%;background:#ffffff;">
<p  style="width:92%; overflow: hidden; margin:3% auto;">
		<textarea id="text" maxlength="200" cols="value" 
			placeholder="亲，请您点击输入评论内容～～"
			; style="width:100%;height:150px;outline: none;border:1px solid #c8cccf;border:none;resize:none;font-size:14px;  text-align: justify;"
			type="text" name="" value=""></textarea>
			
			</p>
		
 </div>
	<div class="main" style="width:100%;height:auto;">
		  <div class="mains" style="width:100%;height:50px;background:#ffffff;position:relative;">
		 <p id="txtDom" style="color:#919191;font-size:16px;margin:0 3%;float:left;line-height: 50px;">评论列表</p>
		<button class="btn"
			style="background:#19B4ED;color:#ffffff;outline: none; border:none;width:30%;height:40px;font-size:16px;border-radius: 30px;float:right;position:absolute;top:50%;left:68%;margin-top:-20px;">发表评论</button>
		</div>
		
    
      
        
	</div>
      <p style="text-align: center;font-size:14px;color:#919191;margin:5% 0;">没有更多数据</p>
</body>
</html>
