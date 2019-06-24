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
<title>个人中心</title>
<!-- 引入 FrozenUI -->
<link rel="stylesheet"
	href="http://i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css" />
<link rel="stylesheet"
	href="lib/css/font-awesome.css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="http://ryanbay.cn/vipstyle/qui/2.0.0/demo/js/lib/zepto.min.js"></script>
<script src="http://i.gtimg.cn/vipstyle/frozenjs/1.0.1/frozen.js"></script>
<style>
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
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">个人中心
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b">
         
         <h1 style="font-size:14px;width:80px;float:left;">个人中心</h1>
         <a href="cartest/index"><i style="font-size:14px;width:20px;line-height:50px;float:right"class="icon-home"></i></a>
    </header>
	<ul class="ui-list ui-list-function ui-border-tb" style="margin-top:50px;">
	    <li>
	        <div class="ui-avatar">
	            <span style="background-image:url(http://placeholder.qiniudn.com/100x100)"></span>
	        </div>
	        <div class="ui-list-info ui-border-t">
	            <h4 class="ui-nowrap">黄靖宇</h4>
	            <p>未提取佣金:￥18000</p>
	        </div>
	    </li>
    </ul>
    
	<div class="ftitle">
	             基本设置
	</div>
    <ul class="ui-list ui-list-single ui-list-link ui-border-tb">
	    <li class="ui-border-t">
	        <div class="ui-list-info">
	            <h4 class="ui-nowrap" style="font-size:14px;">佣金提现</h4> 
	        </div>
	    </li>
	    <li class="ui-border-t">
	        <div class="ui-list-info">
	            <h4 class="ui-nowrap" style="font-size:14px;">账户中心</h4> 
	        </div>
	    </li>
	    <li class="ui-border-t">
	        <div class="ui-list-info">
	            <h4 class="ui-nowrap" style="font-size:14px;">用户评论</h4> 
	        </div>
	    </li>
	</ul>
	
	<div class="ftitle">
	            月嫂订单
	</div>
    <ul class="ui-list ui-list-single ui-list-link ui-border-tb">
	    <li class="ui-border-t">
	        <div class="ui-list-info">
	            <h4 class="ui-nowrap" style="font-size:14px;">已预定</h4> 
	        </div>
	    </li>
	    <li class="ui-border-t">
	        <div class="ui-list-info">
	            <h4 class="ui-nowrap" style="font-size:14px;">进行中</h4> 
	        </div>
	    </li>
	    <li class="ui-border-t">
	        <div class="ui-list-info">
	            <h4 class="ui-nowrap" style="font-size:14px;">已完成</h4> 
	        </div>
	    </li>
	   
	</ul>

	<script type="text/javascript">
	     $(function() {
	          window.BASEPATH = '<%=basePath%>';
	          var dic={'XINLANG':'新浪','YICHE':'易车'};
			  var parseAjaxResult = function(data){
					if(data.status !== 200){
						$.toptip('data.message', 'error');
						return -1;
					}else{
						return data.data;		
					}
			  };
	          
	          
	     });
	
	</script>
</body>
</html>
