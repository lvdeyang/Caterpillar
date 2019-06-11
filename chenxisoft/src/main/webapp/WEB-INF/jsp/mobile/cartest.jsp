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
<script src="http://demo.mycodes.net/kuangjia/frozenui/frozenjs/lib/zepto.min.js"></script>
<script src="http://demo.mycodes.net/kuangjia/frozenui/frozenjs/1.0.1/frozen.js"></script>
</head>

<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">微官网首页
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b">
         
         <h1 style="font-size:14px;width:80px;float:left;">微官网首页</h1>
         <a href="person/index"><i style="font-size:14px;width:20px;line-height:50px;float:right"class="icon-user"></i></a>
    </header>
	<div class="ui-tab" style="margin-top:40px;">
		<ul class="ui-tab-nav ui-border-b">
               <li id="recomm" class="mytab">推荐</li>
               <li id="article" class="mytab">文章</li>
               <li id="product" class="mytab">比价</li>
	    </ul>
	    <ul class="ui-tab-content" style="width:200%">
	        <li></li>
	        <li></li>
	        <li></li>
	    </ul>
    </div>
	<div class="demo-item">
		<div class="demo-block">
			<ul id="articleList" class="ui-list ui-border-tb ">
				
				
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
	          var dic={'XINLANG':'新浪','YICHE':'易车','JINGDONG':'京东'};
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
	          var type='recomm';
	          
	          function getPage(isinit){
	              var _uriArticle = window.BASEPATH + 'cartest/list?currPage='+currPage+'&pageCount='+pageCount+
	              '&type='+type;
		
				  $.get(_uriArticle, null, function(data){
				       currPage+=1;
				       data = parseAjaxResult(data);
				       if(!isinit&&data.articles.length!=0){
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

	               if(data.articles){
	                  for(var i=0;i<data.articles.length;i++){
				         html.push('<li class="article" id="art-'+data.articles[i].id+'">');
						 html.push('    <div class="ui-list-img-square">');
						 html.push('        <span style="background-image:url('+data.articles[i].pic+')"></span></div>');
						 html.push('	<div class="ui-list-info ui-border-t">');
						 html.push('		<div style="font-size:13px;">'+data.articles[i].title+'</div>');
						 html.push('		<p class="ui-nowrap" style="font-size:13px;">来源-'+dic[data.articles[i].source]+'</p>');
						 html.push('	</div>');
						 html.push('</li>'); 
				       }
	               
	               }
			       
			       if(data.products){
		               for(var i=0;i<data.products.length;i++){
				         html.push('<li style="margin-top:10px" class="article" id="art-'+data.products[i].id+'">');
						 html.push('    <div class="ui-list-img-square">');
						 html.push('        <span style="background-image:url('+data.products[i].pic+')"></span></div>');
						 html.push('	<div class="ui-list-info ui-border-t">');
						 html.push('		<div style="font-size:13px;">'+data.products[i].shortContent+'</div>');
						 html.push('		<p class="ui-nowrap" style="font-size:13px;">来源-'+dic[data.products[i].source]+
						           '￥<span style="font-size:14px;color:red">'+data.products[i].price+'</span></p>');
						 html.push('	</div>');
						 html.push('</li>'); 
				       }
	               
	               }
			       

			       $('#articleList').append(html.join(''));
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
	          
	          var tab = new fz.Scroll('.ui-tab', {
		            role: 'tab',
		            autoplay: false,
		            interval: 3000
		      });
		      
		      $('.mytab').on('click',function(){
		            $('#articleList').children().remove();
		            type=$(this).attr('id');
		            currPage=1;
		            getPage(true);
		      });
		      
		      $(document).on('click','.article',function(){
		         var ids=this.id.split('-');
		         location.href='article/mobile/index?articleId='+ids[1];
		      
		      });
		      
	          
	     });
	
	</script>
</body>
</html>
