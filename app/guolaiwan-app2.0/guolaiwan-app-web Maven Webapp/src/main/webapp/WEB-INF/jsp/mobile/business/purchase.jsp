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
<title>购票</title>
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
	background-color: #DFDFDF !important; 
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


 
  .gotop {
	    position: fixed;
	    right: 20px;
	    bottom: 50px;
	    display: block;
	    width: 50px;
	    height: 50px;
	    opacity: 0.8;
	    z-index:111111;
	}
	.jieshao ul li p{
	 margin:0;
	 font-weight: bold;
	}

	.jieshao ul li{
	 line-height: 40px;
	 border-bottom:1px solid #DFDFDF;
	}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>

<script type="text/javascript">



   
	$(function() {
	 getallteam(); 
	 getRecomment();
	 getMerchantInfo();
	var iscollect;
	  window.BASEPATH = '<%=basePath%>';
	  var comCode='${comCode}';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
	
	  if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		    getloca();
	  } else {
		    if(comCode=='0000'){
			    comCode='0001';
			}
			getCom();
            getRecomment();
		    getModal();
			getActivityBundle();
			initSharewx();
	  }
	
	  
	  
		
	 
	
	  var loca={};
	  function getloca(){
	      
		  var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	
		  var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
		    $.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
					loca=data;
					
				}
				
		  });
	  
	  }
	  
	  
	 
	  
	  function getCity(la,lo){
	  
	     $.ajax({  
            url: 'http://api.map.baidu.com/geocoder/v2/?ak=yPjZB3eElPXn7zXRjcfqGCze6LCPlkmn&callback=renderReverse&location=' + la + ',' + lo + '&output=json&pois=1',  
            type: "get",  
            dataType: "jsonp",  
            jsonp: "callback",  
            success: function (data) {  
                console.log(data);  
                var province = data.result.addressComponent.province;  
                var cityname = (data.result.addressComponent.city);  
                var district = data.result.addressComponent.district;  
                var street = data.result.addressComponent.street;  
                var street_number = data.result.addressComponent.street_number;  
                var formatted_address = data.result.formatted_address;  
                if(comCode=='0000'){
				    if(district.indexOf('平谷')!=-1){
					    comCode='1003';
					    $('#headerName').html('全域休闲');
					    $('#phone').html('010-89991991');
					}else{
					    comCode='0001';
					}
				}
				
				
				getCom();
                getRecomment();
			    getModal();
				getActivityBundle();
				initSharewx();
            }  
        });  
	  
	  }
	  
	  function getCom(){
	     var _uricoms = window.BASEPATH + 'pubnum/getComs';
		
		 $.get(_uricoms, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data && data.length>0){
				    var html=[];
				   
					for(var i=0; i<data.length; i++){
					    if(data[i].comCode==comCode){
					        $('#selCom').html(data[i].comName);
					    }
					    	
					    html.push('<li><a data="'+data[i].comCode+'" href="javascript:void(0)" class="comSel">'+data[i].comName+'</a></li>');
					    
					}
					$('#com').append(html.join(''));
				}
				
		  });
	  
	  }
	  

      /**/
		
	  function getRecomment(){
	      var _uriMerchantInfo = window.BASEPATH + 'phoneApp/merchantInfo?merchantID=${productMerchantID}&userId=${userId}';
		
		$.get(_uriMerchantInfo, null, function(data){
			data = parseAjaxResult(data);
			merchantName = data.shopName + '-过来玩';
			merchantPic = 'http://<%=weburl%>/file/' + data.shopHeading;
			merchantUrl = window.location.href;
			if(data === -1) return;
			if(data){
			    var html=[];
			    var pics=data.shopMpic.split(',');
				for(var i=0; i<pics.length; i++){
					var str = pics[i].split('.');
					if(str[3]!="mp4"&&str[3]!="MP4"){ 
					html.push('<div class="swiper-slide" style="height:200px;"><img class="exampleImg" style="height:200px;" id="imgTest" src="'+pics[i]+'" alt=""></div>');
					}else{
					html.push('<div class="swiper-slide" style="height:200px;"><video class="exampleImg" style="height:200px;width:100%;" src="'+pics[i]+'" controls="controls" ></div>');
					}
				}
			    $('.header-content').html(data.shopName);
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			    });
			    }
			    });
	  }
		

	    var share={};
	    function initSharewx(){
	        var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	  
	    	var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			    $.get(_uri, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					    
						share=data;
						
					}
					
			});
	    
	    }
	    
	  

	
	});
</script>
<script>
$(function(){
 
  })
 /*返回顶部  */
  $(function(){
	$(window).scroll(function(){
		if($(window).scrollTop()>100){
			$(".gotop").fadeIn(400);	
		}
		else{
			$(".gotop").fadeOut(400);
		}
	});
	$(".gotop").click(function(event){
        event.preventDefault();
		$('html,body').animate({'scrollTop':0},500);
        return false;
	});
});
    var page = 2;
    //下拉加载
    $(window).scroll(function(){
　　			//判断是否滑动到页面底部
		     if($(document).height()<=$(window).scrollTop()  + $(window).height()){

				
		           // TODO 滑动到底部时可请求下一页的数据并加载，加载可使用append方法
		        var pageNumber=page;			
				var date={ "merchantId":${productMerchantID},"pageNum":pageNumber}				
				$.post('<%=basePath%>product/package/list', date, function(data){							   			 									
					if(data.productPOs.length>0){
					  getLine(data);
					  $('.weui-loadmores').hide();
					 $('.weui-loadmore').show();
					 setTimeout(function(){$('.weui-loadmore').hide()},1000);
					
					page+=1;
						}
						else{						
						$('.weui-loadmore').hide();
						  $('.weui-loadmores').show();
				          setTimeout(function(){$('.weui-loadmores').hide()},1000);
						}
					});
			     }
			});


	function getLine(data){
	         var html=[];
                 var list = data.productPOs;
                 var  str = data.strArryList;                                                                                 
	       //后面的
		         for(var i=0;i<list.length;i++){
                var pro_pro = list[i].productName.split(",");                               
         		var html=[];       		
		           html.push('<div class="youxuan"  style="width:48%;border-radius:6px;height:auto;float:left;margin:10px 1%;background:#fff;position: relative;overflow: hidden;">');
		           html.push('<div class="goupiao" style="position: relative;width:100%;height:180px;border:none;border-left:none;border-right:none;margin:0 auto;">');
		         if(pro_pro[1] == "act"){
		           html.push('<img id="mg-'+list[i].id+'" style="height:150px;width:100%;border-radius:6px;vertical-align: middle;display: inline-block;" src="http://www.guolaiwan.net/file/'+list[i].productShowPic+'" onclick="commodity(this.id,1)"/> ');
		           html.push('<div class="youxuan-in">');		           	           
		           html.push('<p  id="p-'+list[i].id+'" onclick="commodity(this.id,1)" style="font-size:16px;margin:10px 0 0 3%;font-weight:bold;">'+pro_pro[0]+'</p>'); 
		           html.push('<p style="font-size:12px;margin:10px 0 0 3%;"><span>距您739m</span><span style="color:#fff;background:#EC6D1E;float:right;margin-right:15px;border-radius:16px;padding:0 5px;">活动</span></p>');
		           }else{
		           html.push('<img id="mg-'+list[i].id+'" style="height:150px;width:100%;border-radius:6px;vertical-align: middle;display: inline-block;" src="http://www.guolaiwan.net/file/'+list[i].productShowPic+'" onclick="commodity(this.id,0)"/> ');
		           html.push('<div class="youxuan-in">');		           	           
		           html.push('<p  id="p-'+list[i].id+'" onclick="commodity(this.id,0)" style="font-size:16px;margin:10px 0 0 3%;font-weight:bold;">'+pro_pro[0]+'</p>'); 
		           }
		           html.push('<p style="font-size:12px;"><span style="color:#EC6D1E;font-size:16px;float:left;margin:10px 0 0 3%;">￥'+str[i]+'</span><span style="float:right;margin-top:10px">已售100+</span><span style="color:#EC6D1E;float:right;margin-top:10px;">5.0分</span>   </p>');
		           html.push('</div></div></div>');
		          
		 		$('#contents').append(html.join(''));
	     	}
		       
	    }
    
 function getallteam(){
     var _uricoms = window.BASEPATH + '/product/package/list?merchantId='+${productMerchantID}+'&pageNum=1';	
     $.get(_uricoms, null, function(data){  
           var list =  data.productPOs;
           var  str = data.strArryList;          
         for(var i=0;i<list.length;i++){
                var pro_pro = list[i].productName.split(",");                             
         		var html=[];       		
		           html.push('<div class="youxuan"  style="width:48%;border-radius:6px;height:auto;float:left;margin:10px 1%;background:#fff;position: relative;overflow: hidden;">');
		           html.push('<div class="goupiao" style="position: relative;width:100%;height:180px;border:none;border-left:none;border-right:none;margin:0 auto;">');
		         if(pro_pro[1] == "act"){
		           html.push('<img id="mg-'+list[i].id+'" style="height:150px;width:100%;border-radius:6px;vertical-align: middle;display: inline-block;" src="http://www.guolaiwan.net/file/'+list[i].productShowPic+'" onclick="commodity(this.id,1)"/> ');
		           html.push('<div class="youxuan-in">');		           	           
		           html.push('<p  id="p-'+list[i].id+'" onclick="commodity(this.id,1)" style="font-size:16px;margin:10px 0 0 3%;font-weight:bold;">'+pro_pro[0]+'</p>'); 
		           html.push('<p style="font-size:12px;margin:10px 0 0 3%;"><span>距您739m</span><span style="color:#fff;background:#EC6D1E;float:right;margin-right:15px;border-radius:16px;padding:0 5px;">活动</span></p>');
		           }else{
		           html.push('<img id="mg-'+list[i].id+'" style="height:150px;width:100%;border-radius:6px;vertical-align: middle;display: inline-block;" src="http://www.guolaiwan.net/file/'+list[i].productShowPic+'" onclick="commodity(this.id,0)"/> ');
		           html.push('<div class="youxuan-in">');		           	           
		           html.push('<p  id="p-'+list[i].id+'" onclick="commodity(this.id,0)" style="font-size:16px;margin:10px 0 0 3%;font-weight:bold;">'+pro_pro[0]+'</p>'); 
		           }
		           html.push('<p style="font-size:12px;"><span style="color:#EC6D1E;font-size:16px;float:left;margin:10px 0 0 3%;">￥'+str[i]+'</span><span style="float:right;margin-top:10px">已售100+</span><span style="color:#EC6D1E;float:right;margin-top:10px;">5.0分</span>   </p>');
		           html.push('</div></div></div>');
		          
		 		$('#contents').append(html.join(''));
	     	}
	 });
}

  function  getMerchantInfo(){
     var mer_path = window.BASEPATH +'/product/package/merInfo';
     var mer_da = {"merchantId":${productMerchantID}};
     $.post(mer_path,mer_da,function(msg){       
          if(msg === undefined) return;          
          var merpo  =  msg.merpos;
          var proinfo = msg.prosinfos;  
          
                   
          var html = [] ;
          html.push('<ul>');
          html.push('<li><p style="font-size:18px;">'+merpo.shopName+'</p></li>');
          html.push('<li ><p id="_pro_name" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:90%;">');        
          html.push('</p></li>');
          html.push(' <li><p><span style="font-size:18px;color:#EA6B1F;">'+msg.grade+'分</span><span style="margin:0 5px;color:#DFDFDF;">|</span><span>好评率'+msg.feedback+'%</span></p></li>');
          html.push('<li><p>开放时间：'+msg.beginTime+'-'+msg.endTime+'</p></li>');
          html.push('<li><p><img style="width:25px;height:25px;" src="lib/images/dingweis.png">地址：'+merpo.shopAddress+'</p></li>');
          html.push('</ul>');
          $(".jieshao").append(html.join(''));  
          for(var i = 0 ;i <proinfo.length;i++){           
          var htms = [];         
          
           htms.push('<span >'+proinfo[i].productName+'</span>,');                         
          $("#_pro_name").append(htms.join(''));  
          }             
     })  
  }
   function commodity(id,state){
   var proId = id.split("-");
   if(state == '0'){   
    location.href=window.BASEPATH + '/product/package/commodity/jump?merchantId='+${productMerchantID}+'&proId='+proId[1]+'&choice=0'; 
    }else{
    location.href=window.BASEPATH + '/product/package/commodity/jump?merchantId='+${productMerchantID}+'&proId='+proId[1]+'&choice=1';
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
		
		
		
	
		<div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		</div>
		
	</div>     
	     
	    <div class="jieshao" style="height:auto;width:100%;padding:0 5%;background: #fff;">
	        
	     </div>
	     
	  
	     
               <!--  购票  -->
	           <div id="contents" ></div>	 	        	    
          <!-- 置顶 -->
			<div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/hometop.png"></a></div>
        
         <div class="weui-loadmore" hidden="hidden" style="position:fixed;bottom: 5%;left:18%;z-index: 10000">
			  <i class="weui-loading"></i>
			  <span class="weui-loadmore__tips">正在加载</span>
	</div>
	<div class="weui-loadmores" hidden="hidden" style="position:fixed;bottom: 7%;left:50%;margin-left:-40px;z-index: 10000">
			  <span class="weui-loadmore__tips">没有内容了</span>
	</div>
	</div>	
	
   
</body>




</html>