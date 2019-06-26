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
     color:#7CAE23;
     
   }
   .ftitle a{
     display:inline-block;
     float:right;
     margin-right:20px;
     font-size:14px;
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
     width:90%;margin-left:15px;border:1px solid #CCC;height:100px;
   }
   #imageContent img{
     width:100px;
     height:100px;
     float:left;
   }
</style>
</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">详情
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b">
         
         <h1 style="font-size:14px;width:80px;float:left;">详情</h1>
         <a href="person/index"><i style="font-size:14px;width:20px;line-height:50px;float:right"class="icon-user"></i></a>
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
	       
	        <div class="ui-form-item ui-border-b" style="display:none">
	            <label style="width:100px;">
	                                    身份证号
	            </label>
	            <label style="margin-left:20px;">${worker.idCard}</label>
	        </div>
	        <div class="ui-form-item ui-border-b" >
	            <label style="width:100px;">
	                                   电话
	            </label>
	            <label style="margin-left:20px;">0315-6659872</label>
	        </div>
	        <div class="ui-form-item ui-border-b" style="display:none">
	            <label style="width:100px;">
	                                    家庭住址
	            </label>
	            <label style="margin-left:20px;">${worker.address}</label>
	        </div>
	        <div class="ftitle">
	                                    证件照片
	        </div>
	        <div id="imageContent">
	            
	            <image src="/chenxisoft${worker.idCardPhoto}">
	            <image src="/chenxisoft${worker.healthPhoto}" style="margin-left:12px;" src="/chenxisoft/lib/images/1.jpg">
	            <image src="/chenxisoft${worker.expertPhoto}" style="margin-left:12px;" src="/chenxisoft/lib/images/1.jpg">
	        </div>
	        <div class="ftitle">
	                                    价格表(单位:元)
	        </div>
	        <table id="priceTable">
	           ${priceHtml}
	        </table>
	        
	        <div class="ftitle">
	                                    评论列表<a href="#" style="">查看更多</a>
	        </div>
	        
	        
	        <ul class="ui-list ui-border-tb" style="font-size:12px;">
		        <c:forEach items="${comments}" var="comment">
	                <li>
	                    <div class="ui-avatar-lg">
	                        <span style="background-image:url(/chenxisoft/${comment.userPhoto})"></span>
	                    </div>
	                    <div class="ui-list-info ui-border-t">
	                         ${comment.content}
	                    </div>
	                </li>
	            </c:forEach>
            </ul>

	        <div class="ui-btn-wrap">
	            <button class="ui-btn-lg ui-btn-primary" id="addOrder">
	                                     电话咨询
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
	         
		     $(document).on('click','#addOrder',function(){
		         location.href=window.BASEPATH+'/worker/mobile/addorder?workerId='+${worker.id};
		     });
	          
	     });
	
	</script>
</body>
</html>
