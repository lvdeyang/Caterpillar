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
<title>住宿选房确认订单</title>
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
	background:#E1E1E1 !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	
	text-decoration: none !important;
}

* {
	box-sizing: border-box;
	list-style: none;
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

.listbox li input{
    width: 100%;
    height: 60px;
    margin:5px auto;
    text-align: right;
    margin-left: 2%;
    padding: 0 10%;
    border-radius:6px;
    border:none;
    background:#fff;
    outline: none;
    border:1px solid  rgb(230, 230, 230);
}
.fukuan{
  background: -webkit-linear-gradient(left,rgba(254,187,56,1),rgba(254,104,33,1)); /* Safari 5.1 - 6 */
}
.homepage_add span{
border-radius:10px;
border:1px solid #FCB735;
padding:3px 20px;
margin:20px 0 0 10px;
display: inline-block;
color:#FCB735;
}
.homepage_add span:hover{
background: #FCB735;
color:#fff;
}
.fuceng{
    position: fixed;
    width:100%;
    height:100%;
    left:0;
    top: 0;
    background-color:rgba(0,0,0,0.6);
    z-index: 10000;

}
.listbox-2 li input{
    width: 100%;
    height: 60px;
    margin:5px auto;
    text-align:center;
  

    border-radius:6px;
    border:none;
    background:#fff;
    outline: none;
    border:1px solid  rgb(230, 230, 230);
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
<script>	
	var room;
	var dayNumber;
 	$(function() {
	     getUserInfo();	 	   	   	   
	     window.BASEPATH = '<%=basePath%>';
	     var parseAjaxResult = function(data){
				if(data.status !== 200){
					$.toptip('data.message', 'error');
					return -1;
				}else{
					return data.data;		
				}
	  	};	  		 
	  	
	  	$(document).on('click', '.change', function(){ 															
		    $(".window-1").fadeIn();
		});
	    $(document).on('click', '.close-window-1', function(){ 
		    $(".window-1").fadeOut();
		});
               
		var inroomdate =new Date("${inRoomDate}");
		var outroomdate =new Date("${outRoomDate}");
		 dayNumber = calculateDate(inroomdate,outroomdate);
		$('#zong').text((${room.price/100}*dayNumber).toFixed(2));
		
    }); 
	 
	 layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  //执行一个laydate实例
          	
	});
	
		 
	//添加信息
	function addInfo(){
	   $("#_name").val(""),
       $("#_phone").val(""),
       $("#_idcard").val(""),
	   $(".window-2").fadeIn();
	   $(".homepage").fadeOut();
	   $(".window-1").fadeOut();
	}	
	   		
	    var prepay_id,paySign,appId,timeStamp,nonceStr,packageStr,signType,orderNo;
	   			  	   	  	
	//购买条件判断	
	var userId = ${userId};
	var clientNumber = []; //所选的用户ID
	$(document).on('click','#nowbuy',function(){
	    if(clientNumber.length==0){
	     $.toast("请添加住宿人信息", "forbidden");
	      return false;
	    }
	           
	    var room_url = window.BASEPATH + 'business/search.do?roomId=${room.id}&inRoomDate=${inRoomDate}&outRoomDate=${outRoomDate}';
	    $.get(room_url,null,function(msg){	    
	       if(msg == "success"){
	         dobuy();
	       }else{
	         $.toast("该房间已被购买,请重新选择!", "forbidden");	       
	       }  	    
	    })	      	     	
	}) 
	
	 //用时间算天数
	 function  calculateDate(sDate1,  sDate2){	       
	  
       iDays  =  parseInt(Math.abs(sDate1  -  sDate2) / 1000 / 60 / 60 /24); 
            
       return  iDays;
	 }
		
	 //设置时间转换格式
	  function formatDate(date){
	   var y = date.getFullYear();//获取年
	   var m = date.getMonth()+1;//获取月
	   m = m < 10?'0'+m:m; //判断月是否大于10
	   var d = date.getDate();//获取日
	   d=d<10?('0'+d):d;//判断日期是否大于10
	   return y+m+d;//返回时间格式 
	  }
	  				 
	 function dobuy(){
		    var param={};
			param.id=${room.id};      //-------------------这玩意有坑 我是room			
			param.userId=${userId};			
			param.numberDays = dayNumber;
			param.paytype='WEICHAT';
			param.source='PUBLICADDRESS';
			param.bookstartDate= "${inRoomDate}";
		   	param.bookendDate = "${outRoomDate}";	   	                   
	        var _uriPay = window.BASEPATH + 'business/addRoomOrder';
				$.post(_uriPay, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;				    
					$.modal({
						  title: "付款方式",
						  buttons: [
						    { text: "余额支付", onClick: function(){ 

						    	$.confirm("确定支付？", function() {
									addMessageOrderId(data.orderId,0);										
								  }, function() {});

						    } }, 					    
						    { text: "微信支付", onClick: function(){ 
							    $.confirm("确定支付？", function() {
							       addMessageOrderId(data.orderId,1);							      							      				

		       													    
								  }, function() {});
						    } },
						    { text: "取消", className: "default", onClick: function(){ } },
						  ]
						});
				});	 	 	 
	 }	
	 
	    //添加下单客户 orderID 
	  function addMessageOrderId(orderId,paytipe){
	    if(clientNumber.length > 0){	   
	        var gather = {};
	        gather.oderId= orderId;
	        gather.clientList = clientNumber;
	        gather.proId =${room.id};
	        gather.userId = ${userId};
	        gather.merchantId =${room.merchantId};
	        gather.bookstartDate = "${inRoomDate}";
	        gather.bookendDate = "${outRoomDate}";
		    var addMessage_url = window.BASEPATH+'business/addMessage';		    
		     $.post(addMessage_url,$.toJSON(gather),function(msg){
		       	if(msg == 'success'){
    	            if(paytipe == '0'){
    	             payByWallet(orderId);
    	            }else{
    	             payPublic(orderId);	
    	            }
		       	}else{
		       	 $.toast("用户信息保存失败", "forbidden");
		       	}	     
		     })		    
		    }else{		    
		     $.toast("住房人信息为空", "forbidden");		    
		    }				  	  
	  }		  
	 
	 
	 /* 钱包购买方法 */
		function payByWallet(orderId){
			var url=window.BASEPATH+'pubnum/wallet/walletbuy';
			var userId=${userId};
			$.post(url,{'orderId':orderId,'userId':userId},function(data){
						data = parseAjaxResult(data);
				if(data==1){
						$.get(window.BASEPATH +"pubnum/order/status?orderId="+orderId, null, function(data){
						    if(data.data=="PAYSUCCESS"){						    	
					           updatemessage(orderId);					            
						        location.href=window.BASEPATH +"business/gotopayment?merchantId=${merchantId}&orderId="+orderId;
						    }
						});
				}else{
					$.alert('您的余额不足！');
				}
			})			
		}
		
		//支付成功后修改身份采集表里的状态 
		function updatemessage(orderId){
		    var _uri = window.BASEPATH + 'product/package/updatemessage';
		          var params = {};		        
		          params.oderId=orderId;		 
			$.post(_uri, params, function(data){					   
					if(data.msg==1){
						alert("保存失败");
					}			        
				});       	    
		}
	 
	 function payPublic(orderId){
			$.get(window.BASEPATH +"business/prev/pay/"+orderId, null, function(data){
				prepay_id = data.prepay_id;
		        paySign = data.paySign;
		        appId = data.appId;
		        timeStamp = data.timeStamp;
		        nonceStr = data.nonceStr;
		        packageStr = data.packageStr;
		        signType = data.signType;
		        orderNo = data.orderNo;
		        callpay();
			});		
		}   		
					
		function callpay(){
		    if (typeof WeixinJSBridge == "undefined"){
		        if( document.addEventListener ){
		            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		        }else if (document.attachEvent){
		            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		        }
		    }else{
		        onBridgeReady();
		    }
		} 
			function onBridgeReady(){
		    WeixinJSBridge.invoke(
		        'getBrandWCPayRequest', {
		           "appId"     : appId,     //公众号名称，由商户传入
		           "timeStamp" : timeStamp, //时间戳，自1970年以来的秒数
		           "nonceStr"  : nonceStr , //随机串
		           "package"   : packageStr,
		           "signType"  : signType,  //微信签名方式：
		           "paySign"   : paySign    //微信签名
		        },
		        
		        function(res){
		            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		                $.confirm("交易成功");
		                //每五秒刷新订单状态					
		                setInterval(function(){ 
                                $.get(window.BASEPATH +"pubnum/order/status?orderId="+orderNo, null, function(data){								    
								    if(data.data=="PAYSUCCESS"){								      							    			

					       
								       location.href=window.BASEPATH +"business/gotopayment?merchantId=${merchantId}&orderId="+orderNo;
								    }
								});
                        }, 1000);
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		                alert("交易取消");  
		            } 
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		                alert(res.err_desc); 
		            }  
		        }
		    );
		} 
			 	  		
  	    //人脸识别生成base64码
  	    
     	var base="";
        $(document).on('click','#photo',function(){

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
        
        //身份证信息采集
        	
		//点击输入框识别图片
		$(document).on('click','#face',function(){
	       discern();
	    });
	 
	    
	    function  discern() {
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
                            choosePicone(this.id);
                        }
                    }
                });
            });
            wx.error(function(res){
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                alert("验证失败，请重试！");
                wx.closeWindow();
            });
	    }
		
		
		function choosePicone(id) {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    $.toast("照片处理中...", "loading");
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    getLocalDataone(localIds[0]);
                }
            });
        }
		
        function getLocalDataone(localid) {

			//获取本地图片资源
            wx.getLocalImgData({
                localId: localid, // 图片的localID
                success: function (res) {
                    var localData = res.localData; // localData是图片的base64数据，可以用img标签显示    
                
                    var _uri = window.BASEPATH + 'pubnum/IdentityCard';
                    var params = {};
                   var str=new String();
                    var arr=new Array();
                    str=localData ;
　               var sear=new RegExp(',');
                     if(sear.test(str)) {
                          arr=str.split(',');//注split可以用字符或字符串分割
                          params.localData=arr[1];
                       }else{
                                      params.localData=localData; 
                         }
                     
                                 
                      
		$.post(_uri, params, function(data){                                                  
			           if(data.msg==0){
						   $("#_idcard").val(data.sfz)
						   $("#_name").val(data.name) 	
						}			   
					     if(data.msg==1){
						     alert("识别失败,请继续上传或者手动输入");
						 }			        
				    });
                }
            });
        }
        
        
      //区分 保存 还是更新 
      var state ="";   
      function  save(){
        if($('#_phone').val()==''){
			   $.toast("请输入手机号", "forbidden");
			   return false;
			}
			if(!(/^1[3456789]\d{9}$/.test($('#_phone').val()))){ 
		       $.toast("手机号码有误，请重填", "forbidden");  
		       return false; 
		    } 
			if($('#_name').val()==''){
			   $.toast("请输入姓名", "forbidden");
			   return false;
			} 
			if($('#_idcard').val()==''){
			   $.toast("请输入身份证号", "forbidden");
			   return false;
			} 
			 var re = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
                      
		    if(re.test($('#_idcard').val())==false){
		       $.toast("身份证号码有误，请重填", "forbidden");  
		       return false;  		    
		    }	
		    
		      if(base==""){
              $.toast("照片获取失败", "forbidden");
			  return false;
             }    	    
                $(".window-2").fadeOut();
                $(".window-1").fadeOut();
			    $(".homepage").fadeIn();
			                 
              var url = window.BASEPATH + 'product/package/add/info';
              if(state == ""){           
              var date = {"name":$("#_name").val(),
                          "phone":$("#_phone").val(),
                          "idcard":$("#_idcard").val(),
                          "facedate":base,
                          "state": 0
                      }
               }else{
                 var date = {"name":$("#_name").val(),
                          "phone":$("#_phone").val(),
                          "idcard":$("#_idcard").val(),                         
                          "facedate":base,
                          "state": state           
              }  
              }            
	          $.post(url, date,function(msg){ 
	          	if(msg == 'success') {
	          	    $(".homepage_add").children().remove();
	          	    $("#window-1-message").children().remove();          	    
	          	    clientNumber.splice(0,clientNumber.length);
	           		getUserInfo();       			           		
	           	}
	         })                                                                                                       
     }	
        //删除用户  	 
	    function deleteClientMessage(id){
	    var merssageId = id.split("-");
	    var url =  window.BASEPATH + 'product/package/deleteClientMessage?merssageId='+merssageId[1]; 
	    $.get(url,null,function(msg){   
	      if(msg=="success"){
	        $(".homepage_add").children().remove();
	        $("#window-1-message").children().remove();          	    
	        clientNumber.splice(0,clientNumber.length);	        
	        getUserInfo();
	        alert("操作成功")
	        }
	     })	 	 	    
	    }    
	     //查询用户信息
	      var clientInfo = " "; 	    
	  	 function  getUserInfo(){
	  	 
	  	  var url_ = window.BASEPATH + 'product/package/user/list';
	  	  $.get(url_,null,function(msg){
	  	  var mesage = msg.message;
	  	  clientInfo = msg.message; 	  
	  	  if(mesage.length==0){return;}
	   
	  	 for(var i = 0; i<mesage.length;i++){	  	    	  	     
	  	     var html = [];  	  	             
             html.push('<div  style="width:100%;height:auto;background: #fff;margin:5px 0;position: relative;border-bottom:1px solid #A6A6A6;text-align: left;padding:10px 10%;">');
             html.push('<input id="input-'+i+'"  onclick="clientMessage(this.id)"  type="checkbox" name="sex" value="1" style="position: absolute;top:30px;left:5%;" />');
             html.push('<P>姓名：'+mesage[i].name+'</P>');
             html.push('<P>手机号：'+mesage[i].phone+'</P>');
             html.push('<P>身份证号：'+mesage[i].number+'</P>');
             html.push('<img id="img1-'+mesage[i].id+'" onclick="deleteClientMessage(this.id)" style="width:28px;height:28px;position: absolute;top:26px;right:15%;border-radius:50%;" src="lib/images/trashss.png">');
             html.push('<img id="'+mesage[i].id+'" onclick="update(this.id)"  style="width:20px;height:20px;position: absolute;top:30px;right:5%;" src="lib/images/xiugai.png">');
			 html.push('</div>');
             $("#window-1-message").append(html.join(''));
			 
	  	  }
	  	  	})  	 	  	 
	  	 }
	  	 	  	 	
 	function update(id){       
	  	state = id;
	  	 var url = window.BASEPATH + 'product/package/update/message?id='+id;
	  	 $.get(url,null,function(msg){
	  	  var mesage = msg.mes;	  	  
	  	  $("#_name").val(mesage.name),
          $("#_phone").val(mesage.phone),
          $("#_idcard").val(mesage.number),
          $(".homepage").fadeOut();
          $(".window-1").fadeOut();
          $(".window-2").fadeIn();  	
	  	}) 
   }           		
   function shutdown(){
    $(".window-2").fadeOut();
    $(".homepage").fadeIn();
    $(".window-1").fadeIn();  
   }    
   
  function clientMessage(id){
    var i = id.split("-");
   if(clientInfo != " "){
			//多选框要用prop 来获取判断
            if($("#"+id).prop("checked")){
            //添加勾选的信息
             clientNumber[i[1]] = clientInfo[i[1]].id;	
			 var htm = [];					
             htm.push('<p style="width:20%;padding:2px 5px;background:#FF9C00;color:#fff;border-radius:12px;margin:2px 5px;display: inline-block;text-align:center;" id="homepage-'+i[1]+'">'+clientInfo[i[1]].name+'</p>');            
             $(".homepage_add").append(htm.join(''));         
            } 
            //取消勾选删除
            if(!($("#"+id).prop("checked"))){
               $("#homepage-"+i[1]).remove();               
               clientNumber.splice(i[1],1);
            }                           
     }         
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
		
<div class="homepage" style="z-index:11111111;">		
	 <!-- 订单信息  -->
	  	<div id="order"  style="width:96%;height:auto;margin:0 auto;background:#fff;position: relative;overflow: hidden;">
	     	
	    </div>
	  
	    <div class="main" style="width:96%;height:120px;border-radius: 10px;background:#fff;padding:10px 10px;margin:10px auto;overflow: hidden;position: relative;">
		   <img  style="width:30%;height:100%;border-radius: 10px;" src="http://www.guolaiwan.net/file/${room.roomimg}"/>
		   <p id="proName" style="position: absolute;top:20px;font-size:14px;left:35%"><span>${merchant.shopName}</span><span>${room.name}包间（${room.identity}）</span></p>
		    <p style="position: absolute;bottom:20px;margin:0;font-size:12px;color:#9A9A9A;left:35%">单价<span style="color:#EA712C;font-size:18px;">￥${room.price/100}</span></p> 
		  </div>  
        <div class="footer" style="width:96%;height:auto;padding:10px 5px;background: #fff;margin:10px auto;overflow: hidden;border-radius:12px;font-weight: bold;"> 
          <p style="height:40px;line-height: 40px;margin:0;width:100%;padding:0 5%;border-bottom:1px solid #D4D6D5;text-align:left;"><span>入住时间</span><span style="float:right;">${inRoomDate}</span></p>
          <p style="height:40px;line-height: 40px;margin:0;width:100%;padding:0 5%;border-bottom:1px solid #D4D6D5;text-align:left;"><span>离店时间</span><span style="float:right;">${outRoomDate}</span></p>
          
        </div> 
	  
      <div class="layui-inline" style="width:96%;height:auto;position: relative;"> <!-- 注意：这一层元素并不是必须的 -->
  			<ul class="listbox" style="color:black;">
		   <!--  <li>
		    <input type="button">
		    <p style="position: absolute;top:25px;left:10%;">优惠券</p>
		    <p style="position: absolute;top:25px;right:7%;"><span>0</span>张可用</p>
		    </li>  -->
		    <li>
		    <input type="button">
		    <p style="position: absolute;top:25px;left:10%;color:#666;">填写身份信息 </p>
		    <p class="change" style="position: absolute;top:25px;right:7%;color:#FCB735;">添加/修改▲</p>
		    <div class="homepage_add" style="height:auto;width:85%;background: #fff;margin:-20px auto 0;padding:20px 0;border-radius:10px;">		   						     
		    </div> 		   
		    </li>
		    
		    </ul>	 
	  </div>
	 <!-- 付款  -->
	 <div   style="background:#fff;height:60px;width:100%;border-bottom:1px solid  rgb(230, 230, 230);border-top:1px solid  rgb(230, 230, 230);position: fixed;bottom:0;">
	     	<p style="height:100%;float:left;text-align:center;width:55%;line-height: 60px;color:#EC6D1E;font-size:20px;font-weight:bold;display: inline-block;">￥<span id="zong"></span></p>	        
	        <p id="nowbuy" style="height:100%;float:right;text-align:center;width:45%;line-height: 60px;color:#fff;font-size:20px;font-weight:bold;display: inline-block;background:#EC6D1E; ">立即购买</p>
	 </div> 
     <div style="height:90px;width:100%;"> </div> 
     
     </div> 
     
     
     <div  class="window-1" style="z-index:11111;width:100%;display: none;height:500px;padding:0 0 50px 0;overflow-x: hidden;text-align: center;background: #fff;position: fixed;bottom:0; ">
	   <p style="text-align:center;width:100%;margin:0 auto;height:40px;line-height: 40px;font-size: 14px;border-bottom:1px solid #D3D3D3;background:#CFCFCF">添加/修改信息<span class="close-window-1" 

style="float:right;color:#fff;margin-right:5%;font-weight:bold;">关闭</span></p>
	   <button onclick="addInfo()" style="width:50%;height:30px;color:#FFA940;border:none;outline:none;background: #fff;border:1px solid #FFA940;margin:20px 0;">添加信息</button>	 
	  <div id="window-1-message"></div>
	  </div>
	  
	  
	  
	<!-- 添加信息 -->  
		<div class="window-2" style="z-index:111111;display: none;">
		<ul class="listbox-2" style="color:black;">
		    <li>
		    <input  id="_name" type="text" placeholder="请输入您的姓名" minlength="4" maxlength="4" style="" >
		    <p    style="position: absolute;top:65px;left:10%;">用户姓名</p>
		    </li>
		    <li>
		     <input id="_phone" class="phone" type="text" placeholder="请输入正确的手机号码" minlength="11" maxlength="11" style="" >
		    <p   style="position: absolute;top:135px;left:10%;">联系电话</p>
		    </li>
		    <li>
		    <input id="_idcard" class="pid"  type="text" placeholder="请输入身份证号码" minlength="18" maxlength="18" style="" >
		     <img id="face" style="height:20px;height:20px;position: absolute;top:205px;right:10%;" src="lib/images/zhaoxiang.png"> 
		    <p  style="position: absolute;top:205px;left:10%;">身份证号码</p>
		    </li>
		 </ul>
		    
<div style="background:#fff;height:370px;width:96%;border-radius:6px;margin:0 auto;position: relative;top:10px;">
          <img id="uploadImages" style="width:140px;height:171px;position: absolute;left:50%;margin:40px 0 0 -70px;" alt="" src="lib/images/renliansss.png">
          <p style="text-align: center;position: absolute;top:250px;left:50%;margin-left:-126px;">请保持正脸，平视屏幕，面部足够清晰。</p>
          <button id="photo" style="position: absolute;left:50%;margin-left:-50px;top:300px;font-size:18px;width:100px;height:35px;color:#fff;font-weight:bold;background:#FFC138;border:none;outline:none;border-radius:10px;">开始拍摄

</button>
          <button onclick="save()" style="position: absolute;left:14%;top:300px;font-size:18px;width:70px;height:35px;color:#fff;font-weight:bold;background:#FFC138;border:none;outline:none;border-radius:10px;">保存</button>
          <button onclick="shutdown()" style="position: absolute;right:14%;top:300px;font-size:18px;width:70px;height:35px;color:#fff;font-weight:bold;background:#FFC138;border:none;outline:none;border-radius:10px;">关闭</button>
      </div>
  </div>    
  </body>






</html>