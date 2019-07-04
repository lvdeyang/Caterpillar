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
                    params.localData=base; 
 		          $.post(_uri, params, function(data){
		          if(data.msg==1){
		            
		             ydNow(data.orderId,data.userid);		             
		          }		 		        			   
					 if(data.msg==0){
		            
		             alert("人脸不匹配")	             
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