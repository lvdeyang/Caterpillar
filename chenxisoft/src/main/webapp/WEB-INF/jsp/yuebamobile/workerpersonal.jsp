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
     color:#BA6985;
     
   }
   .ftitle a{
     display:inline-block;
     float:right;
     margin-right:20px;
     font-size:14px;
     color:#FFC0CB;
   }
</style>


</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">个人中心
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b" style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
        
    </header>
    <c:if test="${worker.status eq 'PASSED'}">
		<ul class="ui-list ui-list-function ui-border-tb" style="margin-top:50px;">
		    <li>
		        <div class="ui-avatar">
		            <span style="background-image:url(/${worker.photo})"></span>
		        </div>
		        <div class="ui-list-info ui-border-t">
		            <h4 class="ui-nowrap">${worker.realName}</h4>
		            <p>历史佣金:0</p>
		        </div>
		    </li>
	    </ul>
    </c:if>
    <c:if test="${worker.status eq 'CHECKING'}">
	   <div style="text-align:center;font-size:12px;color:red;margin-top:45px;">审核中......</div>
	</c:if>
	<c:if test="${worker.status eq 'REFUSE'}">
	   <div style="text-align:center;font-size:12px;color:red;margin-top:45px;">审核不通过:${worker.checkMsg}</div>
	   <div class="ui-btn-wrap">
           <button id="reapply" class="ui-btn-lg ui-btn-primary"  style="background:#FFC0CB">
                                    重新申请
           </button>   
	   </div>
	</c:if>
	<c:if test="${worker.status eq 'PASSED'}">
	    <div class="ftitle">
	             基本设置
		</div>
	    <ul class="ui-list ui-list-single ui-list-link ui-border-tb">
		    <li class="ui-border-t" data="seerecord/mobile/seeusers">
		        <div class="ui-list-info">
		            <h4 class="ui-nowrap" style="font-size:14px;">谁看过我</h4> 
		        </div>
		    </li>
		    <li class="ui-border-t" data="assign/mobile/wlist?orderId=0">
		        <div class="ui-list-info">
		            <h4 class="ui-nowrap" style="font-size:14px;">月嫂签到</h4> 
		        </div>
		    </li>
		    <li class="ui-border-t" data="order/mobile/wlist?workerId=0">
		        <div class="ui-list-info"  >
		            <h4 class="ui-nowrap" style="font-size:14px;">历史订单</h4> 
		        </div>
		    </li>
		    <li class="ui-border-t" data="comment/mobile/index" style="display:none;">
		        <div class="ui-list-info">
		            <h4 class="ui-nowrap" style="font-size:14px;">用户评论</h4> 
		        </div>
		    </li>
		</ul>
	</c:if>
    
	
	
	

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
	          
	          
	         $(document).on('click','#reapply',function(){
	              location.href=window.BASEPATH+'worker/mobile/reapply';
	         }); 
	         $(document).on('click','.ui-border-t',function(){
	              location.href=$(this).attr('data');
	         }); 
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
