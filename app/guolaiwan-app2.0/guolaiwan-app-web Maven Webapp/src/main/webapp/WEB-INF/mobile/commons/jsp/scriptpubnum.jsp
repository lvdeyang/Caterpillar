<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String weburl=WXContants.Website;
%>
<style>
  .weui_bar__item_on{
  
     background:#18b4ed;
     color:#FFF;
  } 
</style>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/fastclick.js"></script>
<script type="text/javascript">
    


    $(function() {
    	FastClick.attach(document.body);
    	function initMenu(num){
    	   var menuHtml=[];
		
		   menuHtml.push('<div class="weui-navbar" style="position:fixed;bottom:0px;top:auto;">');
		   menuHtml.push('<div id="index" class="bottomMenu weui-navbar__item '+(num==1?'weui_bar__item_on':'')+'">');
		   menuHtml.push('<span class="icon-home"></span>主页');
		   menuHtml.push('</div>');
		   menuHtml.push('<div id="order" class="bottomMenu weui-navbar__item '+(num==2?'weui_bar__item_on':'')+'">');
		   menuHtml.push('<span class="icon-shopping-cart"></span>购物车');
		   menuHtml.push('</div>');
		   menuHtml.push('<div id="person" class="bottomMenu weui-navbar__item '+(num==4?'weui_bar__item_on':'')+'">');
		   menuHtml.push('<span class="icon-user"></span>个人');
		   menuHtml.push('</div>');
	       menuHtml.push('</div>');
		
		   $('.content').append(menuHtml.join(''));
    	
    	}
    	
    	$(document).on('click','.bottomMenu',function(){
    	
    	 
    	  if(this.id=='index'){
    	     location.href=window.BASEPATH + 'pubnum/index';
    	  }else if(this.id=='order'){
    	     location.href=window.BASEPATH + 'pubnum/basket/index';
    	  }else if(this.id=="person"){
    	     location.href=window.BASEPATH + 'pubnum/personal/index';
    	  }
    	  
    	
    	});
    	
		var href=location.href;
		if(href.indexOf('pubnum/product/index')==-1&&
		   href.indexOf('pubnum/address/index')==-1&&
		   href.indexOf('pubnum/address/edit')==-1&&
		   href.indexOf('pubnum/basket/index')==-1&&
		   href.indexOf('pubnum/surpport')==-1&&
		   href.indexOf('supersell')==-1&&
		   href.indexOf('classify=PRODUCT')==-1){
		   
		   
		   if(href.indexOf('pubnum/index')!=-1||
		      href.indexOf('pubnum/gotoshop')!=-1||
		      href.indexOf('pubnum/column/index')!=-1||
		      href.indexOf('pubnum/merchant/index')!=-1||
		      href.indexOf('live')!=-1||
		      href.indexOf('/product/payinshop')!=-1||
		      href.indexOf('pubnum/activity/index')!=-1){
		      initMenu(1);
		   }else if(href.indexOf('search')!=-1){
		      initMenu(3);
		   }else{
		      initMenu(4);
		   }
		   
		}
		
		
		function initBack(){
		
		 
		    $('.link-left').children().remove();
		    $('.link-left').append('<span class="icon-chevron-left"></span>');
		   
		    
		    $(document).on('click','.link-left',function(){
		        history.back();
		        return false;
		    });
		}
		
		
		
		
		
		/*<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
		</div>*/
		$('.header').css('background',"#FFF").css('color','#000');
		$('.link-left').children().remove();
		$('.link-left').append('<span class="icon-chevron-left"></span>');
		if(href.indexOf('supersell')==-1){
		    initBack();
	        var str="";
			if(href.indexOf('pubnum/index')!=-1){
			  str='<div style="font-size:24px;line-height:40px;position:absolute;margin-top:-42px;z-index:1000;right:10px"><image id="ewmpic" style="height:18px;width:18px;" src="http://<%=weburl%>/file/ewm.jpg" /></div>';
			}
			var nameHeader='过来玩';
			var phone='0315-6681288';
			var ccode='${comCode}';
			if(ccode=='1003'){
			   nameHeader='全域休闲';
			   phone='010-89991991';
			}
			$('.header-content').html('<image src="lib/images/logo.jpg" style="position:absolute;margin-top:5px;width:25px;height:25px;display:inline-block;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="headerName" style="font-size:14px">'+nameHeader+'</span><div id="phone" style="font-size:10px;position:absolute;margin-top:-38px;z-index:1000;right:40px">'+phone+'</div>'
			+str);
			
			
			$(document).on('click','#phone',function(){
		       location.href = 'tel://0315-6681288';
		    });
		}
		
		
    	
    });
    
    //解析ajax返回
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip(data.message, 'error');
				return -1;
			}else{
				return data.data;		
			}
		};
		
		
		function openqq(qq){
            var type = undefined;
            var param = "";
            var sid = 2;
            var rawuin = qq;//这里填写QQ号
            var qsig = "undefined";
            var QQApi = {
                openURL: function(url){
                    var i = document.createElement('iframe');
                    i.style.display = 'none';
                    i.onload = function() { i.parentNode.removeChild(i); };
                    i.src = url;
                    document.body.appendChild(i);

                    var returnValue = QQApi.__RETURN_VALUE;
                    QQApi.__RETURN_VALUE = undefined;
                    return returnValue;
                },

                isAppInstalled: function(scheme) {
                    var parameters = {'scheme':scheme};
                    var r = QQApi.openURL('jsbridge://app/isInstalled_?p=' + encodeURIComponent(JSON.stringify(parameters)));
                    return r ? r.result : null;
                },

                isQQWebView: function(){
                    return QQApi.isAppInstalled('mqq') == true;
                },

                __RETURN_VALUE: undefined
            };

            var usa=navigator.userAgent;
            var p;
            var mobile_q_jump = {
                android:"https://play.google.com/store/apps/details?id=com.tencent.mobileqq",
                ios:"itms-apps://itunes.apple.com/cn/app/qq-2011/id444934666?mt=8",
                winphone:"http://www.windowsphone.com/zh-cn/store/app/qq/b45f0a5f-13d8-422b-9be5-c750af531762",
                pc:"http://mobile.qq.com/index.html"
            };
            var isMQ = 0;
            if(typeof type == "undefined") type = 1;
            if(usa.indexOf("Android")>-1){
                p = "android";
            }
            else if(usa.indexOf("iPhone")>-1 || usa.indexOf("iPad")>-1 || usa.indexOf("iPod")>-1){
                p = "ios";
            }
            else if(usa.indexOf("Windows Phone") > -1 || usa.indexOf("WPDesktop") > -1){
                p = "winphone";
            }
            else {
                p = "pc";
            }
            if(p == "ios"){
                //防止循环
                if(history.pushState)
                    history.pushState({},"t","#");
                isMQ = QQApi.isQQWebView();
                if (!isMQ){ 
                    var sc = document.createElement("script");
                    sc.src = "http://__.qq.com/api/qqapi.js";
                    sc.onload = function(){
                        if(window['iOSQQApi']){
                            isMQ =iOSQQApi.device.isMobileQQ(); 
                        }
                    };
                    document.body.appendChild(sc);
                }
            }
            else if(p == "pc" && qsig != "undefined"){
                window.open(qsig,"_self");
            }
            if(type == 1){//手Q
                var isSuccess = true;
                var f = document.createElement("iframe");
                f.style.display = "none";
                document.body.appendChild(f);
                f.onload = function(){
                    isSuccess = false;
                };
                if(p == "ios" && sid == 1){
                    f.src = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin="+ rawuin +"&card_type=person&source=qrcode";
                }
                if(p == "ios" && sid == 2){//ios并且为群名片
                    f.src = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin="+ rawuin +"&card_type=person&source=qrcode";
                }
                else if(p != "pc"){
                    var url = window.location.href.split("&");
                    f.src = "mqqopensdkapi://bizAgent/qm/qr?url=" + encodeURIComponent(url[0]);
                }
                if(p == "android" && sid == 1){
                    f.src = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin="+ rawuin +"&card_type=person&source=qrcode";
                }
                if(p == "android" && sid == 2){//ios并且为群名片
                    f.src = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin="+ rawuin +"&card_type=person&source=qrcode";
                }
                var now = Date.now();
                setTimeout( function(){
                    if((p == "ios" && !isMQ && Date.now() - now < 2000) || (p == "android" && !isSuccess) || ((p == "winphone" && Date.now() - now < 2000))){
                        var jumpUrl = mobile_q_jump[p]; 
                        if(jumpUrl) window.open(jumpUrl,"_self");
                    }
                } , 1500);

            }
        }
		
		
</script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/js/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery.mmenu/js/jquery.mmenu.min.all.js"></script>
<script type="text/javascript" src="<%=basePath %>web/lib/ui/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=basePath %>web/commons/date/date.ext.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/swiper.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jquery_json.js"></script>