<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String weburl=WXContants.Website;
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
    <title>过来玩网站</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
    
    <style type="text/css">
  	
  		.company-video-change:hover{opacity:1 !important;}
  		.company-video-change{opacity:.1;}
  		.handler{height:42px;margin:0 auto;font-size:10px;text-align:center;border-top:1px solid #DCDCDC;
  				line-height:45px;bottom:-50px;}
  	</style>
  	 <style type="text/css">
        div,body {
        margin:0;
        padding:0;
        border:0;
        }
        .footer_div{
    width:100%;
    height:200px;
    position:fixed; 
  
    }
        .footer_con {
        
            width:100%;
            height:100%;
            margin:0 auto;
        
        }
            .footer_con div {
            float:left;
            }
            .footer_con .footer_con1 {
            width:90%;
            }
           
            .footer_con .footer_con3 {
            width:10%;
            }


        .footer_block {
        
            display:none;
            width:10%;
            height:100%;        
            float:right;
        }
        body {
        height:2000px;
        }
         .pic img{
        	width:100%;
			height:480px;
        	
        }
    </style>
  	
    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/index.js"></script>
    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/js-tab.js"></script>
    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/MSClass.js"></script>
    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/jcarousellite.js"></script>
    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/top.js"></script>
    <script type="text/javascript">
        var intDiff = parseInt(80000);//倒计时总秒数量
        var webUrl = '${sysconfig.webUrl}';
        function timer(intDiff) {
            window.setInterval(function () {
                var day = 0,
                        hour = 0,
                        minute = 0,
                        second = 0;//时间默认值
                if (intDiff > 0) {
                    day = Math.floor(intDiff / (60 * 60 * 24));
                    hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
                    minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
                    second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
                }
                if (minute <= 9) minute = '0' + minute;
                if (second <= 9) second = '0' + second;

                $('#hour_show').html('<s id="h"></s>' + hour + '');
                $('#minute_show').html('<s></s>' + minute + '');
                $('#second_show').html('<s></s>' + second + '');
                intDiff--;
            }, 1000);
        }

        $(function () {
            timer(intDiff);
            //判断浏览器
            var userAgent = navigator.userAgent;
			var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    		var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
    		var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
    		var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器//谷歌
			var liu;
			if (isIE) {
        		var IE5 = IE55 = IE6 = IE7 = IE8 = false;
        		var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        		reIE.test(userAgent);
        		var fIEVersion = parseFloat(RegExp["$1"]);
        		liu = "IE"+fIEVersion;
    		}
    		if(isOpera){
    			liu = "欧朋";
    		}
    		if(isFF){
    			liu = "火狐";
    		}
    		if(isSafari){
    			liu = "google";
    		}
    		if(!(isFF||isSafari||fIEVersion>8)){
				alert("您的浏览器版本过低，请更换chrome浏览器、360浏览器、火狐浏览器或ie9以上的浏览器。\n您的浏览器内核是"+liu+"。");
			}
        });
        
        
        //换一换5个
        var i = 1;
        function changeH(){
        	i++;
        	var pagecurr =i;
        	if(i==4){//4页 前20
        		i=0;
        	}
        	var path = $("#ipath").val();
        	
			$.ajax({
               type:"post",
           	   url:"changeH.do",
               data:{"pagecurr":pagecurr},
               success:function(msg){
               		hotList = msg.hotProducts;
               		var hotStr="";
                    for(var i=0;i<hotList.length;i++){
                     
                    	hotStr = hotStr+"<li><a href='"+path+"product/productInfo?info="+hotList[i].uuid+"'><img  src='"+webUrl+hotList[i].productShowPic+
                    			 "'><p class='head-name pc-pa10'>"+hotList[i].productName+"<br/><span style='color:#ea4949'>销量："+hotList[i].productSaleNum
                    		  	 +"</span></p><p class='label-default'>预定</p></a></li>";
                    }
                    $("#hotList").html(hotStr);
                    $("#hotList").attr("style","display:none");
                    $("#hotList").fadeIn(200);
                    }
                })   
        }
        
        
      
        
    $(window).scroll(function() {
        var h1 = $(document).scrollTop();
        var h2 = $("#blTop").offset().top;
        
        var i=0;
        $(".time-clear-f").each(function(index,obj){
    		var h3 = $(obj).offset().top;
    		if(h1+200+index*30>h3){
    		 	i=index+1;
    		 	return true;
    		}
    	
  		});
  			
         i > 0 ? $("#drop_down").show(200) : $("#drop_down").hide(200); 
   /*      i > 0 ? minidemo("#drop_down","slideLeftRetourn") : minidemo("#drop_down","rotateDown"); */
        $(".handler").css("background-color","white").css("color","black");
      	$($(".handler")[i-1]).css("background-color","green").css("color","white");
    });
    
    
    	//xmlhttprequest对象
	var xhr;
	//上传方法
	function uploadFile(){
		var fileObj = document.getElementById("file").files[0];//获取文件对象
		var url = "http://localhost:8080/guolaiwan-app-web/web/videoPic/upload.do"//接收文件地址
		var form = new FormData() //fromData对象
		form.append("file",fileObj);
		form.append("userId","1");
		

		xhr = new XMLHttpRequest(); // XMLHttpRequest对象
		xhr.withCredentials = true;//设置跨域操作;
		xhr.open("post",url,true);//post请求,URL地址,true异步请求
		
		xhr.onload = uploadComplete;//请求完成
		xhr.onerror = uploadFailed;//请求失败
		xhr.upload.onprogress  = progressFunction;//上传进度方法

		xhr.upload.onloadstart = function(){
			ot = new  Date().getTime();  //开始上传的时间
			oloaded = 0;//上传实文件的大小
		};

		xhr.send(form)//开始上传发送form
	}

	//上传成功的方法
	function uploadComplete(evt){
		var data = JSON.parse(evt.target.responseText);//返回的json数据
		if(data.msg=="success"){
			alert("上传成功!");
		}else{
			alert("上传失败!");
		}
	}
	//上传失败的方法
	function uploadFailed(evt){
		alert("上传失败!");
	}
	//取消上传
	function cancleUploadFile(){
		xhr.abort();
	}
	
	//进度条,上传过程中会频繁的调用该方法
	function progressFunction(evt){
		var progressBar = document.getElementById("progressBar");
		var percentageDiv = document.getElementById("percentage");
		if(evt.lengthComputable){
			progressBar.max = evt.total;//总字节
			progressBar.value = evt.loaded; //已经传输的字节
			percentageDiv.innerHTML =Math.round(evt.loaded / evt.total * 100) + "%";
		}
		var time = document.getElementById("time");

		var nt = new Date().getTime();//当前时间
		var pertime = (nt-ot)/1000//时间查
		ot = new  Date().getTime();//用于下次计算

		var perload = evt.loaded - oloaded;//大小差
		oloaded = evt.loaded;//用于下次计算

		var speed = perload/pertime;//速度单位b/s
		var bspeed = speed;
		var units = 'b/s';
		if(speed/1024>1){
            speed = speed/1024;
            units = 'k/s';
        }
       	if(speed/1024>1){
       	    speed = speed/1024;
       	    units = 'M/s';
       	}
		speed = speed.toFixed(1);
		//剩余时间
		var resttime = ((evt.total-evt.loaded)/bspeed).toFixed(1);
        time.innerHTML = '，速度：'+speed+units+'，剩余时间：'+resttime+'s';
        if(bspeed==0) time.innerHTML = '上传已取消';
	}
	
	
	
    </script>
  
   
     <script type="text/javascript">

$(function(){
    var num=1;
    var timer;
    var hasStarted = false;

    function showpic(index){
        $(".pic li").eq(index).show().siblings().hide();
        $(".dot li").eq(index).css("background","red").siblings().css("background","#fff");
    }
    $(".dot li").hover(function () {
        stop();
        num = $(this).index();
        $(".pic li").eq(num).show().siblings().hide();
        $(this).css("background","red").siblings().css("background","#fff");
    },start);
    $(".pic li").each(function(index){
        $(this).hover(function(){
            stop();
            /* show(index); */
            num = index+1;
        },start)
    });
    function start() {
        if(!hasStarted) {
            hasStarted = true;
            timer = setInterval(function(){
                showpic(num);
                num++;
                if(num== $(".pic li").size()){
                    num =0;
                }
            },4000);
        }
    }
    function stop() {
        clearInterval(timer);
        hasStarted = false;
    }
    start();
});
    </script>
  
</head>
<body>
 <%--   <div class="cemian1_img">
 <img src="<%=path %>/webtheme/theme/img/ad/8.png" style="position:absolute;left:5%;top:20%;z-index:110;" >
 </div>
  <div class="cemian2_img">
 <img src="<%=path %>/webtheme/theme/img/ad/9.png" style="position:absolute;right:5%;top:20%;z-index:110;" >
 </div> --%>
    <div>
        <div id="moquu_wxin" class="moquu_wxin"><a href="javascript:void(0)">
            <div class="moquu_wxinh"></div>
        </a></div>
        <div id="moquu_wshare" class="moquu_wshare" style="text-indent: 0;z-index:800"><a href="javascript:void(0)" style="text-indent: 0;">
            <div class="moquu_wshareh" style="z-index:800">
                <img onclick="location.href='http://<%=weburl%>/download/guolaiwanV1.0.1.apk'" src="<%=path %>/webtheme/theme/icon/moquu_wshare.png" width="100%" ></div>
        </a></div>
        <div id="moquu_wmaps"><a  href="http://wpa.qq.com/msgrd?v=3&amp;uin=442161617&amp;site=qq&amp;menu=yes" target="_blank" class='moquu_wmaps'></a></div>
        <a id="moquu_top" href="javascript:void(0)"></a>
    </div>

    <!--- header begin-->
    <c:import url="${path}/web/top"></c:import>
    <!-- header End -->

    <!--- banner begin-->
    <section id="pc-banner" style="position:relative; height:480px;">
    	<!-- style="position:absolute; left:260px; right:360px; top:0; bottom:0;" -->
        <div class="yBanner" style="width:1200px; margin:auto; position:relative;">
            <div class="banner" id="banner" style="position:absolute; left:190px; right:252px; top:0; bottom:0;">
	         <%--    <c:if test="${carousels != null}">
		            <c:forEach items="${carousels}" var="c">
		             	<a href="${c.slideurl}" class="d1" style="background: url(${sysconfig.webUrl}${c.slidepic}) center no-repeat; background-color: white;"></a>
		            </c:forEach>
	            </c:if>
                <a href="javascript:;" class="d1" style="background: url(<%=path %>/webtheme/theme/img/ad/banner2.jpg) center no-repeat; background-color: #a96ae3; padding-left: 180px;"></a>
                <a href="javascript:;" class="d1" style="background: url(<%=path %>/webtheme/theme/img/ad/banner3.jpg) center no-repeat; background-color: #081f3c; padding-left: 180px;"></a>
                <a href="javascript:;" class="d1" style="background: url(<%=path %>/webtheme/theme/img/ad/banner4.jpg) center no-repeat; background-color: #4684ff; padding-left: 180px;"></a>
                <a href="javascript:;" class="d1" style="background: url(<%=path %>/webtheme/theme/img/ad/banner5.jpg) center no-repeat; background-color: #a89d9f; padding-left: 180px;"></a>
                --%>
          <div class="pic">

           <ul>
                     <c:forEach items="${carousels}" var="c">
                       <li>
		             	 <a href="${c.slideurl}" class="d1" style="background: url(${sysconfig.webUrl}${c.slidepic}) center no-repeat; background-color: white;"></a>
		               </li>
		            </c:forEach>
            </ul>
       
        </div>
               <!--  <div class="d2" id="banner_id">
                    <ul style="left:0;">
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
         -->
            </div>
            <!-- <div style="text-align: center; clear: both"></div> -->
            <div class="frame-right" style="width:250px; height:100%; position:absolute; right:0; top:0; text-align:center;">
            	<div style="width:100%; height:156px; margin-bottom:4px; margin-top:4px; border:1px solid #e0e0e0; box-sizing:border-box;">
            		<video id="company-video" src="" controls="controls" style="margin:0; width:100%; height:100%; float:left;"></video>
	        		<c:if test="${videos != null}">
	        			<img class="company-video-change" id="company-video-prev" src="<%=path %>/webtheme/theme/icon/m-fl.png" style="position:absolute; left:0; top:60px; cursor:pointer;"/>
	        			<img class="company-video-change" id="company-video-next" src="<%=path %>/webtheme/theme/icon/m-fr.png" style="position:absolute; right:0; top:60px; cursor:pointer;"/>
	        			<c:forEach items="${videos}" var="video">
	        				<input class="company-videos" type="hidden" value="${video.slideurl}" />
	        			</c:forEach>
	        		</c:if>
            	</div>
            	<div style="width:100%; height:142px; margin-bottom:4px;">
            		<div style="padding:5px; color:#fff; background-color:rgb(249, 192, 32); text-align:center;
	    			float:left; width:100%; box-sizing:border-box; height:28px;">
	        			<span>热门推荐</span>
	        		</div>
	        		<a>
	        			<img id="company-special" style="width:100%; height:114px;"/>
	        			<div id="company-special-name" class="coms-title" style="position:absolute;bottom:170px;cursor:pointer;height:0px;padding:0px; border-top:1px solid #e0e0e0; margin-top:0; background-color:#5c5c5c; color:#fff;
                             width:100%; box-sizing:border-box;opacity: 0">&nbsp;</div>
                        <c:if test="${specials != null}">
	        				<c:forEach items="${specials}" var="special">
	        					<input class="company-specials" type="hidden" value="${special.slidepic}" data-href="${special.slideurl}" data-name="${special.name}" />
	        				</c:forEach>
	        			</c:if>
	        		</a>
            	</div>
            	<div style="width:100%; heigth:170px;">
            		<div style="padding:5px; color:#fff; background-color:rgb(249, 192, 32); text-align:center;
	    			float:left; width:100%; box-sizing:border-box; height:28px;">
	        			<span>过来玩公司</span>
	        		</div>
	        		<a id="company-link">
	        			<img id="company-img" style="width:100%; height:113px; float:left;"/>
	        			<span id="company-name" class="comb-title" style="display:block; padding:5px; border-top:1px solid #e0e0e0; margin-top:0; background-color:#5c5c5c; color:#fff;
		    				float:left; width:100%; box-sizing:border-box;">&nbsp;</span>
	        			<c:if test="${comps != null}">
	        				<c:forEach items="${comps}" var="comp">
	        					<input class="company-infos" type="hidden" value="${comp.pic}" data-href="<%=path %>/index?com=${comp.comCode}" data-name="${comp.comName}" />
	        				</c:forEach>
	        			</c:if>
	        		</a>
            	</div>
	        </div>
        </div>
    </section>
    <!-- banner End -->
		
    <!--- advert begin-->
    <%-- <section id="pc-advert">
        <div class="container-c clearfix">
            <a href="#" target="_blank">
                <img src="<%=path %>/webtheme/theme/img/pd/pd1.png"></a>
            <a href="#" target="_blank">
                <img src="<%=path %>/webtheme/theme/img/pd/pd2.png"></a>
            <a href="#" target="_blank">
                <img src="<%=path %>/webtheme/theme/img/pd/pd3.png"></a>
            <a href="#" target="_blank">
                <img src="<%=path %>/webtheme/theme/img/pd/pd4.png"></a>
        </div>
    </section> --%>
    <!-- advert End -->

    <!-- 商城资讯 begin -->
    <!-- <section id="pc-information">
        <div class="containers">
            <div class="pc-info-mess  clearfix" style="position: relative;">
                <h2 class="fl" style="margin-right: 20px;">平台快讯</h2>
                <div id="MarqueeDiv" class="MarqueeDiv">
                    <a href="new.html">[特惠]新一代Moto 360智能手表登陆</a>
                    <a href="new.html">[特惠]洗护节 跨品牌满199减100</a>
                    <a href="new.html">[特惠]仁怀政府开启“仁怀酱香酒馆”</a>
                    <a href="new.html">[特惠]洗护节 跨品牌满199减100</a>
                    <a href="new.html">逛商城赚话费，商城通信51城全线抢购 </a>
                    <a href="new.html">文艺蓝牙音箱 火热众筹中 </a>
                    <a href="new.html">[公告]第1000家商城帮服务店落户遵义</a>
                    <a href="new.html">[特惠]新一代Moto 360智能手表登陆</a>
                    <a href="new.html">[特惠]新一代Moto 360智能手表登陆</a>
                    <a href="new.html">[特惠]新一代Moto 360智能手表登陆</a>
                </div>
                <a href="new.html" style="position: absolute; right: 15px; top: 0;">更多资讯</a>
            </div>
        </div>
    </section> -->
    <!-- 商城资讯 End -->

    <!-- 限时抢购 begin -->
    
    <!-- 限时抢购 End -->

    <!-- 卖场推荐 begin -->
    <div class="drop_down" id="drop_down" style="width: 64px; height:343px; overflow: visible; top: 70px; position: fixed; left: 0px; display: none;
						border:1px solid #DCDCDC;z-index:960;left: 50%;top: 50%;margin: -320px 0px 0px -665px">
			<div class="drop_down1" style="width:64px;height:42px;text-align:center;color:#228B22;line-height:45px;">过来玩</div>
				<ul>
					<c:forEach items="${modulars}" var="m">
					    <c:if test="${m.merchants!= null && fn:length(m.merchants) != 0}">
						<a href="#${m.uuid}"><li class="handler">${m.modularName}</li></a>
						</c:if>
					</c:forEach>
					<c:forEach items="${activitys}" var="a">
					  <c:if test="${a.products!= null && fn:length(a.products) != 0}">
						<a href="#${a.uuid}"><li class="handler" style=" overflow: hidden;">${a.name}</li></a>
					  </c:if>	
					</c:forEach>
				</ul>
		</div>

    <div class="time-lists clearfix">
        <div class="time-list time-list-w fl">
            <div class="time-title time-clear">
                <h2>热卖区</h2>
                <div class="pc-font fl"></div>
                <a class="pc-spin fr" onclick="changeH()">换一换</a> </div>
            <div class="time-border">
                <div class="yScrollList">
                    <div class="yScrollListIn">
                        <div style="display: block;" class="yScrollListInList yScrollListInList1">
                            <ul id="hotList">
                            	<c:forEach items="${hotProducts}" var="hpro">
                                <li>
                                    <a href="<%=basePath%>product/productInfo?info=${hpro.uuid}">
                                        <img  src="${sysconfig.webUrl}${hpro.productShowPic}">
                                        <p class="head-name pc-pa10">${hpro.productName}<br/><span style="color:#ea4949">销量：${hpro.productSaleNum}</span></p>
                                        <p class="label-default">预定</p>
                                    </a>
                                </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<div id="blTop"></div>
	<c:forEach items="${modulars}" var="m">
	<c:if test="${m.merchants!= null && fn:length(m.merchants) != 0}">
	<c:choose>
	<c:when test="${m.modularCode=='01112'}">
	
	<div class="time-lists  clearfix">
        <div class="time-list time-list-w fl" id="${m.uuid}">
            <div class="time-title time-clear-f">
                <span style="float:left;width:5px;height:28px;display:block;background-color:#2bad4a"></span><h2>${m.modularName}</h2>
                <ul class="brand-tab H-table clearfix fr" id="H-table">
                    <c:forEach items="${m.modularClasses}" var="mc">
                    <li><a href="<%=path %>/product/productList?m=${m.modularCode}&mc=${mc.classCode}">${mc.className}</a></li>
                    </c:forEach>
                    <li><a href="<%=path %>/product/productList?m=${m.modularCode}">更多>></a></li>
                </ul>
            </div>
            <div class="time-border time-border-h clearfix">
                <div class="brand-poa fl">
                    <div class="brand-poa H-over clearfix">
                        <ul>
                        	<c:forEach items="${m.products}" var="mp">
                            <li>
                                <div class="brand-imgss"><a href="<%=basePath%>product/productInfo?info=${mp.uuid}">
                                    <img width="210px" height="140px" src="${sysconfig.webUrl}${mp.productShowPic}"></a></div>
                                <div class="brand-title"><a href="<%=basePath%>product/productInfo?info=${mp.uuid}">${mp.productName}</a> </div>
                                <div class="brand-price"><span>${mp.productPrice}</span><del>${mp.productOldPrice}</del></div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
	</c:when>
	<c:otherwise>
	<div class="time-lists  clearfix">
        <div class="time-list time-list-w fl" id="${m.uuid}">
            <div class="time-title time-clear-f">
                <span style="float:left;width:5px;height:28px;display:block;background-color:#2bad4a"></span><h2>${m.modularName}</h2>
                <ul class="brand-tab H-table clearfix fr" id="H-table">
                    <c:forEach items="${m.modularClasses}" var="mc">
                    <li><a href="<%=path %>/web/merchant/merchantList?m=${m.modularCode}&mc=${mc.classCode}">${mc.className}</a></li>
                    </c:forEach>
                    <li><a href="<%=path %>/web/merchant/merchantList?m=${m.modularCode}">更多>></a></li>
                </ul>
            </div>
            <div class="time-border time-border-h clearfix">
                <div class="brand-poa fl">
                    <div class="brand-poa H-over clearfix">
                        <ul>
                			<c:forEach items="${m.merchants}" var="mm">
                    			<li>
                        			<div class="brand-imgss" style="padding: 10px 0 0 10px;"><a href="<%=basePath%>web/merchant/productList?merchant=${mm.uuid}">
                           	 		<img width="210px" height="140px" src="${sysconfig.webUrl}${mm.shopPic}"></a></div>
                        			<div class="brand-title" style="padding: 0 15px 16px 15px;"><a href="<%=basePath%>web/merchant/productList?merchant=${mm.uuid}">${mm.shopName}</a> </div>
                    			</li>
                    		</c:forEach>	
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
	</c:otherwise>
	</c:choose>
	</c:if>
    </c:forEach>
   <c:forEach items="${activitys}" var="activity">
   <c:if test="${activity.products!= null && fn:length(activity.products) != 0}">
	<div class="time-lists  clearfix">
        <div class="time-list time-list-w fl" id="${activity.uuid}">
            <div class="time-title time-clear-f">
                <span style="float:left;width:5px;height:28px;display:block;background-color:#2bad4a"></span><h2>${activity.name}</h2>
            	<ul class="brand-tab H-table clearfix fr" id="H-table">
                    <li><a href="<%=path %>/web/activity/productList?activity=${activity.uuid}">更多>></a></li>
                </ul>
            </div>
            <div class="time-border time-border-h clearfix">
                <div class="brand-poa fl">
                    <div class="brand-poa H-over clearfix">
                        <ul>
                        	<c:forEach items="${activity.products}" var="product">
                            <li>
                                <div class="brand-imgss"><a href="<%=basePath%>product/productInfo?info=${product.uuid}">
                                    <img width="210px" height="140px" src="${product.productShowPic}"></a></div>
                                <div class="brand-title"><a href="<%=basePath%>product/productInfo?info=${product.uuid}">${product.productName}</a> </div>
                                <div class="brand-price"><span>${product.productPricesStr}</span><del>${product.productOldPriceStr}</del></div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:if>
    </c:forEach> 
     <!--  <div id="sse">
         <a href="javascript:WebSocketTest()">运行 WebSocket</a>
          <a href="javascript:sendMess()">发送消息</a>
          
      </div> -->
    <div class="containers main-banner">
       <%--  <img src="<%=path %>/webtheme/theme/img/ad/br1.jpg" width="1200" height="105"></a> </div> --%>

    <%-- <div class="time-lists clearfix">
        <div class="time-list fl">
            <div class="time-title time-clear">
                <h2>好店推荐 </h2>
            </div>
            <div class="time-border time-border-h clearfix">
                <div class="fl shop-img">
                    <div class="shop-title"><a href="#">
                        <img src="<%=path %>/webtheme/theme/img/ad/shop1.png"></a></div>
                    <div class="shop-text">
                        <h2>熊太子坚果炒货金冠店铺...</h2>
                        <p>[正品 有赠品 如实描述]</p>
                    </div>
                    <div class="shop-work clearfix"><a href="#">
                        <img src="<%=path %>/webtheme/theme/img/ad/shop2.png"></a><a href="#"><img src="<%=path %>/webtheme/theme/img/ad/shop3.png"></a> </div>
                </div>
                <div class="shop-bar clearfix fl">
                    <ul>
                        <li>
                            <div class="shop-icn"><a href="#">
                                <img src="<%=path %>/webtheme/theme/img/ad/shop4.png"></a></div>
                            <div class="shop-tex"><a href="#">阿迪王品牌店铺</a> </div>
                        </li>
                        <li>
                            <div class="shop-icn"><a href="#">
                                <img src="<%=path %>/webtheme/theme/img/ad/shop4.png"></a></div>
                            <div class="shop-tex"><a href="#">阿迪王品牌店铺</a> </div>
                        </li>
                        <li>
                            <div class="shop-icn"><a href="#">
                                <img src="<%=path %>/webtheme/theme/img/ad/shop4.png"></a></div>
                            <div class="shop-tex"><a href="#">阿迪王品牌店铺</a> </div>
                        </li>
                        <li>
                            <div class="shop-icn"><a href="#">
                                <img src="<%=path %>/webtheme/theme/img/ad/shop4.png"></a></div>
                            <div class="shop-tex"><a href="#">阿迪王品牌店铺</a> </div>
                        </li>
                        <li>
                            <div class="shop-icn"><a href="#">
                                <img src="<%=path %>/webtheme/theme/img/ad/shop4.png"></a></div>
                            <div class="shop-tex"><a href="#">阿迪王品牌店铺</a> </div>
                        </li>
                        <li>
                            <div class="shop-icn"><a href="#">
                                <img src="<%=path %>/webtheme/theme/img/ad/shop4.png"></a></div>
                            <div class="shop-tex"><a href="#">阿迪王品牌店铺</a> </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="news-list fr">
            <div class="time-title time-clear">
                <h2>店铺销量top排行</h2>
            </div>
            <div style="border-left: none;" class="time-border time-border-h">
                <ul class="shop-top">
                    <li class="clearfix">
                        <div class="shop-name fl"><a href="#">
                            <img src="<%=path %>/webtheme/theme/img/ad/top1.png"></a></div>
                        <div class="shop-titl fl">
                            <p><b>NO.1 阿卡官方旗舰店</b></p>
                            <p>梦想会喜欢 <span class="fr red">已售出：3000+</span></p>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="shop-name fl"><a href="#">
                            <img src="<%=path %>/webtheme/theme/img/ad/top1.png"></a></div>
                        <div class="shop-titl fl">
                            <p><b>NO.1 阿卡官方旗舰店</b></p>
                            <p>梦想会喜欢 <span class="fr red">已售出：3000+</span></p>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="shop-name fl"><a href="#">
                            <img src="<%=path %>/webtheme/theme/img/ad/top1.png"></a></div>
                        <div class="shop-titl fl">
                            <p><b>NO.1 阿卡官方旗舰店</b></p>
                            <p>梦想会喜欢 <span class="fr red">已售出：3000+</span></p>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="shop-name fl"><a href="#">
                            <img src="<%=path %>/webtheme/theme/img/ad/top1.png"></a></div>
                        <div class="shop-titl fl">
                            <p><b>NO.1 阿卡官方旗舰店</b></p>
                            <p>梦想会喜欢 <span class="fr red">已售出：3000+</span></p>
                        </div>
                    </li>
                    <li class="clearfix">
                        <div class="shop-name fl"><a href="#">
                            <img src="<%=path %>/webtheme/theme/img/ad/top1.png"></a></div>
                        <div class="shop-titl fl">
                            <p><b>NO.1 阿卡官方旗舰店</b></p>
                            <p>梦想会喜欢 <span class="fr red">已售出：3000+</span></p>
                        </div>
                    </li>
                </ul>
            </div>
        </div> --%>
    </div>
    
	<c:import url="${path}/web/foot"></c:import>
	

    <div class="footer_div">

        <div class="footer_con">

            <div class="footer_con1"> <img src="<%=path %>/webtheme/theme/img/ditu2.png" width="100%" /> </div>
            <div class="footer_con3" ><img src="<%=path %>/webtheme/theme/img/close.png" width="30" height="30"/></div>


        </div>

        <div class="footer_block" >

            <img src="<%=path %>/webtheme/theme/img/bj02.png" />

        </div>

    </div>
    

    
    
</body>
    <script src="js/jquery.js"></script>

    <script type="text/javascript">

       
        $(function () {
                $(".footer_div").animate({ "bottom": "10%" });
      
            $(".footer_con3").click(function () {
                $(".footer_div").animate({ "left": "-100%" }, function () { $(".footer_con").hide(); $(".footer_block").show(); $(".footer_div").animate({"left":"-90%"}) });
               
            })

            $(".footer_block").click(function () {
                $(".footer_div").animate({ "left": "-110%" }, function () { $(".footer_block").hide(); $(".footer_con").show(); $(".footer_div").animate({ "left": "0" }); })
            })
           
            
        })

      
    </script>
<script type="text/javascript">
	$(function(){
		
		//切换推荐视频
		var changeVideo = function(index){
			var $vidoes = $('.company-videos');
			if(index < $vidoes.length){
				var src = webUrl.replace('/file/', '') + $($vidoes[index]).val();
				$('#company-video').attr('src', src);
				return true;
			}else{
				return false;
			}
		};
		
		var currVideo = 0;
		var init = changeVideo(currVideo);
		if(!init){
			//没有推荐视频
		}else{
			$('#company-video-prev').on('click', function(){
				if(currVideo == 0){
					alert('这已经是第一个视频了！');
				}else{
					currVideo = currVideo - 1;
					changeVideo(currVideo);
				}
			});
			
			$('#company-video-next').on('click', function(){
				var result = changeVideo(currVideo + 1);
				if(result){
					currVideo = currVideo + 1;
				}else{
					alert('这已经是最后一个视频了！');
				}
			});
		}
	
		//切换特别推荐
		var changeSpecial = function(index){
			var $specials = $('.company-specials');
			if(index < $specials.length){
			    var $special = $($specials[index]);
				var src = webUrl + $special.val();
				var href = $special.data('href');
				var $img = $('#company-special');
                var name = $special.data('name');
                var $name = $('#company-special-name');
				$img.attr('src', src);
				$img.parent().attr('href', href);
                $name.text(name);
				return true;
			}else{
				return false;
			}
		}
		
		$("#company-special").hover(function(){
            $("#company-special-name").stop(true,true).animate({height:"30px",padding:"5px",opacity:1},200);
        }, function(){
            $("#company-special-name").stop(true,true).animate({height:"0px",padding:"0px",opacity:0},200);
        });
		
		var currSpecial = 0;
		init = changeSpecial(currSpecial);
		
		if(!init){
			//没有特别推荐
		}else{
			//轮询切换
			setInterval(function(){
				currSpecial = currSpecial + 1;
				var result = changeSpecial(currSpecial);
				if(!result){
					currSpecial = 0;
					changeSpecial(currSpecial);
				}
			}, 2000);
		}
		
		//切换公司
		var changeCompany = function(index){
			var $infos = $('.company-infos');
			if(index < $infos.length){
			    var $info = $($infos[index]);
				var src = webUrl + $info.val();
				var href = $info.data('href');
				var name = $info.data('name');
				var $img = $('#company-img');
				var $name = $('#company-name');
				$img.attr('src', src);
				$img.parent().attr('href', href);
				$name.text(name);
				return true;
			}else{
				return false;
			}
		}
		
		var currComp = 0;
		init = changeCompany(currComp);
		
		if(!init){
			//没有公司列表
		}else{
			//轮询切换
			setInterval(function(){
				currComp = currComp + 1;
				var result = changeCompany(currComp);
				if(!result){
					currComp = 0;
					changeCompany(currComp);
				}
			}, 4000);
		}
				
	});
	 var ws = new WebSocket("ws://localhost:8080/guolaiwan-app-web/phoneApp/message");
	 //var ws = new WebSocket("ws://<%=weburl%>/guolaiwan/phoneApp/message");
	function WebSocketTest()
         {
            if ("WebSocket" in window)
            {
               alert("您的浏览器支持 WebSocket!");
               
               // 打开一个 web socket
              
				
               ws.onopen = function()
               {
                  // Web Socket 已连接上，使用 send() 方法发送数据
                  //ws.send('{"key":"live-33-79"}');
                  alert("数据发送中...");
                 // ws.send('{"key":"live-9-79-M"}');
                  alert("数据发送中...");
               };
				
               ws.onmessage = function (evt) 
               { 
                  var received_msg = evt.data;
                  console.log("数据已接收...");
                  console.log(evt);
                  alert(evt);
               };
				
               ws.onclose = function()
               { 
                  // 关闭 websocket
                  alert("连接已关闭..."); 
               };
            }
            
            else
            {
               // 浏览器不支持 WebSocket
               alert("您的浏览器不支持 WebSocket!");
            }
         }
	function sendMess(){
	  ws.send('{"key":"live-9-1-M"}');
	}
</script>
</html>
