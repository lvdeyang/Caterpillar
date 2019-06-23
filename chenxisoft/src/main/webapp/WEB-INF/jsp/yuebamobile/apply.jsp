<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
<title>注册月嫂</title>
<!-- 引入 FrozenUI -->
<link rel="stylesheet"
	href="http://i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css" />
<link rel="stylesheet"
	href="lib/css/font-awesome.css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="http://ryanbay.cn/vipstyle/qui/2.0.0/demo/js/lib/zepto.min.js"></script>
<script src="http://i.gtimg.cn/vipstyle/frozenjs/1.0.1/frozen.js"></script>
<style>
   label{
     font-size:14px;
     width:100px;
   }
   input{
     font-size:14px;
     height:46px;
   }
   .ftitle{
     width:96%;
     height:40px;
     padding-left:10px;
     line-height:40px;
     color:#7CAE23;
     
   }
   .ftitle a{
     display:inline-block;
     float:right;
     margin-right:20px;
     font-size:14px;
   }
</style>
</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">申请月嫂
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b">
         
         <h1 style="font-size:14px;width:80px;float:left;">申请月嫂</h1>
         <a href="person/index"><i style="font-size:14px;width:20px;line-height:50px;float:right"class="icon-user"></i></a>
    </header>
	
	<div class="ui-form ui-border-t" style="margin-top:45px;">
	        <div class="ftitle">
	                                    基本信息
	        </div>
	        <form action="">
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        姓名*
		            </label>
		            <input type="text" placeholder="请输入姓名">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        身份证号*
		            </label>
		            <input type="text" placeholder="18位身份证号码">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        电话*
		            </label>
		            <input type="text" placeholder="请输入手机号">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        家庭住址*
		            </label>
		            <input type="text" placeholder="请输入详细地址">
		           
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        上传照片*
		            </label>
		           
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        上传身份证照片*
		            </label>
		           
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>上传健康证明*
		            </label>
		           
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        上传证书*
		            </label>
		           
		        </div>
		        
		    </form>
	        
	        <div class="ui-btn-wrap">
	            <button class="ui-btn-lg ui-btn-primary">
	                                     立即申请
	            </button>
	        </div>
	</div>
	
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

	<script type="text/javascript">
	     $(function() {
	          window.BASEPATH = '<%=basePath%>';
	          
			  var parseAjaxResult = function(data){
					if(data.status !== 200){
						$.toptip('data.message', 'error');
						return -1;
					}else{
						return data.data;		
					}
			  };
	         var share={};
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
					            //debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
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
		                    var localData = res.localData; 
		                }
		                
		            )};
		 
		        }
	          
	     });
	
	</script>
</body>
</html>
