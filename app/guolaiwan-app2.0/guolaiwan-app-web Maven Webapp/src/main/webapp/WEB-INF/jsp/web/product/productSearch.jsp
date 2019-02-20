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
	<title>二级分类</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">


 </head>
 <body>

<!--- header begin-->

<c:import url="${path}/web/top"></c:import>
<!-- header End -->

<div class="containers"><div class="pc-nav-item"><a href="#">货架</a> &gt; <a href="#">二级货架</a></div></div>

<div class="containers clearfix">
    
    <div class="pc-info fr">
        <div class="pc-term">
            
            <div class="pc-line"></div>
            <div class="pc-search clearfix">
                
           
            </div>
        </div>
        <div class="pc-term">
            <div class="clearfix pc-search-p">
                <div class="fl pc-search-e"><a href="#" class="cur">销量</a><a href="#">价格</a><a href="#">评价</a><a href="#">上架时间</a></div>
                <div class="fr pc-search-v">
                    <ul>
                        <li><input type="checkbox"><a href="#">有货</a> </li>
                        <li><input type="checkbox"><a href="#">限时抢购</a> </li>
                        <li><input type="checkbox"><a href="#">满减大促</a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="time-border-list pc-search-list clearfix">
            <ul class="clearfix" id="productList">
            	<c:forEach items="${productList}" var="pl">
            		<li>
                    	<a href="productInfo?info=${pl.uuid}"> <img width="209px" height="140px"  src="${sysConfig.webUrl}${pl.productShowPic}"></a>
                    	<p class="head-name"><a href="productInfo?info=${pl.uuid}">${pl.productName}</a> </p>
                    	<p><span class="price">￥${pl.productPrice}</span></p>
                    	<p class="head-futi clearfix"><span class="fl">好评度：90% </span> <span class="fr">${pl.productSaleNum}人购买</span></p>
                    	<p class="clearfix"><span class="label-default fl">抢购</span> <a href="#" class="fr pc-search-c">收藏</a> </p>
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
</div>

<c:import url="${path}/web/foot"></c:import>
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>

<script type="text/javascript">
//成员变量
	var	mc ;
	var cc ;
	
$(function(){
	mc = "${mc}";
	cc = "${cc}";
	$(".mk${mc}").css("background","#5FAAFE");
	$(".cl${cc}").css("background","#5FAAFE");
	console.log("${param}");
	
})
function search(){
	var kw=$("#pc-name").val();
	var pcPriceMin=$("#pc-price-min").val();
	var pcPriceMax=$("#pc-price-max").val();
	var str = "productList?m="+mc
	if(cc!=""){
		str = str+"&mc="+cc;
	}
	if(kw!=""){
		str = str+"&pcName="+kw;
	}
	if(pcPriceMin!=""){
		str = str+"&pcPriceMin="+pcPriceMin;
	}
	if(pcPriceMax!=""){
		str = str+"&pcPriceMax="+pcPriceMax;
	}
	window.location.href=str;
}
function searchPage(){
	var kw=$("#pc-name").val();
	var pcPriceMin=$("#pc-price-min").val();
	var pcPriceMax=$("#pc-price-max").val();
    var	pagecurr=$("#pagecurr").val();
    
    
    
	var str = "productList?m="+mc
	if(cc!=""){
		str = str+"&mc="+cc;
	}
	if(kw!=""){
		str = str+"&pcName="+kw;
	}
	if(pcPriceMin!=""){
		str = str+"&pcPriceMin="+pcPriceMin;
	}
	if(pcPriceMax!=""){
		str = str+"&pcPriceMax="+pcPriceMax;
	}
	if(pagecurr==""){
		alert('请填写页码');
	}else{
	str+="&pagecurr="+pagecurr;
	window.location.href=str;
	}
}

</script>
</body>
</html>
