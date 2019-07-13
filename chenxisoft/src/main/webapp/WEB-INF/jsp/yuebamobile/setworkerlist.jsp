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
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">微官网首页
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b" style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
        
    </header>
	
	<div class="demo-item" style="margin-top:50px;" >
		<div class="demo-block">
			<ul id="seeList" class="ui-list ui-list-link ui-list-single ui-border-tb">
			    
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
	          var pageCount=15;
	          var currPage=1;
	       
	          
	          function getPage(isinit){
	              var _uriWorker = window.BASEPATH + 'worker/mobile/setworkerlist?currPage='+currPage+'&pageCount='+pageCount;
		
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
	                        html.push('<li id="wor-'+data[i].id+'" class="worker">');
						    html.push('    <div class=" ui-avatar-s">');
						    html.push('        <span style="background-image:url(/'+data[i].photo+')"></span>');
						    html.push('    </div>');
						    html.push('    <div class="ui-list-info ui-border-t">');
						    html.push('        <h4 class="ui-nowrap" style="font-size:12px">'+
						                  data[i].realName+'</h4>');
						    html.push('    </div>');
						    html.push('</li>');
				       }
	               
	               }
			       $('#seeList').append(html.join(''));
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
	             location.href=window.BASEPATH +'worker/mobile/setworker?workerId='+ids[1];
		      
		      });
		      
		     
	          
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
