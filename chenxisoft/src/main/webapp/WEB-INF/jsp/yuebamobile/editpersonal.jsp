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
<title>月嫂详情</title>
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
   }
</style>
</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">月嫂详情
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b" style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
       
    </header>
	
	<div class="ui-form ui-border-t" style="margin-top:45px;">
	        <div class="ftitle">
	                                    基本信息
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                     姓名
	            </label>
                <label style="margin-left:20px;">黄靖宇</label>
	           
	        </div>
	       
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    身份证号
	            </label>
	            <label style="margin-left:20px;">13028119840715201x</label>
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                   电话
	            </label>
	            <label style="margin-left:20px;">13810728953</label>
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    家庭住址
	            </label>
	            <label style="margin-left:20px;">13028119840715201x</label>
	        </div>
	        <div class="ui-form-item ui-border-b">
	            <label style="width:100px;">
	                                    价格
	            </label>
	            <label style="margin-left:20px;">￥6000/月</label>
	        </div>
	        <div class="ftitle">
	                                    评论列表<a href="#" style="">查看更多</a>
	        </div>
	        
	        
	        <ul class="ui-list ui-border-tb" style="font-size:12px;">
                <li>
                    <div class="ui-avatar-lg">
                        <span style="background-image:url(http://placeholder.qiniudn.com/140x140)"></span>
                    </div>
                    <div class="ui-list-info ui-border-t">
                                                                          这个必须要赞，服务态度特别好，做饭也好吃，很有耐心。
                    </div>
                </li>
                <li>
                    <div class="ui-avatar-lg">
                        <span style="background-image:url(http://placeholder.qiniudn.com/140x140)"></span>
                    </div>
                    <div class="ui-list-info ui-border-t">
                                                                          这个必须要赞，服务态度特别好，做饭也好吃，很有耐心。
                    </div>
                </li>
               
            </ul>
	        
	        
	        
	        
	        <div class="ui-btn-wrap">
	            <button class="ui-btn-lg ui-btn-primary">
	                                     立即预订
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
	<jsp:include page="../common.jsp"></jsp:include>
	</script>
</body>
</html>
