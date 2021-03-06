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
<title>注册</title>
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
     width:105px;
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
     color:#BA6985;
     
   }
   .ftitle a{
     display:inline-block;
     float:right;
     margin-right:20px;
     font-size:14px;
     color:#FFC0CB;
   }
   .addPhoto{
     margin-top:5px;
     margin-left:20px;
     width:50px;
     height:50px;
     line-height:50px;
     font-size:24px;
     color:#CCC;
     border:1px solid #CCC;
     text-align:center
   }
   .showPhoto{
     margin-top:5px;
     margin-left:10px;
     width:50px;
     height:50px;
     display:none;
   }
</style>
</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">申请
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b"  style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
        
    </header>
	
	<div class="ui-form ui-border-t" style="margin-top:45px;">
	        <div class="ftitle">
	                                    基本信息
	        </div>
	        <div id="status" style="font-size:12px;color:red;width:100%;text-align:center;display:none;">审核中</div>

		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        姓名*
		            </label>
		            <input id="name" type="text" placeholder="请输入姓名">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        年龄*
		            </label>
		            <input id="age" type="text" placeholder="请输入年龄">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        身份证号*
		            </label>
		            <input id="idStr" type="text" placeholder="18位身份证号码">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        电话*
		            </label>
		            <input id="phone" type="text" placeholder="请输入手机号">
		            
		        </div>
		        <div class="ui-form-item ui-border-b">
		            <label>
		                                        家庭住址*
		            </label>
		            <input id="address" type="text" placeholder="请输入详细地址">
		           
		        </div>
		        <div class="ui-form-item ui-border-b" style="height:60px;">
		            <label>
		                                        上传照片*
		            </label>
		            <div id="photo-btn" class="addPhoto">
		            +
		            </div>
		            <image src="" id="photo-pic" class="showPhoto"/>
		            <input type="hidden" id="photo-input" name="photo"/>
		        </div>
		        <div class="ui-form-item ui-border-b" style="height:60px;">
		            <label>
		                                        上传身份证照片*
		            </label>
		            <div id="idcard-btn" class="addPhoto">
		            +
		            </div>
		            <image src="" id="idcard-pic" class="showPhoto"/>
		            <input type="hidden" id="idcard-input" name="idCardPhoto"/>
		        </div>
		        <div class="ui-form-item ui-border-b" style="height:60px;">
		            <label>上传健康证明*
		            </label>
		            <div id="health-btn" class="addPhoto">
		            +
		            </div>
		            <image src="" id="health-pic" class="showPhoto"/>
		            <input type="hidden" id="health-input" name="healthPhoto"/>
		        </div>
		        <div class="ui-form-item ui-border-b" style="height:60px;">
		            <label>
		                                        上传证书*
		            </label>
		            <div id="certificate-btn" class="addPhoto">
		            +
		            </div>
		            <image src="" id="certificate-pic" class="showPhoto"/>
		            <input type="hidden" id="certificate-input" name="expertPhoto"/>
		        </div>
		        
		 
	        
	        <div class="ui-btn-wrap">
	            <button id="doApply" class="ui-btn-lg ui-btn-primary" style="background:#FFC0CB">
	                                     立即申请
	            </button>
	            
	        </div>
	        
	        <div id="loading" class="ui-loading-block show" style="display:none">
			    <div class="ui-loading-cnt">
			        <i class="ui-loading-bright"></i>
			        <p>正在上传，请不要关闭页面......</p>
			    </div>
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
	         var photoId='';
	         $(document).on('click','.addPhoto',function(){
	            var ids=this.id.split('-');
	            photoId=ids[0];
	            getPhoto();
	         });
	         
		     function  getPhoto() {
	    
			    var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	            var _uri = window.BASEPATH + 'worker/wx/prev?url='+reqUrl;
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
	                wx.closeWindow();
	            });
			}
		    function choosePicone(id) {
	            wx.chooseImage({
	                count: 1, // 默认9
	                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	                success: function (res) {
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
	                    var sear=new RegExp(',');
                        if(sear.test(localData )) {
                          arr=str.split(',');//注split可以用字符或字符串分割
                          localData=arr[1];
                        }
                                
		    $('#'+photoId+'-pic').attr('src','data:image/png;base64,'+localData);
                                    $('#'+photoId+'-pic').show();
	                    $('#'+photoId+'-input').val(localData);

	                }
	                
	            });
	 
	        }
	        $(document).on('click','#doApply',function(){
	            $('#loading').show();
	            var params={};
	            params.realName=$('#name').val();
	            params.idCard=$('#idStr').val();
	            params.phone=$('#phone').val();
	            params.address=$('#address').val();
	            params.photo=$('#photo-input').val();
	            params.idCardPhoto=$('#idcard-input').val();
	            params.healthPhoto=$('#health-input').val();
	            params.expertPhoto=$('#certificate-input').val();
	            params.age=$('#age').val();
                var _uri = window.BASEPATH + 'worker/mobile/apply.do';
		        $.post(_uri, params, function(data){
			        location.href=window.BASEPATH + 'worker/mobile/apply?t=' + new Date().getTime();
			        $('#loading').hide();
				}); 
				     
	        });
	          
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
