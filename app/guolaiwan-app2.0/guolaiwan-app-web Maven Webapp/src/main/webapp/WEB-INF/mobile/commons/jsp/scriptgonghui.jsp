<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String weburl=WXContants.Website;
%>
<style>
  .weui_bar__item_on{
  
     background:none;
     color:#E7030B;
  } 
</style>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/fastclick.js"></script>
<script type="text/javascript">
    


    $(function() {
    	FastClick.attach(document.body);

		
		
		function initBack(){
		
		 
		    $('.link-left').children().remove();
		    $('.link-left').append('<span class="icon-chevron-left"></span>');
		   
		    
		    $(document).on('click','.link-left',function(){
		        history.back();
		        return false;
		    });
		}

		$('.link-left').children().remove();
		$('.link-left').append('<span class="icon-chevron-left"></span>');
		
    	
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
		
		
		function initMenu(num){
    	   var menuHtml=[];
		
		   menuHtml.push('<div class="weui-navbar" style="font-size:small;position:fixed;bottom:0px;left:0px;top:auto;style="background:none">');
		   menuHtml.push('<div id="index" class="bottomMenu weui-navbar__item '+(num==1?'weui_bar__item_on':'')+'">');
		   menuHtml.push('<span class="icon-home"></span>活动介绍');
		   menuHtml.push('</div>');
		   menuHtml.push('<div id="upload" class="bottomMenu weui-navbar__item '+(num==2?'weui_bar__item_on':'')+'">');
		   menuHtml.push('<span class="icon-upload"></span>上传视频');
		   menuHtml.push('</div>');
		   menuHtml.push('<div id="acount" class="bottomMenu weui-navbar__item '+(num==3?'weui_bar__item_on':'')+'">');
		   menuHtml.push('<span class="icon-heart"></span>点赞');
		   menuHtml.push('</div>');
	       menuHtml.push('</div>');
		
		   $('.content').append(menuHtml.join(''));
    	
    	}
    	
    	$(document).on('click','.bottomMenu',function(){
    	
    	 
    	  if(this.id=='index'){
    	     location.href=window.BASEPATH + 'gonghui/video/index';
    	  }else if(this.id=='upload'){
    	     location.href=window.BASEPATH + 'gonghui/video/upload/index';
    	  }else if(this.id=="acount"){
    	     location.href=window.BASEPATH + 'gonghui/video/list/index';
    	  }
    	  
    	
    	});
    	
		var href=location.href;
		if(href.indexOf('gonghui/video')!=-1){
		   
		   
		   if(href.indexOf('upload')!=-1){
		      initMenu(2);
		   }else if(href.indexOf('list')!=-1){
		      initMenu(3);
		   }else{
		      initMenu(1);
		   }
		   
		}
		
		
		
		
		
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