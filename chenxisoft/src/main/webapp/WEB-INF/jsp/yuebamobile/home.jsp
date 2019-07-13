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
<title>微官网首页</title>
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
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">小青月嫂
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b" style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
    </header>
	
	<div class="demo-item" style="margin-top:45px;padding-bottom:50px;">
		<div class="demo-block">
			<ul id="workerList" class="ui-list ui-border-tb ">
				
				
			</ul>
		</div>
		
	</div>
	<div class="ui-loading-wrap" id="loading" style="display:none">
            <p>加载中</p>
            <i class="ui-loading"></i>
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
	          var pageCount=10;
	          var currPage=1;
	       
	          
	          function getPage(isinit){
	              var _uriWorker = window.BASEPATH + 'home/getworkers?currPage='+currPage+'&pageCount='+pageCount;
		
				  $.get(_uriWorker, null, function(data){
				       currPage+=1;
				       data = parseAjaxResult(data);
				       if(!isinit&&data.length!=0){
				          $('#loading').fadeIn().show();
				       }
				       if(isinit){
				          initList(data);
				       }else{
				          setTimeout(function(){
				             initList(data);
			                 $('#loading').fadeOut().hide();
			              },2000);
				       }
				  });
	          
	          }
	          
	          function initList(data){
	               var html=[];

	               if(data){
	                  for(var i=0;i<data.length;i++){
	                     
				         html.push('<li class="worker" id="wor-'+data[i].id+'">');
						 html.push('    <div class="ui-list-img-square">');
						 html.push('        <span style="background-image:url(/'+data[i].photo+')"></span></div>');
						 html.push('	<div class="ui-list-info ui-border-t">');
						 html.push('		<p style="font-size:13px;">'+data[i].realName+'&nbsp;&nbsp;'+data[i].age+'岁<span style="color:red;margin-left:5px;">(带过'+data[i].orderCount+'个孩子)</span></p>');
						 html.push('<div style="height:5px;">&nbsp;</div>');
						 html.push('		<p class="ui-nowrap" style="font-size:13px;">'+data[i].level+'月嫂</p>');
						 html.push('		<p class="ui-nowrap" style="font-size:13px;">');
						 var subHtml=[];
						 if(data[i].idCardPhoto){
						    subHtml.push('<span style="color:green">身份证</span>');
						 }
						 if(data[i].healthPhoto){
						    subHtml.push('<span style="color:green">健康证明</span>');
						 }
						 if(data[i].expertPhoto){
						    subHtml.push('<span style="color:green">职业证书</span>');
						 }
						 html.push(subHtml.join('&nbsp;&nbsp;&nbsp;&nbsp;'))
                         html.push('</p>');
                         //html.push('<p style="font-size:13px;">'+data[i].age+'</p>');
						 html.push('	</div>');
						 html.push('</li>'); 
				       }
	               
	               }
			       $('#workerList').append(html.join(''));
	          }
	          
	          
	          getPage(true);
	          
	          $(window).scroll(function(){
			        // scroll at bottom
			       var innerHeight =  window.innerHeight;
			       if($(window).scrollTop() === $(document).height() - innerHeight){
			            // load data
			            getPage(false);
			       }
			  });
	          
	          
		      
		      $(document).on('click','.worker',function(){
		         var ids=this.id.split('-');
	             location.href=window.BASEPATH +'worker/mobile/index?workerId='+ids[1];
		      
		      });
		      
		     
	          
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>

</body>
</html>
