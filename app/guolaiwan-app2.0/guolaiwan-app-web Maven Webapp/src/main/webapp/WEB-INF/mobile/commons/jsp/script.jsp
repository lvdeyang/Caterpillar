<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/fastclick.js"></script>
<script type="text/javascript">
    $(function() {
    	FastClick.attach(document.body);
    	function initMenu(){
    	   var menuHtml=[];
		
		   menuHtml.push('<div class="weui-navbar" style="position:fixed;bottom:0px;top:auto">');
		   menuHtml.push('<div id="index" class="bottomMenu weui-navbar__item weui_bar__item_on">');
		   menuHtml.push('<span class="icon-home"></span>主页');
		   menuHtml.push('</div>');
		   menuHtml.push('<div id="person" class="bottomMenu weui-navbar__item">');
		   menuHtml.push('<span class="icon-user"></span>个人');
		   menuHtml.push('</div>');
	       menuHtml.push('</div>');
		
		   $('.content').append(menuHtml.join(''));
    	
    	}
    	
    	$(document).on('click','.bottomMenu',function(){
    	  if(this.id=='index'){
    	     location.href=window.BASEPATH + 'distributor/distribute/index/0/0';
    	  }else if(this.id=="person"){
    	     location.href=window.BASEPATH + 'distributor/app/login/0';
    	  }
    	  
    	
    	});
    	
    	
		var href=location.href;
		if(href.indexOf('pubnum/admin')==-1&&
		   href.indexOf('distributor/product/index')==-1&&
		   href.indexOf('distributor/off/sell')==-1&&
		   href.indexOf("distributor/offorder/edit")==-1&&
		   href.indexOf("distributor/order/check")==-1&&
		   href.indexOf("distributor/order/checkadmin")==-1&&
		   href.indexOf("distributor/pay/index")==-1&&
		   href.indexOf("distributor/mod/product/index")==-1){
		   initMenu();
		}
		
		
		function initBack(){
		
		    $('.link-left').children().remove();
		    $('.link-left').append('<span class="icon-chevron-left"></span>');
		    $(document).on('click','.link-left',function(){
		        history.back();
		        return false;
		    });
		}
		
		initBack();
    	
    });
    
    //解析ajax返回
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
		};
		
		
		
		
		
		
		
		
</script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/js/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery.mmenu/js/jquery.mmenu.min.all.js"></script>
<script type="text/javascript" src="<%=basePath %>web/lib/ui/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=basePath %>web/commons/date/date.ext.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/swiper.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jquery_json.js"></script>