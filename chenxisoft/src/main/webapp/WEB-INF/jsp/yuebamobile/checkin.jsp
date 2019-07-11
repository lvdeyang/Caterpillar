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
     color:#FFC0CB;
     
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
	
	<div class="ui-form ui-border-t">
	        <div class="ftitle">
	                                    基本信息
	        </div>

	         <div class="ui-form-item ui-border-b">
		            <label>级别
		            </label>
		            <div class="ui-select" style="margin-top:10px;margin-left:50px;">
			            <select id="level">
			                 <c:forEach items="${levelList}" var="level">
				                <option value="${level.name}">${level.name}</option>
				             </c:forEach>
			            </select>
			        </div>
		     </div>
		     <div class="ui-form-item ui-border-b">
		            <label>订单数
		            </label>
		            <input style="margin-top:12px;margin-left:15px;" type="text" id="baseOrderCount" value="">
		     </div>
		     <div class="ui-form-item ui-border-b">
		            <label>排序
		            </label>
		            <input  style="margin-top:12px;margin-left:25px;" type="text" id="sindex" value="">
		     </div>
		     <div class="ui-form-item ui-border-b">
		            <label>反馈信息
		            </label>
		            <input style="margin-top:12px;margin-left:0px;" type="text" id="checkMsg" value="">
		     </div>
            
	        <div class="ui-btn-wrap" style="margin-left:-5px">
	            <button class="ui-btn-lg ui-btn-primary" id="agree" style="background:#FFC0CB;width:50%">
	                                                    同意                 
	            </button>
	            <button class="ui-btn-lg ui-btn-primary" id="refuse" style="background:#FFC0CB;width:50%;margin-left:5px;">
	                                                    拒绝                            
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
	         
	
		     
		     $(document).on('click','#agree',function(){
		         var _uricheckdo = window.BASEPATH + 'worker/mobile/docheck';
		         var data={};
		         data.id=${workerId};
		         data.level=$('#level').val();
		         data.baseOrderCount=$('#baseOrderCount').val();
		         data.sindex=$('#sindex').val();
		         data.checkMsg=$('#checkMsg').val();
		         $.post(_uricheckdo, data, function(data){
				       location.href=window.BASEPATH +'worker/mobile/check';
				  });
		     
		     });
		     $(document).on('click','#refuse',function(){
		         var _uricheckdo = window.BASEPATH + 'worker/mobile/notpass';
		         var data={};
		         data.id=${workerId};
		         data.level=$('#level').val();
		         data.baseOrderCount=$('#baseOrderCount').val();
		         data.sindex=$('#sindex').val();
		         data.checkMsg=$('#checkMsg').val();
		         $.post(_uricheckdo, data, function(data){
				       location.href=window.BASEPATH +'worker/mobile/check';
				  });
		     
		     });
		     
	       
	          
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
