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
	<title>${activity.name}</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/web.css">
 </head>

 <body>

<!--- header begin-->

<c:import url="${path}/web/top"></c:import>
<!-- header End -->

<div class="containers"><div class="pc-nav-item"></div></div>
<div class="containers clearfix">
    <div class="pc-info fr">
        <div class="pc-term">	
            <div class="pc-search clearfix">
                <div class="fl pc-search-in">
                    <input type="text" class="pc-search-w" id="pc-name" value="${pcName}">
                    <a href="javascript:search()" class="pc-search-a" >搜索</a>
                </div>
            </div>
        </div>
        <div class="time-border-list pc-search-list clearfix">
            <ul class="clearfix" id="productList">
            	<c:forEach items="${productList}" var="pl">
            		<li>
                    	<a href="<%=basePath%>product/productInfo?info=${pl.uuid}"><img width="209px" height="140px" src="${sysConfig.webUrl}${pl.productShowPic}"></a>
                    	<p class="head-name"><a href="<%=basePath%>product/productInfo?info=${pl.uuid}">${pl.productName}</a> </p>
                    	<p><span class="price">￥${pl.productPricesStr}</span></p>
                    	<p class="head-futi clearfix"><span class="fl">好评度：99% </span> <span class="fr">${pl.productSaleNum}人购买</span></p>
                    	<p class="clearfix"><span class="label-default fl">抢购</span> <a href="#"></a></p>
                	</li>
            	</c:forEach>
            </ul>
            

            <div class="clearfix">
            ${pages}
            </div>
            
        </div>
        
        <div class="pc-search-re clearfix">
        
        </div>
        
    </div>
    

<c:import url="${path}/web/foot"></c:import>
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>
 

<script type="text/javascript">
//成员变量
	
	var basePath="<%=basePath%>";
	var uuid = '${activity.uuid}';
	var merM = '${merM}';
	
$(function(){
	$(".mk${mc}").css("background","#5FAAFE");
	$(".cl${cc}").css("background","#5FAAFE");
	if(merM!=""){
		$(".selected").addClass("select").removeClass("selected");
		$(".select${merM}").addClass("selected").removeClass("select");
	}
	
})
function search(){
	var kw=$("#pc-name").val();
	var pcPriceMin=$("#pc-price-min").val();
	var pcPriceMax=$("#pc-price-max").val();
	var str = basePath+"web/activity/productList?activity="+uuid;
	if(kw!=""){
		str = str+"&pcName="+kw;
	}
	window.location.href=str;
}


</script>

</body>
</html>
