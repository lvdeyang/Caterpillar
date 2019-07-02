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
<title>文章页面</title>
<!-- 引入 FrozenUI -->
<link rel="stylesheet"
	href="http://i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css" />
<link rel="stylesheet"
	href="lib/css/font-awesome.css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="http://ryanbay.cn/vipstyle/qui/2.0.0/demo/js/lib/zepto.min.js"></script>
<script src="http://i.gtimg.cn/vipstyle/frozenjs/1.0.1/frozen.js"></script>
</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b" style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
        
    </header>
	
	<div style="margin-top:50px;padding-left:15px;font-size:18px;id="title">${article.title}</div>
    <div style="padding-left:15px;margin-top:5px;font-size:13px;" id="source"></div>
    <div style="padding:15px;font-size:12px;margin-top:5px;" id="content">
         <c:forEach var="content" items="${contentPos}">
		         <c:if test="${content.myimage!=null}">
		             <image style="width:100%;margin-top:5px;" src="${content.myimage}"/>
		         </c:if>
		         <c:if test="${content.mycontent!=null}">
		             <div style="width:100%;margin-top:5px;">
		                  ${content.mycontent}
		             </div>
		         </c:if>
		 </c:forEach>
    </div>
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
	          
	         var source='${article.source}'; 
	         $('#source').html(dic[source]);
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
