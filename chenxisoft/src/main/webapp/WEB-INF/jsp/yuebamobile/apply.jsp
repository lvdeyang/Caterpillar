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
		        
		    </form>
	        
	        <div class="ui-btn-wrap">
	            <button class="ui-btn-lg ui-btn-primary">
	                                     立即申请
	            </button>
	        </div>
	</div>
	
	

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
	         
		     
	          
	     });
	
	</script>
</body>
</html>
