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
<title>确认订单</title>
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
	background:#fff !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	
	text-decoration: none !important;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
}
.ps span{
 margin:0 2%;
 color:#818181;
}
.header {

	height: 40px;

	line-height: 40px;

	background-color: #18b4ed;

	color: #fff;

	border-bottom: 1px solid #bababa;

}
.header-content {

	
	width: 100%;

	position: absolute;

	left: 0;

	top: 0;

	padding-left: 40px;

	padding-right: 40px;

	text-align: center;

	z-index: 0;

}
.header .link-left {

	margin-left: 10px;

	margin-right: 10px;

	position: relative;

	z-index: 1;

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

<body>


		<div class="header">

			<div class="wrapper">

				<a class="link-left" href="#side-menu"><span

					class="icon-reorder icon-large"></span></a>

				<div class="header-content">商品详情</div>

			</div>

		</div>
     <div style="height:270px;width:96%;border-radius:6px;margin:50px auto 0;position: relative;top:3px;">
        <p style="height:50px;line-height: 50px;text-align: center;font-size:18px;font-weight:bold;">请衣着整齐，平视屏幕，并正对光源</p>
          <img id="uploadImages" style="width:180px;height:180px;position: absolute;left:50%;margin:10px 0 0 -90px;opacity: 0.6" alt="" src="lib/images/renlian1s.jpg">
          
     </div>
     <p class="ps" style="text-align: center;"><span>不能配戴眼镜</span><span>不能遮挡脸部</span><span>不能仰头俯拍</span></p>
     <div style="width:100%;height:100px;margin:80px auto;text-align: center; ">
     <p style="color:#818181;margin:20px;">请确保是本人操作</p>
      <button style="color:#fff;background:#EC6D1E;width:80%;height:60px;border:none;outline:none;font-size:16px;z-index:1111111111;" id="confirm" >立即确认</button>
     </div>
      


<script type="text/javascript">

var base;
$(document).on('click','#uploadImages',function(){


		    //人脸采集部分

		    var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");

            var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;

			$.get(_uri, null, function(data){

				data = parseAjaxResult(data);

				if(data === -1) return;

				if(data){

				    

					share=data;

					wx.config({

			            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。

			            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。

			            appId : share.appId, // 必填，公众号的唯一标识

			            timestamp : share.timestamp, // 必填，生成签名的时间戳

			            nonceStr : share.nonceStr, // 必填，生成签名的随机串

			            signature : share.signature,// 必填，签名，见附录1

			            jsApiList : ['chooseImage',

		                        'previewImage',

		                        'uploadImage',

		                        'downloadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2

	       	        });

                }

            }); 

            

            

             wx.ready(function () {

                wx.checkJsApi({

                    jsApiList: [

                        'chooseImage',

                        'previewImage',

                        'uploadImage',

                        'downloadImage'

                    ],

                    success: function (res) {

                      

                        if (res.checkResult.getLocation == false) {

                            alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');

                            return;

                        }else{

                            choosePic(this.id);

                        }

                    }

                });

            });

            wx.error(function(res){

                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

                alert("验证失败，请重试！");

                wx.closeWindow();

            });

		

		});

		

		function choosePic(id) {

            wx.chooseImage({

                count: 1, // 默认9

                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有

                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有

                success: function (res) {

                    $.toast("照片处理中...", "loading");

                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片

                    getLocalData(localIds[0]);

                }

            });

        }

		

        function getLocalData(localid) {

			//获取本地图片资源

            wx.getLocalImgData({

                localId: localid, // 图片的localID

                success: function (res) {

                    var localData = res.localData; // localData是图片的base64数据，可以用img标签显示
                    var str=new String();
                    var arr=new Array();
                    str=localData ;
                    var sear=new RegExp(',');
                      if(sear.test(str)) {
                        arr=str.split(',');//注split可以用字符或字符串分割
						$('#uploadImages').attr('src','data:image/png;base64,'+arr[1]);
						base=arr[1];

                       }else{
                            $('#uploadImages').attr('src','data:image/png;base64,'+localData);
                            base=localData;
                       }                 
                  
                    photos[id]=localData;

                }

            });

 

        }

$(document).on('click','#confirm',function(){
               var _uri = window.BASEPATH + 'face/facerecognition';
                    var params = {};
                    params.merchantid=${merchantid};  
                    params.localData="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";                                 
		          $.post(_uri, params, function(data){
		          if(data.msg==1){
		            
		             ydNow(data.orderId,data.userid);		             
		          }		 		        			   
					
			});       
	});
	
	
	
	
	
  function ydNow(orderNo,userid){

	       var _uriYd = window.BASEPATH + 'phoneApp/order/ydNow';

		   var params={};


		   params.userId=userid;

		   params.orderNo=orderNo;

			$.post(_uriYd, $.toJSON(params), function(data){


				data = parseAjaxResult(data);

               
				if(data === -1) return;

				

				if(data){

				    if(data=='TESTED'){

				       $.toast("验单通过");
				
                       succeed(orderNo);
				       setTimeout(function(){

				   

					       location.href=window.BASEPATH + "pubnum/admin/orderinfo?orderId="+orderNo;

					   

					   },1000);

				    }else{

				       $.toast("验单未通过");

				    }

				}

			});

	    }
	
	
function succeed(orderNo){
	   var _uriYd = window.BASEPATH + 'pubnum/succeed?oderId='+orderNo;	   	       
      
	  $.get(_uriYd, null, function(data){
	       
	    });
	 }
	
</script>



</body>
</html>