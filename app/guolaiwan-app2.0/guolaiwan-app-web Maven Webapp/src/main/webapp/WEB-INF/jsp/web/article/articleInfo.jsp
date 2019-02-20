<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<style type="text/css">
.aritcleBody{
width:100%;
min-height: 500px;
}
.aritcleTitle
{
width:100%;
text-align: center;

}
.aritcleTitle h1{
height:50px;
line-height: 50px;
}
.aritcleTitle h2{
height:50px;
line-height: 50px;
}
.subtitle{
font-size: 12px;
}
.aritcleCon
{
width:60%;
margin: 0 auto;
font-size: 14px;
margin-top: 50px;
}

</style>
 </head>
 <body>

<!--- header begin-->

<c:import url="${path}/web/top"></c:import>
<!-- header End -->

<div class="aritcleBody">

<div class="aritcleTitle">
 <h1>${article.className}</h1>
 <h2>${article.articleName}</h2>
 <div class="subtitle"> 创建时间：${article.articleDate}&nbsp;&nbsp;&nbsp;&nbsp;作者：过来玩网站 </div>
</div>

<div class="aritcleCon">
${article.articleCon}
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
