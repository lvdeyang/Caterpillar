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
	<!-- <div class="ui-tab" style="margin-top:40px;">
		<ul class="ui-tab-nav ui-border-b">
               <li id="PAYSUCCESS" class="mytab">已支付</li>
               <li id="COMPLETE" class="mytab">已完成</li>
               <li id="REFUNDING" class="mytab">退款中</li>
               <li id="REFUNDED" class="mytab">已退款</li>
	    </ul>
	    <ul class="ui-tab-content" style="width:200%">
	        <li></li>
	        <li></li>
	        <li></li>
	        <li></li>
	    </ul>
    </div> -->
	<div class="demo-item" style="margin-top:45px;">
		<div class="demo-block">
			<ul id="orderList" class="ui-list ui-border-tb ">
				
				
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
	          var adminFlg=${user.adminFlg};
	          function getPage(isinit){
	              var _uriOrder = window.BASEPATH + 'order/mobile/wlist.do?currPage='+currPage+
	              '&pageCount='+pageCount+'&workerId=${workerId}';
		
				  $.get(_uriOrder, null, function(data){
				       currPage+=1;
				       data = parseAjaxResult(data);
				       if(data.length!=0&&!isinit){
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
				         html.push('<li class="order" id="order-'+data[i].id+'">');
						 html.push('    <div class="ui-list-img-square">');
						 html.push('        <span style="background-image:url(/'+data[i].worderPhoto+')"></span></div>');
						 html.push('	<div class="ui-list-info ui-border-t">');
						 html.push('		<div style="font-size:14px;">'+data[i].workName+'</div>');
						 html.push('		<p class="ui-nowrap" style="font-size:12px;">'+
						           data[i].days+'天</p>');
						 html.push('		<p class="ui-nowrap" style="font-size:12px;">开始日期:'+data[i].fromDateStr+'</p>');
						 html.push('	</div>');
						 html.push('</li>'); 
						 if(adminFlg==1){
						 	html.push('<p style="width:100%;height:20px;background:#FFF;border-bottom:1px solid #CCC"><a style="float:right;margin-right:15px;font-size:12px" href="javascript:void(0)" class="del" id="del-'+data[i].id+'">删除</a>'+
						 	'<a style="float:right;margin-right:15px;font-size:12px" href="javascript:void(0)" class="modify" id="modify-'+data[i].id+'">修改用户</a>'+
						 	'<a style="float:right;margin-right:15px;font-size:12px" href="javascript:void(0)" class="assign" id="assign-'+data[i].id+'">签到记录</a></p>')
						 
						 }
						 
				       }
	               
	               }
			       $('#orderList').append(html.join(''));
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
	          
	          
	          /*var tab = new fz.Scroll('.ui-tab', {
		            role: 'tab',
		            autoplay: false,
		            interval: 3000
		      });
		      
		      $('.mytab').on('click',function(){
		            $('#orderList').children().remove();
		            orderStatus=$(this).attr('id');
		            currPage=1;
		            getPage(true);
		      });*/
	          
		      
		      $(document).on('click','.order',function(){
		         var ids=this.id.split('-');
	             location.href=window.BASEPATH + 'order/mobile/winfo?orderId='+ids[1];
		      });
		      
		      $(document).on('click','.del',function(){
		         var ids=this.id.split('-');
	             var _uridelOrder = window.BASEPATH + 'order/mobile/del?orderId='+ids[1];
		
				  $.get(_uridelOrder, null, function(data){

				       location.href=window.BASEPATH + 'order/mobile/wlist?workerId=${workerId}';
				  });
		      });
		      
		      $(document).on('click','.modify',function(){
		         var ids=this.id.split('-');
	             location.href = window.BASEPATH + 'order/mobile/modify?orderId='+ids[1];
		
		      });
		      
		      $(document).on('click','.assign',function(){
		         var ids=this.id.split('-');
	             location.href = window.BASEPATH + 'assign/mobile/ulist?orderId='+ids[1];
		
		      });
		      
		      
		     
	          
	     });
	
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
</body>
</html>
