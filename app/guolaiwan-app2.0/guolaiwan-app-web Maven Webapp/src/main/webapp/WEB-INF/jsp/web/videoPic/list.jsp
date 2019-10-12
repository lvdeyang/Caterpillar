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
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/upload.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/lib/layui/css/layui.css" media="all">
	<style>
		hr{background-color: LightSteelBlue;margin-left:-75px;width:825px;
    		height: 1px;
    		border: none;}
    		
    	.inputDiv{width:978px;height:130px;float:left;border: none; border-bottom:1px solid #cee3e9;padding:20px 0 0 20px;resize:none;font-size: 16px}	
		.inputDiv::-webkit-input-placeholder {
          /* placeholder颜色  */
         color: #aab2bd;
     	}
     	.upload{width:96%;padding: 15px 10px;}
     	.upload.multiple{border: 0 dashed #ddd}
     	.upload.multiple.empty{width:96%}
     	.upload .item{border-width: 0}
     	li.item.add{cursor: pointer;}
     	.one{margin: 10px 10px;}
     	.main {
            background-color: #fff;
            width:323px;
            margin-left:30px;
            margin-top:10px;
        }
        .main>div {
            width: 31%;
            border: 1% solid #fff;         
            background-color: #E78326;
            border-radius: 3%;
            float: left;
            margin: 1% 1% 1% 0;
        }
        #videoPicsliu div{
        background: #E5E5E5 !important;
        }
	</style>
 </head>
 <body style="background:#E5E5E5;">
 
 
 
 
<c:import url="${path}/web/top"></c:import>

        
  <div class="pageContent" style="width:1000px;margin:0 auto;">
   
        
            <div class="pc-search clearfix" style="padding:10px 0 0 0">
                <div style="font-size:16px;float:left">大家都在写什么?</div>
                <div class="fr pc-search-in">
                   	<input type="text" id="pc-name"" style="width:100px">
                    <a href="javascript:search()" class="pc-search-a" >搜索</a>
                </div>
            </div>
   
  		
  		<div id="videoPicsliu">
  			
  		</div>
  		
  		
  </div>

 <div id="view" hidden="hidden"></div>
 <div id="type" hidden="hidden"></div>
<c:import url="${path}/web/foot"></c:import>
<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/jQuery.upload.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>

<script type="text/javascript">
var path = "<%=path%>";
var basePath = "<%=basePath%>";
var pages = "${pages}";


$(function(){
	$("#case1").upload(
		function(_this,data){
				console.log(data) 
		}		
	);
	$("#case2").upload(
		function(_this,data){
				console.log(data) 
		}		
	);
	
	
	
	  		$("#picbtn").click(function(event){
                    //取消冒泡事件
                    event.stopPropagation();//这句是必须
                    	$(".videoPicbtn").css("border-width","0px");
						$("#picbtn").css("border","1px dashed #ddd");
						$(".ttDiv").hide();
						$("#picUploadDiv").show();
						$("#type").val("PICTURE");
                });
                
           $("#videobtn").click(function(event){   
             	event.stopPropagation();//这句是必须  
                $(".videoPicbtn").css("border-width","0px");
				$("#videobtn").css("border","1px dashed #ddd");
				$(".ttDiv").hide();
				$("#videoUploadDiv").show();
				$("#type").val("LITTLEVEDIO");
			});

                 //点击空白或者其他区域时divTop隐藏
                $(document).click(function(){
                    $("#picUploadDiv").hide();
                    $("#videoUploadDiv").hide();
                });
                $("#videoUploadDiv").click(function(event){
                	event.stopPropagation();//这句是必须  
                });
                 $("#picUploadDiv").click(function(event){
                 	event.stopPropagation();//这句是必须  
                });
                 $("#vpContext").click(function(event){
                 	event.stopPropagation();//这句是必须  
                });
                
                

})


 layui.use(['flow','laytpl','layer'], function(){
  var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
  flow = layui.flow;
  laytpl = layui.laytpl;
  layer = layui.layer;
 
 
  
  
  flow.load({
    elem: '#videoPicsliu' //指定列表容器
    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
      var lis = [];
      //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
      setTimeout($.get(path+'/web/videoPic/listPage?pageSize=10&page='+page, function(res){
       
      $.each(res.videoPics,function(i,item){
      //console.log(res.videoPics.content.push(JSON.parse(item.content)));
      if(item.type=="图文"){
      //var option = new array();

      //option["ceshi"] = item.content;
      //console.log(eval('('+item.content+')'));
      var s="";
      s=item.content;
      
   
      item.nlist=JSON.parse(s);
      }
      }) 
  
           //使用引擎模板
				var data= {
					"videoPics":res.videoPics
				}
				var getTpl = videoPicTpl.innerHTML
				,view = document.getElementById('view');
				laytpl(getTpl).render(data, function(html){
  					view.innerHTML = html;
				});
		lis.push($("#view").html());
        next(lis.join(''), page < pages);    
      }), 1000);
    }
  });
});



function fabu(){
	console.log("1");
	var type  =  $("#type").val();
	var fUrl="";
	if(type=="LITTLEVEDIO"){
		fUrl=$("#case2").children("input[name='upload']").val();
		console.log(1);
		console.log(fUrl);
		if(fUrl==""){
		layer.msg("还没上传视频!")
		return false;
	}
	}else{
		type="PICTURE";
		fUrl=$("#case1").children("input[name='upload']").val();
	}
	
	
	var context = $("#vpContext").val();
	console.log(fUrl+"_"+type+"_"+context);
	$.ajax({
		type:"post"
		,url:"videoPic.do"
		,data:{"fUrl":fUrl,"type":type,"context":context}
		,success:function(msg){
			console.log(msg);
			if(msg.status==200){
				layer.msg("发布成功!",{icon:1,time:500},function(){
					window.location.reload();
				});
			}else if(msg.status==403 && msg.message=="未获取到用户！"){
				var nowUrl = window.location.href;
    			nowUrl = nowUrl.replace("http://<%=weburl%>/","").replace(basePath,"").replace("&","!");
    			window.location.href = basePath+"user/login?rul="+path+"/"+nowUrl;
			}else{
			
			}
		
		}
	
	
	})
	

}
function zan(obj,vpId){
	$.ajax({
		url:"praise.do"
		,type:"post"
		,data:{"vpId":vpId}
		,success:function(msg){
			console.log(msg);
			if(msg.code=="1"){
				console.log($(obj));
				$(obj).attr("src",path+"/webtheme/theme/img/videoPic/dianzaned.png");
				$("#"+vpId+"praiseCount").text(parseInt($("#"+vpId+"praiseCount").text())+1);
			
			}else if(msg.code=="0"){
				$(obj).attr("src",path+"/webtheme/theme/img/videoPic/dianzan.png");
				$("#"+vpId+"praiseCount").text(parseInt($("#"+vpId+"praiseCount").text())-1);
			}else if(msg.status==403 && msg.message=="未获取到用户！"){
				var nowUrl = window.location.href;
    			nowUrl = nowUrl.replace("http://<%=weburl%>/","").replace(basePath,"").replace("&","!");
    			window.location.href = basePath+"user/login?rul="+path+"/"+nowUrl;
			}else{
				
			}
		}
	})
}

</script>


<script type="text/html" id="videoPicTpl">
{{#  layui.each(d.videoPics, function(index, d){ }}

	

			<div style="background-color:#fff;border-radius:3px">
	<div class="pageContent1" style="width:800px;position:relative; left:80px;top:30px">
 			<div style="width:50px;height:50px;float:left;border-radius:50%;margin-left:-53px;margin-top:-3px;background:url({{   d.user.userHeadimg }}) no-repeat;background-size:cover;"></div>
 			<div style="width:500px;height:20px;flost:left;margin-top:30px;margin-left:10px;font-size:15px;color:#022856">{{   d.user.userNickname }}&nbsp;&nbsp<span style="color:black"> {{   d.updateTime }} <span> </div>
 			<div style="width:80px;height:20px;flost:left;margin-top:-22px;margin-left:200px;"></div>
 		</div> 	
 		<div class="pageContent2" style="position:relative; left:60px;top:30px;width:800px;height:auto !important;height:300px;min-height:0px;margin-top:10px;">
   		    <div class="pageContent3" style="width:770px;height:auto !important;height:200px;min-height:0px;font-size:16px;text-indent: 2em;font-family:SimHei;color:#111;line-height:23px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
				<span class="text1">
					{{# if(d.type ==="图文"){}}
						{{ d.nlist.title }}
					{{#  } else{ }}
							{{ d.content }}
                    {{#  } }}
                </span>
            </div>
{{#  if( d.type ==="小视频"){ }}
			<div style="width:680px;height:120px;margin-top:10px;">
			<div class="pageContent4" style="width:100px;height:100px;margin-top:10px;"><a href="<%=path%>/web/videoPic/vpInfo/{{   d.id }}"><img src="<%=path %>{{ d.headPic}}" style="width:200px;height:100px;margin-left:30px;"></a></div>
			</div>
		</div>
  		<div class="pageContent3" style="width:800px;margin-left:92px;margin-top:30px;">
		{{# if(d.isPraise === 1 ){ }}
			<div style="width:50px;height:24px;margin-top:20px;"><img onclick="zan(this,{{ d.id }})" style="cursor: pointer;" width="16px" height="16px" src="<%=path %>/webtheme/theme/img/videoPic/dianzaned.png"><span style="margin-left:8px;"  id="{{ d.id }}praiseCount">{{   d.praiseCount }}</span></div>
		{{# }else{ }}
			<div style="width:50px;height:24px;margin-top:20px;"><img onclick="zan(this,{{ d.id }})"  style="cursor: pointer;" width="16px" height="16px" src="<%=path %>/webtheme/theme/img/videoPic/dianzan.png"><span style="margin-left:8px;" id="{{ d.id }}praiseCount">{{   d.praiseCount }}</span></div>
		{{# } }}
			<div style="width:50px;height:24px;margin-top:-24px;margin-left:50px;"><a href="<%=path%>/web/videoPic/vpInfo/{{   d.id }}"><img src="<%=path %>/webtheme/theme/img/videoPic/huifu1.png"><span style="margin-left:8px;margin-top:-10px;">{{   d.commentCount }}</span></a></div>
			<div style="width:100px;height:24px;margin-top:-26px;margin-left:100px;"><a href="<%=path%>/web/videoPic/vpInfo/{{   d.id }}" ><span style="margin-left:8px;margin-top:-10px;">阅读全文>></span></a></div>
		</div>
	<!--视频的标签 src={{ d.furl }}-->
{{#  }else{  }}


{{# if(d.fUrl==""){ }}
<div class="main" id="9div" style="height:108px">
	<div><a href="<%=path%>/web/videoPic/vpInfo/{{   d.id }}"><img src="{{ d.nlist.cover }}" style="width:100px;height:100px;""></a></div>
</div>
{{#  } }}
  		</div> 
  		<div class="pageContent3" style="width:800px;margin-left:92px;margin-top:40px;">
		{{# if(d.isPraise === 1 ){ }}
			<div style="width:50px;height:24px;margin-top:20px;"><img onclick="zan(this,{{ d.id }})"  style="cursor: pointer;" width="16px" height="16px" src="<%=path %>/webtheme/theme/img/videoPic/dianzaned.png"><span style="margin-left:8px;"  id="{{ d.id }}praiseCount">{{   d.praiseCount }}</span></div>
		{{# }else{ }}
			<div style="width:50px;height:24px;margin-top:20px;"><img onclick="zan(this,{{ d.id }})"  style="cursor: pointer;" width="16px" height="16px" src="<%=path %>/webtheme/theme/img/videoPic/dianzan.png"><span style="margin-left:8px;"  id="{{ d.id }}praiseCount">{{   d.praiseCount }}</span></div>
		{{# } }}
			<div style="width:50px;height:24px;margin-top:-24px;margin-left:50px;"><a href="<%=path%>/web/videoPic/vpInfo/{{   d.id }}"><img src="<%=path %>/webtheme/theme/img/videoPic/huifu1.png"><span style="margin-left:8px;margin-top:-10px;">{{   d.commentCount }}</span></a></div>
			<div style="width:100px;height:24px;margin-top:-26px;margin-left:100px;"><a href="<%=path%>/web/videoPic/vpInfo/{{   d.id }}" ><span style="margin-left:8px;margin-top:-10px;">阅读全文>></span></a></div>
		</div>

	<!--图片的标签 src={{ d.furl }}-->
{{# } }}
</div>
{{#  }); }}	

</script>

</body>
</html>
