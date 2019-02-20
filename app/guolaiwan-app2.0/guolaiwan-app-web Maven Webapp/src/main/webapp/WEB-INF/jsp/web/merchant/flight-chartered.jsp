<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> 
	<meta name="renderer" content="webkit">
	<title>${merchant.shopName}</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/web.css">
    <style type="text/css">
    	.flight-table{width:100%; margin-bottom:10px; font-size:13px; border-collapse:collapse;}
    	.flight-table tr{height:40px;}
    	.flight-table td, 
    	.flight-table, th{text-align:center; border:1px solid #e0e0e0; padding:0 10px;}
    	.flight-table td.left{text-align:left;}
    	.flight-table td.right{text-align:right;}
    	.flight-table td.last{padding:0;}
    	.flight-table td.last>a{background-color:#ea4949; display:inline-block; width:100%; height:39px; line-height:39px; border:0; color:#fff;}
    	
    	.tab{background-color:#f7f7f7;}
    	.tab>a{display:inline-block; height:40px; line-height:40px; width:150px; text-align:center;}
    	.tab>a.active{background-color:#fff;}
    </style>
</head>

<body>
	<!--- header begin-->
	<c:import url="${path}/web/top"></c:import>
	<!-- header End -->

	<div class="containers">
		<div class="pc-nav-item">
			<a href="<%= path%>/web/merchant/merchantList?m=${merchant.modularCode}">${merchant.modularName}</a> &gt;
			<a href="<%= path%>/web/merchant/merchantList?m=${merchant.modularCode}&mc=${merchant.modularClassId}">${merchant.modularClass}</a> &gt; 
			<a href="<%= path%>/web/merchant/query/route/${merchant.uuid}?pagecurr=1">${merchant.shopName}</a> &gt; 
			<a href="#">预定</a>
		</div>
	</div>

	<div class="containers clearfix">
	    <div class="pc-info fr">
	    	<div class="pc-term" style="height:40px; font-size:15px; font-weight:700; text-align:center; line-height:40px;">
                <span>包车：</span></span><span>${route.origin}</span> —— <span>${route.end}</span>
            </div>
	        <div class="pc-term tab" style="margin-bottom:0; height:40px; border-bottom:0;">
                <a href="<%= path%>/web/merchant/query/flight/${merchant.uuid}/${route.uuid}/CARTIME?pagecurr=1">班次</a>
                <a href="<%= path%>/web/merchant/query/flight/${merchant.uuid}/${route.uuid}/CONTRACT?pagecurr=1" class="active">包车</a>
            </div>
            <div class="time-border-list pc-search-list clearfix" style="padding-top:0;">
            	<table class="flight-table">
           			<thead>
           				<tr>
           					<th style="width:150px;">发车时间</th>
           					<th style="width:150px;">行程时间</th>
           					<th>司机</th>
           					<th style="width:100px;">价格</th>
           					<th style="width:50px;">操作</th>
           				</tr>
           			</thead>
           			<tbody>
           				<c:if test="${flights!=null && flights.size()>0}">
            				<c:forEach items="${flights}" var="flight">
            					<tr>
		           					<td>${flight.goTime}</td>
		           					<td>${flight.allTime}</td>
		           					<td class="left">${flight.driver}</td>
		           					<td class="right">${flight.price}元</td>
		           					<td class="last"><a href="<%= path%>/web/merchant/flight/order/login/check/${flight.uuid}">预定</a></td>
		           				</tr>
            				</c:forEach>
            			</c:if>
           			</tbody>
           		</table>
            	
            </div>
            
            <!-- 分页 -->
            <div class="clearfix">${pages}</div>
            
	        <div class="pc-search-re clearfix"></div>
	        
	    </div>
	    
	    <!-- 商品分类 -->
	    <div class="feilei">
		   	<div class="feilei-title">商品分类</div>
		    <div id="" class="fenlei-context">
		        <ul class="fenlei-wrap">
			      	<li class="selectAll"><a href="<%=path%>/web/merchant/productList?merchant=${merchant.uuid}"><cite class="selectall selected">全部</cite></a></li>
			      	${sbHtml}
		        </ul>
		    </div>
	    </div>
	</div>

	<!-- 页脚 -->
	<c:import url="${path}/web/foot"></c:import>
	
</body>

<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>
<script type="text/javascript">
	

</script>
</html>
	   