<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>详情</title>
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
     color:#FFC0CB
   }
   #priceTable{
     width:90%;
     margin-left:15px;
   }
   #priceTable tr td{
     width:70px;
     height:30px;
     font-size:13px;
     border:1px solid #CCC;
   }
   #imageContent{
     width:95%;margin-left:3px;
   }
   #imageContent img{
     width:100px;
     height:100px;
     float:left;
   }
   #layui-laydate1{
     font-size:12px;
     width:100%;
   }
   .layui-laydate-footer{
     display:none;
   }
   .layui-laydate-main .layui-laydate-content{
     width:100%;
   }
   .layui-laydate-content table{
     width:100%
   }
   .layui-laydate-content table tbody tr td{
     font-size:14px;
   }
  
</style>
</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">详情
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b" style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
       
    </header>
	<image src="/${worker.photo}" style="width:100%;height:350px;margin-top:45px;">
	<div class="ui-form ui-border-t">
	        <div class="ftitle">
	                                    基本信息
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                     姓名
	            </label>
                <label style="margin-left:20px;">${worker.realName}<span style="color:red;margin-left:5px;">(带过${childCount}个孩子)</span></label>
	           
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    年龄
	            </label>
                <label style="margin-left:20px;">${worker.age}<span style="color:red;margin-left:5px;"></label>
	           
	        </div>
	       
	        <div class="ui-form-item ui-border-b" style="display:none">
	            <label style="width:100px;">
	                                    身份证号
	            </label>
	            <label style="margin-left:20px;">${worker.idCard}</label>
	        </div>
	        <div class="ui-form-item ui-border-b" >
	            <label style="width:100px;">
	                                   客服电话
	            </label>
	            <label style="margin-left:20px;">18617856189</label>
	        </div>
	        <div class="ui-form-item ui-border-b" style="display:none">
	            <label style="width:100px;">
	                                    家庭住址
	            </label>
	            <label style="margin-left:20px;">${worker.address}</label>
	        </div>
	        <div class="ftitle">
	                                   专长特点
	        </div>
	        <p style="font-size:14px;margin-left:12px;width:90%">
	             ${worker.moreMsg}
	        
	        </p>
	        
	        <div class="ftitle">
	                                    订单列表
	        </div>
	       
	        <ul class="ui-list ui-border-tb" style="font-size:12px;">
		        <c:forEach items="${orders}" var="order">
	                <li>
	                    
						<div class="ui-list-img-square">
							<span style="background-image:url(/${order.worderPhoto}"></span></div>
						<div class="ui-list-info ui-border-t">
							<div style="font-size:14px;">${order.workName}</div>
							<p class="ui-nowrap" style="font-size:12px;">${order.price}元/${order.days}天/${order.region}</p>
							<p class="ui-nowrap" style="font-size:12px;">开始日期:${order.fromDateStr}</p>
						</div>
	                </li>
	            </c:forEach>
            </ul>
	        
	        
	        <div class="ftitle adminrow" style="display:none;">
	                                    管理员菜单
	        </div>
	        <div class="adminrow" style="display:none;font-size:14px;margin-left:12px;">
	            <a href="/chenxisoft/order/mobile/addindex?workerId=${worker.id}" id="addOrder">添加订单</a>
	            <a href="/chenxisoft/order/mobile/wlist?workerId=${worker.id}" id="addOrder" style="margin-left:5px;">查看她的订单</a>
	        </div>
	        <div class="ftitle">
	                                    证件照片
	        </div>
	        <div id="imageContent">
	            <c:if test="${!empty worker.idCardPhoto}">
	                <image src="/${worker.idCardPhoto}" style="margin-left:12px;">
	            </c:if>
	            <c:if test="${!empty worker.healthPhoto}">
	                <image src="/${worker.healthPhoto}" style="margin-left:12px;">
	            </c:if>
	            <c:if test="${!empty worker.expertPhoto}">
	                <image src="/${worker.expertPhoto}" style="margin-left:12px;">
	            </c:if>
	        </div>
	        <div class="ftitle" style="float:left;display:none;">
	                                    价格表(单位:元)
	        </div>
	        <table id="priceTable" style="display:none;">
	           ${priceHtml}
	        </table>
	        <div class="ftitle" style="display:none;">
	                                    查看档期
	        </div>
	        <div style="width:90%;margin-left:15px;height:300px;display:none">
	            <div id="seeDate"></div>
	          
	        </div>
	        
	        
	        
	        <div class="ftitle" style="display:none;">
	                                    评论列表<a href="javascript:void(0)" id="moreComment" style="">查看更多</a>
	        </div>
	        <section style="display:none" class="ui-input-wrap ui-border-t">
			    <div class="ui-input ui-border-radius">
			        <input  style="font-size:12px;" type="text" id="commentContent" placeholder="我也说一句...">
			    </div>
			    <button id="addCommnet" class="ui-btn" style="font-size:12px;">评论</button>
			</section>
	        
	        <ul style="display:none;" class="ui-list ui-border-tb" style="font-size:12px;">
		        <c:forEach items="${comments}" var="comment">
	                <li>
	                    <div class="ui-avatar-lg">
	                        <span style="background-image:url(${comment.userPhoto})"></span>
	                    </div>
	                    <div class="ui-list-info ui-border-t">
	                         ${comment.content}
	                    </div>
	                </li>
	            </c:forEach>
            </ul>
            
	        <div class="ui-btn-wrap">
	            <button class="ui-btn-lg ui-btn-primary" id="addOrder" style="background:#FFC0CB">
	                                     电话咨询
	            </button>
	        </div>
	</div>
	
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script type="text/javascript" src="lib/laydate/laydate.js" charset="utf-8"></script>
    
    
	<script type="text/javascript">
	     $(function() {
	          window.BASEPATH = '<%=basePath%>';
	          
			  var parseAjaxResult = function(data){
					if(data.status !== 200){
						return -1;
					}else{
						return data.data;		
					}
			  };
	         
		     $(document).on('click','#addOrder',function(){
		         //location.href=window.BASEPATH+'/worker/mobile/addorder?workerId='+${worker.id};
		     });
		     $(document).on('click','#moreComment',function(){
		         location.href=window.BASEPATH+'/comment/mobile/index?workerId='+${worker.id};
		     });
		     
		     $(document).on('click','#addCommnet',function(){
		         var _uriAddComment = window.BASEPATH + 'comment/mobile/add';
		         var data={};
		         data.content=$('#commentContent').val();
		         data.workerId=${worker.id};
		         $.post(_uriAddComment, data, function(data){
				      
				       data = parseAjaxResult(data);
				       $('#commentContent').val('');
				       location.href=location.href;
				  });
		     
		     });
		     
	         /*var date2=laydate.render({
			   elem: '#seeDate'
			   ,position: 'static',
			   zIndex: 1,
			   //min:'${minDate}',
			   //max:'2019-08-25',
			   theme:'#FFC0CB',
			   ready:function(){
			      $('.layui-laydate-main').css('width','100%');
				  getCurrentRange('');
				  
			   },
			   change: function(value, date, endDate){
						    	 //getCurrentRange(value);
			   }
			 });*/
			 
			 
			 
			var adminFlg=${user.adminFlg};
			if(adminFlg==1){
			   $('.adminrow').show();
			}
            
			
			
			 function getCurrentRange(currentDate){
			         var _cruri = window.BASEPATH + 'order/mobile/checkCurentRage?currentDate='+currentDate+'&workerId=${worker.id}';
				     $.get(_cruri, null, function(data){
						data = parseAjaxResult(data);
						if(data === -1) return;
						if(data){
						    var mins=data.min.split('-');
						    date2.config.min=
						    {year:parseInt(mins[0]), 
                            month:parseInt(mins[1])-1,
                            date:parseInt(mins[2])};
                            
                            var maxs=data.max.split('-');
                            date2.config.max=
						    {year:parseInt(maxs[0]), 
                            month:parseInt(maxs[1])-1,
                            date:parseInt(maxs[2])}
  
						}
						
					 });
			      
			      
			      
			 
			 }
			 
			 
			 
			 
			 
			 var share={};
			 var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
		     var _uri = window.BASEPATH + 'login/wx/prev?url='+reqUrl;
		     $.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				    
					share=data;
					shareReady();
				}
				
			 });
		    

			 
			 
			 function shareReady(){
		            wx.config({
			            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			            appId : share.appId, // 必填，公众号的唯一标识
			            timestamp : share.timestamp, // 必填，生成签名的时间戳
			            nonceStr : share.nonceStr, // 必填，生成签名的随机串
			            signature : share.signature,// 必填，签名，见附录1
			            jsApiList : ['checkJsApi', 'onMenuShareTimeline' , 'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		        	});
			        wx.ready(function() {
	
			            wx.onMenuShareTimeline({
                            title: '[小青月嫂]${worker.realName}_${worker.level}月嫂', // 分享标题
                            link: location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: 'http://www.yueba.net.cn/${worker.photo}', // 分享图标
                            success: function () {
                               	
                            }
		                });
			            wx.onMenuShareAppMessage({
							title : '[小青月嫂]${worker.realName}_${worker.level}月嫂', // 分享标题
							desc : '联系电话:18617856189', // 分享描述
							link : location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
							imgUrl : 'http://www.yueba.net.cn/${worker.photo}', // 分享图标
							success : function() {}
						});
			            
			       });
		     }
			 
			 $(document).on('click','#addOrder',function(){
			      window.location.href = 'tel://18617856189';
			 
			 });
	          
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
