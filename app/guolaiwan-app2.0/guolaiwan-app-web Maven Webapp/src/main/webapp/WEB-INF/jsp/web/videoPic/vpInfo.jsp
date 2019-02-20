<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>过来玩杯</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/lib/layui/css/layui.css" media="all">
	<style>
		#lmPic li{opacity:0.5}
		#lmPic li:hover{opacity:1}
		#lmPic .pl-no{opacity:0.5}
		#lmPic .pl-ed{opacity:1}
		.main {
            background-color: #fff;
            width:323px;
            margin-top:20px;
        }
        .main>div {
            width: 31%;
            border: 1% solid #fff;         
            background-color: #E78326;
            border-radius: 3%;
            float: left;
            margin: 1% 1% 1% 0;
        }
        .fanhui{
        
        	color:#49ab4f;
        
        
        }
	</style>
</head>
 <body style="background:#F5F5F5;">
 
 
 
 
<c:import url="${path}/web/top"></c:import>
<div class="containers" >
	<div class="pc-nav-item"></div>
	<div style="width:1024px;margin:0 auto;margin-left:130px;flost:left;margin-top:-20px;font-size:17px;color:#49ab4f;"><a href="javascript:history.go(-1)" >返回</a></div>
</div>
<div class="containers clearfix" style="margin-left:250px auto;background:#FFFFFF;"></div>
<div style="width:1024px;margin:0 auto;margin-top:-26px;background:#ffffff">
    <div class="pageContent1" style="width:800px;height:63px;">
    	
       <div style="width:1024px;height:10px;"></div>
 	   <div style="width:50px;height:50px;float:left;margin-top:30px;margin-left:40px; border-radius:50%;background:url(${videoPic.user.userHeadimg}) no-repeat;background-size:cover;"></div>
 	   <div style="height:20px;flost:left;margin-top:33px;margin-left:120px;font-size:15px;color: #49ab4f;">${videoPic.user.userNickname}</div>
 	   <div style="width:80px;height:20px;flost:left;margin-top:8px;margin-left:120px;">${videoPic.updateTime}</div>
 	</div> 	
 	<div class="pageContent2" style="width:800px;height:auto !important;min-height:0px;margin-top:32px;margin-left:120px;">
   	   <div class="pageContent3" style="width:770px;height:auto !important;height:500px;min-height:0px;font-size:16px;font-family:SimHei;color:#111;line-height:23px;">
			<span style="" id="ptitle">
			
			</span>
       </div>
       <c:choose>
		<c:when test="${videoPic.type=='图文'}">
       <div style="width:800px;height:auto">
       		<div class="main" id="9div" hidden="hidden">
    		</div>
  	   		<div  class="pageContent4"   style="margin-top:10px;height:auto">
  	   		<div id="plist">
  	   	
  	   		</div>

  	   		<div>
  	   		<div style="">
  	   			<em id="cutpic" onclick="cutpic()"  hidden =hidden ;style="background: url(<%=path %>/webtheme/theme/img/videoPic/left.png) no-repeat right center;position: absolute; cursor: pointer; z-index: 10; display: inline-block; height:400px; width: 45px;"></em>
  	   			<img src="${videoPic.fUrl}" id ="bigpic" style="width: 704px;display: inline;">
  	   			<em id="addpic" onclick="addpic()"  hidden =hidden ;style="background: url(<%=path %>/webtheme/theme/img/videoPic/right.png) no-repeat right center;position: absolute; cursor: pointer; z-index: 10; right:358px; display: inline-block; height: 400px; width: 45px;"></em>
  	   		</div>
  	   		</div>
  	   		<c:if test="${videoPic.fUrl!=''}">
  	   		<div style="width:750px;height:65px;margin-top:8px;marght-left:33px;flost:left;" id="lmPic"></div>
  	   		</c:if>
  	   		<div style="width:900px;margin-left:-80px;">
  	   		<div style="font-size:15px;margin:0 20px 20px 690px;"><a><img style="cursor: pointer;" id="dianzanImg" width="18px" height="18px"  src="<%=path %>/webtheme/theme/img/videoPic/dianzan.png"></a>
  			<span id="praiseCount" style="margin-left:6px;;">${videoPic.praiseCount}</span>
  			<span style="margin-left:20px;"><img src="<%=path %>/webtheme/theme/img/videoPic/huifu1.png"</span>
  			<span style="margin-left:6px;">${videoPic.commentCount}</span>
  		</div>
  		<div contenteditable="true" id="commentText" style="margin-left:79px;margin-top:10px;width:780px;height:100px;flost:left;border: none; border:1px solid #cee3e9;background:#f1f7f9;"></div>
  		<div style="width:77px;height:24px;margin-top:5px;margin-left:790px;"><img style="cursor: pointer;" src="<%=path %>/webtheme/theme/img/videoPic/pinglun.png" onclick="comment()"></div>
  		</div>
  	   </div>
  	   </c:when>
  	   
  	   
  	   
  	   
  	   
  	   <c:otherwise>
  	    <div style="width:800px;">
            	<div style="margin-bottom:4px; margin-top:4px; ">
            	<div style="width:600px;height:520px;margin-top:40px">
            	<video id="lvideo" src="" controls="controls" style=" width:500px; height:500px; float:left;"></video>
       			 </div>
          	<div style="width:900px;margin-left:-80px;">
          	<div style="font-size:15px;margin:0 20px 20px 690px;"><a><img style="cursor: pointer;" id="dianzanImg" width="18px" height="18px" src="<%=path %>/webtheme/theme/img/videoPic/dianzan.png"></a>
  			<span id="praiseCount" style="margin-left:6px;;">${videoPic.praiseCount}</span>
  			<span style="margin-left:20px;"><img src="<%=path %>/webtheme/theme/img/videoPic/huifu1.png"</span>
  			<span style="margin-left:6px;">${videoPic.commentCount}</span>
  		</div>
  		<div contenteditable="true" id="commentText" style="margin-top:10px;width:780px;height:100px;flost:left;border: none; border:1px solid #cee3e9;background:#f1f7f9;"></div>
  		<div style="width:77px;height:24px;margin-top:5px;margin-left:710px;"><img src="<%=path %>/webtheme/theme/img/videoPic/pinglun.png" onclick="comment()"></div>
  		</div>
  	   </div>
       </div>
       </c:otherwise>
       </c:choose>
  	   

  	</div>
  			<div id="commentsLiu" style="margin-top:50px;margin-left:40px;width:784px;">
  		
  			</div>
  			</div>  
</div>
		
 		 </div>
 	 	</div>
  		</div>
  		</div>
  		</div>
  		</div>
  	
  	
  	
 <div id="view" hidden="hidden"></div>   

<c:import url="${path}/web/foot"></c:import>
  <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>

        <script>
var path = "<%=path%>";
var vpId = "${videoPic.id}";
var type = "${videoPic.type}";
var pages = "${pages}";
var pics='${videoPic.fUrl}';
var vpId =  '${videoPic.id}';
var index = 0;
var picz =pics.split(',');
var isPraise =  '${videoPic.isPraise}';
    layui.use(['flow','layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              flow = layui.flow;
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;
              table = layui.table;//模板引擎
              //以上模块根据需要引入
             
           
 			  if(type=='图文'){
 			     var nlist=${videoPic.content};
 			    $("#ptitle").html(nlist.title);
 			  	$.each(nlist.content,function(i,item)
 			  	{
 			  	   if(item.type=="1"){
 			  		$("#plist").append("<p>"+item.text+"</p>");
 			  		 }else{
 			  		 $("#plist").append("<p style='width:100%;text-align:center'><img src='"+item.img+"' style='width:100%;width:400px;height:400px' /></p>");
 			  		 if(item.text)
 			  		 $("#plist").append("<p style='width:100%;text-align:center'>"+item.text+"</p>");
 			  		 }
 			  	})
 			  	
 			 
			  //视频
 			  }else{
 			    
 			    
 			    
 			    try
				  {
				  $("#ptitle").html(${videoPic.content});
				  }
				catch(err)
				  {
				  //在这里处理错误
				  }
 			    
 			    
 			  	$('#lvideo').attr('src',pics);
 			  } 
 			  
 		if(isPraise=="1"){
 			$("#dianzanImg").attr("src",path+"/webtheme/theme/img/videoPic/dianzaned.png");
 		}else{
 			$("#dianzanImg").attr("src",path+"/webtheme/theme/img/videoPic/dianzan.png");
 		}	  			  
        $("#dianzanImg").click(function(){
        	console.log(this);
        	$.ajax({
				url:"../praise.do"
				,type:"post"
				,data:{"vpId":vpId}
				,success:function(msg){
					console.log(msg);
					if(msg.code=="1"){
						$("#dianzanImg").attr("src",path+"/webtheme/theme/img/videoPic/dianzaned.png");
						$("#praiseCount").text(parseInt($("#praiseCount").text())+1);
					}else if(msg.code=="0"){
						$("#dianzanImg").attr("src",path+"/webtheme/theme/img/videoPic/dianzan.png");
						$("#praiseCount").text(parseInt($("#praiseCount").text())-1);
					}else if(msg.status==403&&msg.message=="未获取到用户！"){
						var nowUrl = window.location.href;
    					nowUrl = nowUrl.replace("http://<%=weburl%>/","").replace(basePath,"").replace("&","!");
    					window.location.href = basePath+"user/login?rul="+path+"/"+nowUrl;
					}else{
					}
				}
			})
        })  

              
 flow.load({
    elem: '#commentsLiu' //指定列表容器
    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
      var lis = [];
      //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
      setTimeout($.get(path+'/web/videoPic/pageCommmets?vpId='+vpId+'&pageSize=10&page='+page, function(res){
           //使用引擎模板
				var data= {
					"vpComments":res.vpComments
				}
				var getTpl = commentTpl.innerHTML
				,view = document.getElementById('view'); 
				laytpl(getTpl).render(data, function(html){
  					view.innerHTML = html;
				});
		lis.push($("#view").html());
        next(lis.join(''), page < pages);    
      }), 1000);
    }
  }); 
  })




function pic(obj){
	var a = $(obj).attr("src");
	$("#bigpic").attr("src",a);
}	
//评论
function comment(){
	layer.load(1);
	var commentText = $("#commentText").text();
	console.log(commentText);
	$.ajax({
		type:"post"
		,data:{"vpId":vpId,"commentText":commentText}
		,url:"../comment.do"
		,success:function(msg){
			if(msg.status==200){
				layer.closeAll("loading");
				layer.msg("评论成功！",{icon:1,time:1000},function(){
					window.location.reload();
				});
			}else if(msg.status==403&&msg.message=="未获取到用户！"){
				var nowUrl = window.location.href;
    			nowUrl = nowUrl.replace("http://<%=weburl%>/","").replace(basePath,"").replace("&","!");
    			window.location.href = basePath+"user/login?rul="+path+"/"+nowUrl;
			}else{
			}
		}
	
	})

}

function addpic(){
	if(index<picz.length-1){
		index++;
	}
	$('#bigpic').attr('src',picz[index]);
	$($("#lmPic").find("li")[index]).attr("class","pl-ed");
	$($("#lmPic").find("li")[index]).siblings().attr('class','pl-no');
}
function cutpic(){
	if(index>0){
		index--;
	}
	$('#bigpic').attr('src',picz[index]);
	$($("#lmPic").find("li")[index]).attr("class","pl-ed");
	$($("#lmPic").find("li")[index]).siblings().attr('class','pl-no');

}
</script>
<script type="text/html" id="commentTpl">
{{#  layui.each(d.vpComments, function(index, item){ }}
	<div style="border:none;border-bottom:1px solid LightGrey;">
  				<div style="width:900px;height:10px;"></div>
				<div style="width:50px;height:50px;float:left;margin-top:30px; border-radius:50%;background:url({{ item.user.userHeadimg }}) no-repeat;background-size:cover;"></div>
			  	<div style="width:80px;height:20px;margin-top:38px;margin-left:60px;font-size:15px;color: #49ab4f;">{{ item.user.userNickname }}</div>
				<div style="width:757;height:20px;margin-left:60px;margin-top:5px;border: none;color:black;">{{ item.commentText }}</div>
 				<div style="width:80px;height:20px;margin-top:5px;margin-left:60px;">{{ item.updateTime }}</div>				
  	</div>
	
{{#  }); }}
</script>
</body>
</html>
