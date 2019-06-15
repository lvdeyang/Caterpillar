<!--
<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
%>-->
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
<style>
  #body{
  background-color : #f2f2f2;
  }
  .body1{
   background-color : #ffffff;
   display:inline-block;
   float: left;
   margin-left:5%;  
   margin-top : 4%;
   width : 43%;
   height: 200px;
  }
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<!-- <script src="../../../../layui/lib/alert/css/alertstyle.css"></script> -->
<script src="../../../../layui/lib/alert/js/jquery-1.7.1.min.js"></script>
<script src="../../../../layui/lib/alert/js/ui.js"></script>
<link href="../../../../layui/lib/alert/css/alertstyle.css"  rel="stylesheet" />
<script type="text/javascript">
var money;
var commodityId; //商品id
     $(function() {
      var _uri = window.BASEPATH + 'integral/integ'; //获取所有坐标点
		$.post(_uri, null, function(data) {
	     	data = parseAjaxResult(data);
	     	var produ = data.product;  
	     	var html = [];
		    for(var i=0; i<produ.length; i++){
		       html.push('<div class="body1" onclick="clickHandler('+produ[i].productPrice+','+produ[i].id+')">');
			   html.push('<img style="width:100%;height:70%;" src="'+produ[i].productShowPic+'">');
			   html.push(' <p style="margin-top: 1%; margin-left: 6%">'+produ[i].productName+'</p>');
			   html.push('<p style="margin-top: 1%; margin-left: 8%"> <span>'+produ[i].productPrice+'</span> 积分</p>'); 
			   html.push('</div>'); 
		    }
		       $('#body').append(html.join(''));
		});
		 $("#address").cityPicker({
        title: "选择地址",
        onChange: function (picker, values, displayValues) {
          console.log(values, displayValues);
        }
      });
     });
     
     function  clickHandler(e,id){
     commodityId = id;
     money = e; 
	     $.modal({
			  text: "花费"+e+"积分兑换积分商品?",
			  buttons: [
			   { text: "到店领取", onClick: function(){ 
			       getcommodity(2);  //到店领取
			   } },
               { text: "快递兑换", onClick: function(){  
		         $('#selAddress').popup(); 
		        /*  $('.modDiv').hide(); */
		         $('#cameraDiv').show();
		         getAllAddr();  
		         } },
               { text: "取消", className: "default", onClick: function(){ } },
			  ]
		}); 
		
    }
    
    //编辑地址
		$(document).on('click','#addAddress',function(){
			    $('.modDiv').hide();
		    $('#addressSecond').show();
		});
		$(document).on('click','#save',function(){
		    if($('#address').val()==''){
			   $.toast("请选择地址", "forbidden");
			   return false;
			}
			if($('#addressphone').val()==''){
			   $.toast("请输入手机号", "forbidden");
			   return false;
			}
			if(!(/^1[34578]\d{9}$/.test($('#addressphone').val()))){ 
		       $.toast("手机号码有误，请重填", "forbidden");  
		       return false; 
		    } 
			if($('#name').val()==''){
			   $.toast("请输入姓名", "forbidden");
			   return false;
			}
		    var _uriAdd = window.BASEPATH + 'phoneApp/address/add';
			var params={};
			params.userId=${userId}; 
			var addresses=$('#address').val().split(' ');
			params.province=addresses[0];
			params.city=addresses[1];
			params.district=addresses[2];
			params.idNum=$('#idNum').val();
			params.consigneeAddress=$('#moreAddress').val();
			params.addressphone=$('#addressphone').val();
			params.consigneeName=$('#name').val();
			$.post(_uriAdd, $.toJSON(params), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				$('.modDiv').hide();
				$('#addressFitst').show();
				getAllAddr();	
			});
	  });
	  
	  function getcommodity(e){  //判断积分够 然后减库存
	    var _uri = window.BASEPATH + 'integral/convertibility'; //获取所有坐标点
		var param = {};
		param.integral= money;
		param.id = commodityId;
		$.post(_uri,  $.toJSON(param), function(data) {
	     	data = parseAjaxResult(data);
	     	if(1 == data){
	     	   dobuy(e);
	     	}else if(0 == data){
	     	  $.toast("您的积分不足", "forbidden");
	        } else{
	          $.toast("库存不足", "forbidden");
	        }
	   });  
	  }
	  
     function getAllAddr(){
        var _uriAddress = window.BASEPATH + 'phoneApp/address/list?userId='+${userId};  
		$.get(_uriAddress, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			if(data && data.length>0){
			    var html=[];
				for(var i=0; i<data.length; i++){
				     var chkattr='';
				     if(i==0){
				         chkattr='checked="checked"';
				     }
					 html.push('<div class="weui-media-box weui-media-box_text mailAddress" id="mailadd-'+data[i].id+'">');
					 html.push('<input style="float:left;height:27px;width:20px"  type="radio" name="radio1" class="" id="radio-'+data[i].id+'" '+chkattr+'>');
			         html.push('<h4 style="width:80%;margin-left:35px;" class="weui-media-box__title">'+data[i].consigneeName+'（'+data[i].consigneePhone+'）</h4>');
			         //html.push('<p class="weui-media-box__desc">身份证'+(data[i].idNum?data[i].idNum:'-')+'</p>');
			         if(data[i].province!=null&&data[i].city!=null&&data[i].district!=null)html.push('<p class="weui-media-box__desc">'+data[i].province+data[i].city+data[i].district+data[i].consigneeAddress+'</p>');
			         html.push('</div>');
				}
				$('#addressList').children().remove();
			    $('#addressList').append(html.join(''));
			}
		});
     }  
	  
	  
	
      
    
    
    	$(document).on('click','#cancelAddress',function(){
	   		$.closePopup();
	  
	    });
	    
	    $(document).on('click','#buynow',function(){
		    getcommodity();
		});
		
		
		
		
	  function dobuy(e){
	        var param={};
	        var spli =$('input[type^=radio]:checked').attr('id');
	        if(spli !=null){
	        var ids = spli.split('-');
		    param.addressId=ids[1];
	        }
			param.productId = commodityId;  //${id} 商品id
			param.productNum=1;
			param.payMoney= money;
			param.userId= ${userId};  
			param.paytype='INTEGRAL';
			param.source="PUBLICADDRESS";
			if(e == null){
		        e = 1; //快递配送
		    }
		    param.comboName = e;
            $.closePopup();
			var _uriPay = window.BASEPATH + 'integral/order/add';
			 $.post(_uriPay, $.toJSON(param), function(data){
				data = parseAjaxResult(data);
				location.href=window.BASEPATH +"integral/order/info?orderId="+data.id; 
			});  
		}  
	    
	    
     
</script>



<body id ="body">
	<div style="width:100%;height:40px;background:#ffffff;text-align: center;line-height: 40px;position:relative;z-index:10;">
			<span style="color:black;font-weight: bold;float:left;line-height: 40px;font-size:20px;margin-left:3%;"><a style="color: black;" href="javascript:history.go(-1)">< </a></span> 	
			<span style="text-align:center;">积分商城</span>
    </div>
	
      
     
      <div id="selAddress" class="weui-popup__container"
				style="padding-bottom:50px;">
				<div class="weui-popup__overlay"></div>
				<div class="weui-popup__modal">
				    <div class="modDiv" id="roomList">
				    
				    </div>
				
					<div class="modDiv" id="addressFitst" style="margin-top:20%;">

						<div class="weui-cells__title" style="color:red;font-weight:bold">点击地址选择或添加新联系人</div>
						<a id="addAddress"
								style="width:96%;font-size:14px;margin-left:2%;background-color:#18b4ed;height:30px;line-height:30px;margin-top:0"
								href="javascript:;" class="weui-btn weui-btn_primary">添加地址</a>
						<div class="weui-panel__bd" id="addressList"
							style="padding-bottom:40px;"></div>

						<div style="width:100%;height:40px;">
							<a id="cancelAddress"
								style="width:47%;font-size:14px;margin-left:2%;float:left;height:40px;line-height:40px;"
								href="javascript:;" class="weui-btn weui-btn_warn">取消购买</a> <a
								id="buynow"
								style="width:47%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
								href="javascript:;" class="weui-btn weui-btn_primary">立即购买</a>
						</div>
					</div>


					<div class="modDiv" id="addressSecond" style="display:none;">

						<div class="weui-cells weui-cells_form" style="padding-top:15%;">
							<div class="weui-cells__title">添加收货地址</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">姓名</label>
								</div>
								<div class="weui-cell__bd">
									<input id="name" class="weui-input" type="text" placeholder="">
								</div>
							</div>
							<div class="weui-cell" style="display:none;">
							    <div class="weui-cell__hd"><label class="weui-label">身份证</label></div>
							    <div class="weui-cell__bd">
							      <input id="idNum" class="weui-input" type="text" placeholder="">
							    </div>
							    
							 </div>
							<div class="weui-cell" >
								<div class="weui-cell__hd">
									<label class="weui-label">手机号</label>
								</div>
								<div class="weui-cell__bd">
									<input id="addressphone" class="weui-input" type="text"
										placeholder="">
								</div>
							</div>
							<div class="weui-cell" id="weui">
								<div class="weui-cell__hd">
									<label for="name" class="weui-label">地址选择</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" id="address" type="text" value="" 
										readonly="" data-code="420106"
										data-codes="420000,420100,420106">
								</div> 
							</div>
							<div class="weui-cell" id="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">详细地址</label>
								</div>
								<div class="weui-cell__bd">
									<input id="moreAddress" class="weui-input" type="text" 
										placeholder="">
								</div>
							</div>
						</div>
						<a id="save"
							style="width:96%;position:fixed;bottom:0;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 保存</a>

					</div>
                    <div class="modDiv" id="cameraDiv" style="display:none;">
                          <div id="cameraContent"></div>
							  
						   <div>
                         
                    </div> 
				</div>
			</div>
	
	
		
</body>
</html>



