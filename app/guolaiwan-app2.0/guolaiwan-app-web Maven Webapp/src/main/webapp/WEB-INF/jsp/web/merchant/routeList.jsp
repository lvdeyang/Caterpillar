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
    	.route-table{width:100%; margin-bottom:10px; font-size:13px; border-collapse:collapse;}
    	.route-table tr{height:40px;}
    	.route-table td, 
    	.route-table, th{text-align:center; border:1px solid #e0e0e0; padding:0 10px;}
    	.route-table td.left{text-align:left;}
    	.route-table td.right{text-align:right;}
    	.route-table td.last{padding:0;}
    	.route-table td.last>a{background-color:#ea4949; display:inline-block; width:100%; height:39px; line-height:39px; border:0; color:#fff;}
    </style>
</head>

<body>
	<!--- header begin-->
	<c:import url="${path}/web/top"></c:import>
	<!-- header End -->

	<div class="containers"><div class="pc-nav-item"><a href="<%= path%>/web/merchant/merchantList?m=${merchant.modularCode}">${merchant.modularName}</a> &gt;<a href="<%= path%>/web/merchant/merchantList?m=${merchant.modularCode}&mc=${merchant.modularClassId}">${merchant.modularClass}</a> &gt; <a href="#">${merchant.shopName}</a></div></div>

	<div class="containers clearfix">
	    <div class="pc-info fr">
	        <%-- <div class="pc-term">	
	            <div class="pc-search clearfix">
	                <div class="fl pc-search-in">
	                    <input type="text" class="pc-search-w" id="pc-name" value="${pcName}">
	                    <input type="text" class="pc-search-s" id="pc-price-min" placeholder="￥" value="${pcPriceMin}">
	                    <input type="text" class="pc-search-s" id="pc-price-max"placeholder="￥" value="${pcPriceMax}">
	                    <a href="javascript:search()" class="pc-search-a" >搜索</a>
	                </div>
	            </div>
	        </div>
	        <div class="pc-term">
	            <div class="clearfix pc-search-p">
	                <div class="fl pc-search-e"><a href="#" class="cur">销量</a><a href="#">价格</a><a href="#">评价</a><a href="#">上架时间</a></div>
	                <div class="fr pc-search-v">
	                    <ul>
	                        <li><input type="checkbox"><a href="#">有货</a></li>
	                        <li><input type="checkbox"><a href="#">限时抢购</a> </li>
	                        <li><input type="checkbox"><a href="#">满减大促</a> </li>
	                    </ul>
	                </div>
	            </div>
	        </div> --%>
	        <div class="time-border-list pc-search-list clearfix" style="padding-top:0;">
	            
				<table class="route-table">
					<thead>
						<tr>
							<th style="width:150px;">始发地</th>
							<th style="width:150px;">目的地</th>
							<th style="width:150px;">发车时间</th>
							<th>价格</th>
							<th style="width:50px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${routes!=null || routes.size()>0}">
							<c:forEach items="${routes}" var="route">
								<tr>
									<td class="left">${route.origin}</td>
									<td class="left">${route.end}</td>
									<td>${route.startTime}</td>
									<td class="left">${route.price}</td>
									<td class="last"><a href="<%= path%>/web/merchant/query/flight/${merchant.uuid}/${route.uuid}/CARTIME?pagecurr=1">预定</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>	            
	            
				<!-- 分页 -->
	            <div class="clearfix">${pages}</div>
	            
	        </div>
	        
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
	
	//成员变量
	var basePath="<%=basePath%>";
	var uuid = '${merchant.uuid}';
	var merM = '${merM}';
	
	$(function(){
		$(".mk${mc}").css("background","#5FAAFE");
		$(".cl${cc}").css("background","#5FAAFE");
		if(merM!=""){
			$(".selected").addClass("select").removeClass("selected");
			$(".select${merM}").addClass("selected").removeClass("select");
		}
		
	});
	
	function search(){
		var kw=$("#pc-name").val();
		var pcPriceMin=$("#pc-price-min").val();
		var pcPriceMax=$("#pc-price-max").val();
		var str = basePath+"web/merchant/productList?merchant="+uuid;
		if(kw!=""){
			str = str+"&pcName="+kw;
		}
		if(pcPriceMin!=""){
			str = str+"&pcPriceMin="+pcPriceMin;
		}
		if(pcPriceMax!=""){
			str = str+"&pcPriceMax="+pcPriceMax;
		}
		if(merM!=""){
			str = str+"&merM="+merM;
		}
		window.location.href=str;
	}

</script>
</html>
	   