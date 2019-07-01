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
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">详情
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b" style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
      
    </header>
	
	<image src="/chenxisoft${worker.photo}" style="width:100%;height:200px;margin-top:45px;">
	<div class="ui-form ui-border-t">
	        <div class="ftitle">
	                                    基本信息
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                     姓名
	            </label>
                <label style="margin-left:20px;">${worker.realName}<span style="color:red;margin-left:5px;">(带过${worker.orderCount}个孩子)</span></label>
	           
	        </div>
	       
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    身份证号
	            </label>
	            <label style="margin-left:20px;">${worker.idCard}</label>
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                   电话
	            </label>
	            <label style="margin-left:20px;">${worker.phone}</label>
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    家庭住址
	            </label>
	            <label style="margin-left:20px;">${worker.address}</label>
	        </div>
	        <div class="ftitle">
	                                    订单信息
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    开始日期
	            </label>
	            <label style="margin-left:20px;">${order.fromDate}</label>
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    时间长度
	            </label>
	            <label style="margin-left:20px;">${order.days}</label>
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    服务地区
	            </label>
	            <label style="margin-left:20px;">${order.region}</label>
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    价格
	            </label>
	            <label style="margin-left:20px;">${order.price}</label>
	        </div>
	   </div>
	   <div class="ui-btn-wrap">
	            <button class="ui-btn-lg ui-btn-primary" id="gotolist">
	                                     返回订单列表
	            </button>
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
	         
		     $(document).on('click','#gotolist',function(){
		     
		        location.href=window.BASEPATH+"/order/mobile/list";
		     });
	          
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
